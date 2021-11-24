package ru.an1s9n.binanceapiclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.web.reactive.socket.CloseStatus;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.model.market.KlineInterval;
import ru.an1s9n.binanceapiclient.model.websocket.AggregateTradeEvent;
import ru.an1s9n.binanceapiclient.model.websocket.KlineEvent;
import ru.an1s9n.binanceapiclient.model.websocket.TradeEvent;
import ru.an1s9n.binanceapiclient.websocket.WebSocketSessionFacade;
import ru.an1s9n.binanceapiclient.websocket.WebSocketSessionFacadeImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;
import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.RAW_WEB_SOCKET_STREAM_ENDPOINT;
import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.apiWebSocketUrl;

@RequiredArgsConstructor
@Slf4j
public class BinanceApiWebSocketClientImpl implements BinanceApiWebSocketClient {

  private final WebSocketClient webSocketClient;
  private final ObjectMapper mapper;
  private final String apiKey;
  private final String secret;
  private final Map<UUID, WebSocketSession> sessions = new ConcurrentHashMap<>();

  @Override
  public WebSocketSessionFacade getAggregateTrades(List<String> symbols, Consumer<? super AggregateTradeEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, null, AggregateTradeEvent.class, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade getAggregateTrades(String symbol, Consumer<? super AggregateTradeEvent> onEvent) {
    return getAggregateTrades(List.of(symbol), onEvent);
  }

  @Override
  public WebSocketSessionFacade getTrades(List<String> symbols, Consumer<? super TradeEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, null, TradeEvent.class, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade getTrades(String symbol, Consumer<? super TradeEvent> onEvent) {
    return getTrades(List.of(symbol), onEvent);
  }

  @Override
  public WebSocketSessionFacade getKlines(List<String> symbols, KlineInterval klineInterval, Consumer<? super KlineEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, klineInterval.getId(), KlineEvent.class, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade getKlines(String symbol, KlineInterval klineInterval, Consumer<? super KlineEvent> onEvent) {
    return getKlines(List.of(symbol), klineInterval, onEvent);
  }

  private <T> Mono<Void> createStream(List<String> symbols, String secondParam, Class<T> eventType, Consumer<? super T> onEvent, UUID sessionUuid) {
    if(symbols == null || symbols.isEmpty()) {
      return Mono.error(new IllegalArgumentException("Symbols can not be null or empty."));
    }
    final var streamName = streamName(symbols, secondParam, eventType);
    final var uri = uriFor(streamName);
    if(uri == null) {
      return Mono.error(new IllegalArgumentException("Illegal symbols \"" + symbols + "\", can not construct URI to obtain WebSocket connection."));
    }
    return webSocketClient.execute(uri, session -> {
      sessions.put(sessionUuid, session);
      var pongsFlux = session
        .receive()
        .doOnNext(message -> {
          if(message.getType() == WebSocketMessage.Type.TEXT) {
            try {
              var event = mapper.readValue(message.getPayloadAsText(), eventType);
              onEvent.accept(event);
            } catch(JsonProcessingException e) {
              log.warn("Can not deserialize from {} to {}. Skipping this message.", message.getPayloadAsText(), eventType.getSimpleName());
            }
          }
        })
        .filter(message -> message.getType() == WebSocketMessage.Type.PING)
        .map(ping -> session.pongMessage(DataBufferFactory::allocateBuffer))
        .doFinally(signalType -> {
          sessions.remove(sessionUuid);
          session.closeStatus().subscribe(status -> {
            if(status != CloseStatus.NORMAL) {
              log.debug("WebSocket session {} {} has been closed with abnormal status {}. Going to reconnect.", streamName, sessionUuid, status);
              createStream(symbols, secondParam, eventType, onEvent, sessionUuid).subscribe();
            }
          });
        });
      return session.send(pongsFlux);
    });
  }

  @SneakyThrows(ReflectiveOperationException.class)
  private <T> String streamName(List<String> symbols, String secondParam, Class<T> eventType) {
    final var streamNameFormat = eventType.getField("STREAM_NAME").get(null).toString();
    return "/" + symbols
      .stream()
      .map(symbol -> streamNameFormat.formatted(symbol, secondParam))
      .collect(Collectors.joining("/"));
  }

  private URI uriFor(String streamName) {
    try {
      return new URI(apiWebSocketUrl() + RAW_WEB_SOCKET_STREAM_ENDPOINT + streamName);
    } catch(URISyntaxException e) {
      return null;
    }
  }

}

package ru.an1s9n.binance.api.client.core;

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
import ru.an1s9n.binance.api.client.core.config.BinanceApiConfig;
import ru.an1s9n.binance.api.client.core.model.market.KlineInterval;
import ru.an1s9n.binance.api.client.core.model.websocket.AggregateTradeEvent;
import ru.an1s9n.binance.api.client.core.model.websocket.BookTickerEvent;
import ru.an1s9n.binance.api.client.core.model.websocket.Depth;
import ru.an1s9n.binance.api.client.core.model.websocket.DepthUpdateEvent;
import ru.an1s9n.binance.api.client.core.model.websocket.KlineEvent;
import ru.an1s9n.binance.api.client.core.model.websocket.MiniTicker24HrEvent;
import ru.an1s9n.binance.api.client.core.model.websocket.PartialBookDepthEvent;
import ru.an1s9n.binance.api.client.core.model.websocket.Ticker24HrEvent;
import ru.an1s9n.binance.api.client.core.model.websocket.TradeEvent;
import ru.an1s9n.binance.api.client.core.model.websocket.UpdateSpeed;
import ru.an1s9n.binance.api.client.core.websocket.WebSocketSessionFacade;
import ru.an1s9n.binance.api.client.core.websocket.WebSocketSessionFacadeImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

@RequiredArgsConstructor
@Slf4j
public class BinanceApiWebSocketClientImpl implements BinanceApiWebSocketClient {

  private final WebSocketClient webSocketClient;
  private final ObjectMapper mapper;
  private final Map<UUID, WebSocketSession> sessions = new ConcurrentHashMap<>();

  @Override
  public WebSocketSessionFacade aggregateTrades(List<String> symbols, Consumer<? super AggregateTradeEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, List.of(), AggregateTradeEvent.class, false, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade aggregateTrades(String symbol, Consumer<? super AggregateTradeEvent> onEvent) {
    return aggregateTrades(safeListOf(symbol), onEvent);
  }

  @Override
  public WebSocketSessionFacade trades(List<String> symbols, Consumer<? super TradeEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, List.of(), TradeEvent.class, false, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade trades(String symbol, Consumer<? super TradeEvent> onEvent) {
    return trades(safeListOf(symbol), onEvent);
  }

  @Override
  public WebSocketSessionFacade klines(List<String> symbols, KlineInterval klineInterval, Consumer<? super KlineEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, List.of(klineInterval.getId()), KlineEvent.class, false, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade klines(String symbol, KlineInterval klineInterval, Consumer<? super KlineEvent> onEvent) {
    return klines(safeListOf(symbol), klineInterval, onEvent);
  }

  @Override
  public WebSocketSessionFacade miniTicker24Hr(List<String> symbols, Consumer<? super MiniTicker24HrEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, List.of(), MiniTicker24HrEvent.class, false, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade miniTicker24Hr(String symbol, Consumer<? super MiniTicker24HrEvent> onEvent) {
    return miniTicker24Hr(safeListOf(symbol), onEvent);
  }

  @Override
  public WebSocketSessionFacade miniTicker24Hr(Consumer<? super MiniTicker24HrEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(null, List.of(), MiniTicker24HrEvent.class, true, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade ticker24Hr(List<String> symbols, Consumer<? super Ticker24HrEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, List.of(), Ticker24HrEvent.class, false, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade ticker24Hr(String symbol, Consumer<? super Ticker24HrEvent> onEvent) {
    return ticker24Hr(safeListOf(symbol), onEvent);
  }

  @Override
  public WebSocketSessionFacade ticker24Hr(Consumer<? super Ticker24HrEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(null, List.of(), Ticker24HrEvent.class, true, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade bookTicker(List<String> symbols, Consumer<? super BookTickerEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, List.of(), BookTickerEvent.class, false, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade bookTicker(String symbol, Consumer<? super BookTickerEvent> onEvent) {
    return bookTicker(safeListOf(symbol), onEvent);
  }

  @Override
  public WebSocketSessionFacade bookTicker(Consumer<? super BookTickerEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(null, null, BookTickerEvent.class, true, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade partialBookDepth(List<String> symbols, Depth depth, UpdateSpeed updateSpeed, Consumer<? super PartialBookDepthEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, List.of(depth.getDepthLevel(), updateSpeed.getId()), PartialBookDepthEvent.class, false, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade partialBookDepth(String symbol, Depth depth, UpdateSpeed updateSpeed, Consumer<? super PartialBookDepthEvent> onEvent) {
    return partialBookDepth(safeListOf(symbol), depth, updateSpeed, onEvent);
  }

  @Override
  public WebSocketSessionFacade depthUpdates(List<String> symbols, UpdateSpeed updateSpeed, Consumer<? super DepthUpdateEvent> onEvent) {
    final var sessionUuid = randomUUID();
    createStream(symbols, List.of(updateSpeed.getId()), DepthUpdateEvent.class, false, onEvent, sessionUuid).subscribe();
    return new WebSocketSessionFacadeImpl(sessions, sessionUuid);
  }

  @Override
  public WebSocketSessionFacade depthUpdates(String symbol, UpdateSpeed updateSpeed, Consumer<? super DepthUpdateEvent> onEvent) {
    return depthUpdates(safeListOf(symbol), updateSpeed, onEvent);
  }

  private <T> Mono<Void> createStream(List<String> symbols, List<String> additionalParams, Class<T> eventType, boolean allMarket, Consumer<? super T> onEvent, UUID sessionUuid) {
    if(!allMarket && (symbols == null || symbols.isEmpty())) {
      return Mono.error(new IllegalArgumentException("Symbols can not be null or empty."));
    }
    final var streamName = streamName(allMarket, symbols, additionalParams, eventType);
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
              final var payload = message.getPayloadAsText();
              if(allMarket && payload.startsWith("[")) {
                final List<T> events = mapper.readValue(payload, mapper.getTypeFactory().constructCollectionType(List.class, eventType));
                events.forEach(onEvent);
              } else {
                final var event = mapper.readValue(payload, eventType);
                onEvent.accept(event);
              }
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
              createStream(symbols, additionalParams, eventType, allMarket, onEvent, sessionUuid).subscribe();
            }
          });
        });
      return session.send(pongsFlux);
    });
  }

  @SneakyThrows(ReflectiveOperationException.class)
  private <T> String streamName(boolean allMarket, List<String> symbols, List<String> additionalParams, Class<T> eventType) {
    if(allMarket) {
      return "/" + eventType.getField("ALL_MARKET_STREAM_NAME").get(null).toString();
    }
    final var streamNameFormat = eventType.getField("STREAM_NAME").get(null).toString();
    return "/" + symbols
      .stream()
      .map(symbol -> streamNameFormat.formatted(symbol, "%s", "%s"))
      .map(streamNameFormatWithSymbol -> streamNameFormatWithSymbol.formatted(additionalParams.toArray()))
      .collect(Collectors.joining("/"));
  }

  private URI uriFor(String streamName) {
    try {
      return new URI(BinanceApiConfig.apiWebSocketUrl() + BinanceApiConfig.Endpoints.RAW_WEB_SOCKET_STREAM_ENDPOINT + streamName);
    } catch(URISyntaxException e) {
      return null;
    }
  }

  private List<String> safeListOf(String symbol) {
    return symbol.isBlank() ? List.of() : List.of(symbol);
  }

}

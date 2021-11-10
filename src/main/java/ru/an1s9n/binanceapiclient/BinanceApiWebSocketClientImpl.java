package ru.an1s9n.binanceapiclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.model.websocket.AggregateTradeEvent;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.function.Consumer;

import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.RAW_WEB_SOCKET_STREAM_ENDPOINT;
import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.apiWebSocketUrl;

@RequiredArgsConstructor
@Slf4j
//TODO: reconnect if disconnected by Binance or do reconnect each 24 hours? Client side disconnection support?
public class BinanceApiWebSocketClientImpl implements BinanceApiWebSocketClient {

  private final WebSocketClient webSocketClient;
  private final ObjectMapper mapper;
  private final String apiKey;
  private final String secret;

  @Override
  public void getAggregateTrades(String symbol, Consumer<? super AggregateTradeEvent> onEvent) {
    createStream(symbol, AggregateTradeEvent.class, onEvent).subscribe();
  }

  @SneakyThrows(ReflectiveOperationException.class)
  private <T> Mono<Void> createStream(String symbol, Class<T> eventType, Consumer<? super T> onEvent) {
    if(symbol == null) {
      return Mono.error(new IllegalArgumentException("Symbol can not be null."));
    }
    final var streamName = eventType.getField("STREAM_NAME").get(null).toString().formatted(symbol.toLowerCase());
    final var uri = uriFor(streamName);
    if(uri == null) {
      return Mono.error(new IllegalArgumentException("Illegal symbol \"" + symbol + "\", can not construct URI to obtain WebSocket connection."));
    }
    return webSocketClient.execute(uri, session -> {
      var pongsFlux = session
        .receive()
        .doOnNext(message -> {
          if(message.getType() == WebSocketMessage.Type.TEXT) {
            try {
              var event = mapper.readValue(message.getPayloadAsText(), eventType);
              onEvent.accept(event);
            } catch(JsonProcessingException e) {
              log.warn("Can not deserialize from {} to {}. Skipping this message.", message, eventType.getSimpleName());
            }
          }
        })
        .filter(message -> message.getType() == WebSocketMessage.Type.PING)
        .map(ping -> session.pongMessage(DataBufferFactory::allocateBuffer));
      return session.send(pongsFlux);
    });
  }

  private URI uriFor(String streamName) {
    try {
      return new URI(apiWebSocketUrl() + RAW_WEB_SOCKET_STREAM_ENDPOINT + streamName);
    } catch(URISyntaxException e) {
      return null;
    }
  }

}

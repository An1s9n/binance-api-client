package ru.an1s9n.binance.api.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.websocket.WsWebSocketContainer;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.socket.client.TomcatWebSocketClient;
import org.springframework.web.reactive.socket.client.WebSocketClient;
import ru.an1s9n.binance.api.client.config.BinanceApiConfig;

import javax.websocket.WebSocketContainer;

public class BinanceApiClientFactory {

  private static final ObjectMapper mapper = new ObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
  private static final ExchangeStrategies exchangeStrategies = ExchangeStrategies.builder()
    .codecs(configurer -> {
      configurer.defaultCodecs().maxInMemorySize(16 * 1024 * 1024);
      configurer.defaultCodecs().jackson2JsonDecoder(new Jackson2JsonDecoder(mapper, MediaType.APPLICATION_JSON));
    })
    .build();
  private static final WebClient webClient = WebClient.builder()
    .exchangeStrategies(exchangeStrategies)
    .baseUrl(BinanceApiConfig.apiBaseUrl())
    .build();
  private static final WebSocketContainer webSocketContainer = new WsWebSocketContainer();
  static {
    webSocketContainer.setDefaultMaxTextMessageBufferSize(819200);
  }
  private static final WebSocketClient webSocketClient = new TomcatWebSocketClient(webSocketContainer);

  public static BinanceApiReactiveClient getBinanceApiReactiveClient(String apiKey, String secret) {
    return new BinanceApiReactiveClientImpl(webClient, apiKey, secret);
  }

  public static BinanceApiReactiveClient getBinanceApiReactiveClient() {
    return new BinanceApiReactiveClientImpl(webClient, null, null);
  }

  public static BinanceApiSimpleClient getBinanceApiSimpleClient(String apiKey, String secret) {
    return new BinanceApiSimpleClientImpl(getBinanceApiReactiveClient(apiKey, secret));
  }

  public static BinanceApiSimpleClient getBinanceApiSimpleClient() {
    return new BinanceApiSimpleClientImpl(getBinanceApiReactiveClient());
  }

  public static BinanceApiWebSocketClient getBinanceApiWebSocketClient() {
    return new BinanceApiWebSocketClientImpl(webSocketClient, mapper);
  }

}

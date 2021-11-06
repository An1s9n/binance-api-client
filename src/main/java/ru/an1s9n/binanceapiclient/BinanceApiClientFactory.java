package ru.an1s9n.binanceapiclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import ru.an1s9n.binanceapiclient.config.BinanceApiConfig;

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

  public static BinanceApiWebSocketClient getBinanceApiWebSocketClient(String apiKey, String secret) {
    return null; //TODO
  }

  public static BinanceApiWebSocketClient getBinanceApiWebSocketClient() {
    return null; //TODO
  }

}

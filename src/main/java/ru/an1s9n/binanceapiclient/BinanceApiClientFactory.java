package ru.an1s9n.binanceapiclient;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import ru.an1s9n.binanceapiclient.config.BinanceApiConfig;

public class BinanceApiClientFactory {

  private static final ObjectMapper mapper = new ObjectMapper()
    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
    .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true);
  private static final WebClient webClient = WebClient.builder()
    .exchangeStrategies(
      ExchangeStrategies.builder()
        .codecs(configurer -> configurer
          .defaultCodecs()
          .maxInMemorySize(16 * 1024 * 1024)
        )
        .build()
    )
    .baseUrl(BinanceApiConfig.apiBaseUrl())
    .build();

  public static BinanceApiRestClient getBinanceApiRestClient(String apiKey, String secret) {
    return new BinanceApiRestClientImpl(mapper, webClient, apiKey, secret);
  }

  public static BinanceApiRestClient getBinanceApiRestClient() {
    return new BinanceApiRestClientImpl(mapper, webClient, null, null);
  }

}

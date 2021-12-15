package ru.an1s9n.binance.api.client.autoconfigure;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.an1s9n.binance.api.client.BinanceApiClientFactory;
import ru.an1s9n.binance.api.client.BinanceApiWebSocketClient;

@Configuration
@ConditionalOnClass(BinanceApiClientFactory.class)
@ConfigurationPropertiesScan
@RequiredArgsConstructor
public class BinanceApiClientAutoconfiguration {

  private final BinanceApiClientProperties binanceApiClientProperties;

  @Bean
  @ConditionalOnMissingBean
  public BinanceApiWebSocketClient binanceApiWebSocketClient() {
    return BinanceApiClientFactory.getBinanceApiWebSocketClient();
  }

}

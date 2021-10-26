package ru.an1s9n.binanceapiclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;
import ru.an1s9n.binanceapiclient.model.market.ServerTime;

import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.*;

@RequiredArgsConstructor
public class BinanceApiReactiveClientImpl implements BinanceApiReactiveClient {

  private final WebClient webClient;
  private final String apiKey;
  private final String secret;

  //TODO: handle errors


  @Override
  public Mono<Void> ping() {
    return webClient.get().uri(PING_ENDPOINT).retrieve().bodyToMono(Void.class);
  }

  @Override
  public Mono<Long> getServerTime() {
    return webClient.get().uri(SERVER_TIME_ENDPOINT).retrieve().bodyToMono(ServerTime.class).flatMap(st -> Mono.just(st.getServerTime()));
  }

  @Override
  public Mono<ExchangeInfo> getExchangeInfo() {
    return webClient.get().uri(EXCHANGE_INFO_ENDPOINT).retrieve().bodyToMono(ExchangeInfo.class);
  }

}

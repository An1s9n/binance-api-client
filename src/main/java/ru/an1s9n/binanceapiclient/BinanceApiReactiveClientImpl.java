package ru.an1s9n.binanceapiclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.model.market.ServerTime;

import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.SERVER_TIME_ENDPOINT;

@RequiredArgsConstructor
public class BinanceApiReactiveClientImpl implements BinanceApiReactiveClient {

  private final WebClient webClient;
  private final String apiKey;
  private final String secret;

  //TODO: handle errors

  @Override
  public Mono<Long> getServerTime() {
    return webClient.get().uri(SERVER_TIME_ENDPOINT).retrieve().bodyToMono(ServerTime.class).flatMap(st -> Mono.just(st.getServerTime()));
  }

}

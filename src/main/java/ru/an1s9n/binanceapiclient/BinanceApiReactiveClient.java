package ru.an1s9n.binanceapiclient;

import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;

import java.util.List;

public interface BinanceApiReactiveClient {

  Mono<Void> ping();
  Mono<Long> getServerTime();
  Mono<ExchangeInfo> getExchangeInfo();
  Mono<ExchangeInfo> getExchangeInfo(String symbol);
  Mono<ExchangeInfo> getExchangeInfo(List<String> symbols);

}

package ru.an1s9n.binanceapiclient;

import reactor.core.publisher.Mono;

public interface BinanceApiReactiveClient {

  Mono<Long> getServerTime();

}

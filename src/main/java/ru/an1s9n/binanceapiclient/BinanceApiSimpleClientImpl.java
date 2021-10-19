package ru.an1s9n.binanceapiclient;

import lombok.RequiredArgsConstructor;
import ru.an1s9n.binanceapiclient.exception.BinanceApiException;

@RequiredArgsConstructor
public class BinanceApiSimpleClientImpl implements BinanceApiSimpleClient {

  private final BinanceApiReactiveClient reactiveClient;

  @Override
  public Long getServerTime() {
    return reactiveClient.getServerTime().blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

}

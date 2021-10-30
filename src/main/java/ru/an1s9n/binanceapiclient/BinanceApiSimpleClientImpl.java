package ru.an1s9n.binanceapiclient;

import lombok.RequiredArgsConstructor;
import ru.an1s9n.binanceapiclient.exception.BinanceApiException;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;

import java.util.List;

@RequiredArgsConstructor
public class BinanceApiSimpleClientImpl implements BinanceApiSimpleClient {

  private final BinanceApiReactiveClient reactiveClient;

  @Override
  public void ping() {
    reactiveClient.ping().blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

  @Override
  public Long getServerTime() {
    return reactiveClient.getServerTime().blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

  @Override
  public ExchangeInfo getExchangeInfo() {
    return reactiveClient.getExchangeInfo().blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

  @Override
  public ExchangeInfo getExchangeInfo(String symbol) {
    return getExchangeInfo(List.of(symbol));
  }

  @Override
  public ExchangeInfo getExchangeInfo(List<String> symbols) {
    return reactiveClient.getExchangeInfo(symbols).blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

}

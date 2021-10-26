package ru.an1s9n.binanceapiclient;

import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;

public interface BinanceApiSimpleClient {

  void ping();
  Long getServerTime();
  ExchangeInfo getExchangeInfo();

}

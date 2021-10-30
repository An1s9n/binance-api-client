package ru.an1s9n.binanceapiclient;

import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;
import ru.an1s9n.binanceapiclient.model.market.OrderBook;
import ru.an1s9n.binanceapiclient.model.market.TradeItem;

import java.util.List;

public interface BinanceApiSimpleClient {

  void ping();
  Long getServerTime();
  ExchangeInfo getExchangeInfo();
  ExchangeInfo getExchangeInfo(String symbol);
  ExchangeInfo getExchangeInfo(List<String> symbols);
  OrderBook getOrderBook(String symbol, int limit);
  List<TradeItem> getRecentTrades(String symbol, int limit);

}

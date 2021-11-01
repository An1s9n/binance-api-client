package ru.an1s9n.binanceapiclient;

import ru.an1s9n.binanceapiclient.model.market.AggregateTradeItem;
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
  OrderBook getOrderBook(String symbol, Integer limit);
  List<TradeItem> getRecentTrades(String symbol, Integer limit);
  List<TradeItem> getHistoricalTrades(String symbol, Integer limit, Long fromId);
  List<AggregateTradeItem> getAggregateTrades (String symbol, Long fromId, Long startTime, Long endTime, Integer limit);

}

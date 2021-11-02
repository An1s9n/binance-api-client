package ru.an1s9n.binanceapiclient;

import ru.an1s9n.binanceapiclient.model.market.AggregateTradeItem;
import ru.an1s9n.binanceapiclient.model.market.AveragePrice;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;
import ru.an1s9n.binanceapiclient.model.market.Kline;
import ru.an1s9n.binanceapiclient.model.market.KlineInterval;
import ru.an1s9n.binanceapiclient.model.market.OrderBook;
import ru.an1s9n.binanceapiclient.model.market.TickerPrice;
import ru.an1s9n.binanceapiclient.model.market.Ticker24HrStatistics;
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
  List<Kline> getKlines(String symbol, KlineInterval interval, Long startTime, Long endTime, Integer limit);
  AveragePrice getAveragePrice(String symbol);
  List<Ticker24HrStatistics> get24HrTickerStatistics();
  Ticker24HrStatistics get24HrTickerStatistics(String symbol);
  List<TickerPrice> getTickerPrice();
  TickerPrice getTickerPrice(String symbol);

}

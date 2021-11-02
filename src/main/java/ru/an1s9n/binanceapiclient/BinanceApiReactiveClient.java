package ru.an1s9n.binanceapiclient;

import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.model.market.AggregateTradeItem;
import ru.an1s9n.binanceapiclient.model.market.AveragePrice;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;
import ru.an1s9n.binanceapiclient.model.market.Kline;
import ru.an1s9n.binanceapiclient.model.market.KlineInterval;
import ru.an1s9n.binanceapiclient.model.market.OrderBook;
import ru.an1s9n.binanceapiclient.model.market.TickerStatistics;
import ru.an1s9n.binanceapiclient.model.market.TradeItem;

import java.util.List;

public interface BinanceApiReactiveClient {

  Mono<Void> ping();
  Mono<Long> getServerTime();
  Mono<ExchangeInfo> getExchangeInfo();
  Mono<ExchangeInfo> getExchangeInfo(String symbol);
  Mono<ExchangeInfo> getExchangeInfo(List<String> symbols);
  Mono<OrderBook> getOrderBook(String symbol, Integer limit);
  Mono<List<TradeItem>> getRecentTrades(String symbol, Integer limit);
  Mono<List<TradeItem>> getHistoricalTrades(String symbol, Integer limit, Long fromId);
  Mono<List<AggregateTradeItem>> getAggregateTrades (String symbol, Long fromId, Long startTime, Long endTime, Integer limit);
  Mono<List<Kline>> getKlines(String symbol, KlineInterval interval, Long startTime, Long endTime, Integer limit);
  Mono<AveragePrice> getAveragePrice(String symbol);
  Mono<List<TickerStatistics>> get24HrTickerStatistics();
  Mono<TickerStatistics> get24HrTickerStatistics(String symbol);

}

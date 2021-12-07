package ru.an1s9n.binance.api.client;

import reactor.core.publisher.Mono;
import ru.an1s9n.binance.api.client.model.market.AggregateTradeItem;
import ru.an1s9n.binance.api.client.model.market.AveragePrice;
import ru.an1s9n.binance.api.client.model.market.ExchangeInfo;
import ru.an1s9n.binance.api.client.model.market.Kline;
import ru.an1s9n.binance.api.client.model.market.KlineInterval;
import ru.an1s9n.binance.api.client.model.market.OrderBook;
import ru.an1s9n.binance.api.client.model.market.TickerOrderBook;
import ru.an1s9n.binance.api.client.model.market.TickerPrice;
import ru.an1s9n.binance.api.client.model.market.Ticker24HrStatistics;
import ru.an1s9n.binance.api.client.model.market.TradeItem;

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
  Mono<List<Ticker24HrStatistics>> get24HrTickerStatistics();
  Mono<Ticker24HrStatistics> get24HrTickerStatistics(String symbol);
  Mono<List<TickerPrice>> getTickerPrice();
  Mono<TickerPrice> getTickerPrice(String symbol);
  Mono<List<TickerOrderBook>> getTickerOrderBook();
  Mono<TickerOrderBook> getTickerOrderBook(String symbol);

}

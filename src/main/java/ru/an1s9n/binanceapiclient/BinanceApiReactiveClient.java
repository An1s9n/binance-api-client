package ru.an1s9n.binanceapiclient;

import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;
import ru.an1s9n.binanceapiclient.model.market.OrderBook;
import ru.an1s9n.binanceapiclient.model.market.TradeItem;

import java.util.List;

public interface BinanceApiReactiveClient {

  Mono<Void> ping();
  Mono<Long> getServerTime();
  Mono<ExchangeInfo> getExchangeInfo();
  Mono<ExchangeInfo> getExchangeInfo(String symbol);
  Mono<ExchangeInfo> getExchangeInfo(List<String> symbols);
  Mono<OrderBook> getOrderBook(String symbol, int limit);
  Mono<List<TradeItem>> getRecentTrades(String symbol, int limit);
  Mono<List<TradeItem>> getHistoricalTrades(String symbol, int limit, long fromId);
  Mono<List<TradeItem>> getAggregateTrades (String symbol, long fromId, long startTime, long endTime, int limit);

}

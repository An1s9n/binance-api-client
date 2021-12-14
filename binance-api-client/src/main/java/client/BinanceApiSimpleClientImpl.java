package client;

import lombok.RequiredArgsConstructor;
import ru.an1s9n.binance.api.client.model.market.AggregateTradeItem;
import ru.an1s9n.binance.api.client.model.market.AveragePrice;
import ru.an1s9n.binance.api.client.model.market.ExchangeInfo;
import ru.an1s9n.binance.api.client.model.market.Kline;
import ru.an1s9n.binance.api.client.model.market.KlineInterval;
import ru.an1s9n.binance.api.client.model.market.OrderBook;
import ru.an1s9n.binance.api.client.model.market.Ticker24HrStatistics;
import ru.an1s9n.binance.api.client.model.market.TickerOrderBook;
import ru.an1s9n.binance.api.client.model.market.TickerPrice;
import ru.an1s9n.binance.api.client.model.market.TradeItem;

import java.util.List;

@RequiredArgsConstructor
public class BinanceApiSimpleClientImpl implements BinanceApiSimpleClient {

  private final BinanceApiReactiveClient reactiveClient;

  @Override
  public void ping() {
    reactiveClient.ping().block();
  }

  @Override
  public Long getServerTime() {
    return reactiveClient.getServerTime().block();
  }

  @Override
  public ExchangeInfo getExchangeInfo() {
    return reactiveClient.getExchangeInfo().block();
  }

  @Override
  public ExchangeInfo getExchangeInfo(String symbol) {
    return getExchangeInfo(List.of(symbol));
  }

  @Override
  public ExchangeInfo getExchangeInfo(List<String> symbols) {
    return reactiveClient.getExchangeInfo(symbols).block();
  }

  @Override
  public OrderBook getOrderBook(String symbol, Integer limit) {
    return reactiveClient.getOrderBook(symbol, limit).block();
  }

  @Override
  public List<TradeItem> getRecentTrades(String symbol, Integer limit) {
    return reactiveClient.getRecentTrades(symbol, limit).block();
  }

  @Override
  public List<TradeItem> getHistoricalTrades(String symbol, Integer limit, Long fromId) {
    return reactiveClient.getHistoricalTrades(symbol, limit, fromId).block();
  }

  @Override
  public List<AggregateTradeItem> getAggregateTrades(String symbol, Long fromId, Long startTime, Long endTime, Integer limit) {
    return reactiveClient.getAggregateTrades(symbol, fromId, startTime, endTime, limit).block();
  }

  @Override
  public List<Kline> getKlines(String symbol, KlineInterval interval, Long startTime, Long endTime, Integer limit) {
    return reactiveClient.getKlines(symbol, interval, startTime, endTime, limit).block();
  }

  @Override
  public AveragePrice getAveragePrice(String symbol) {
    return reactiveClient.getAveragePrice(symbol).block();
  }

  @Override
  public List<Ticker24HrStatistics> get24HrTickerStatistics() {
    return reactiveClient.get24HrTickerStatistics().block();
  }

  @Override
  public Ticker24HrStatistics get24HrTickerStatistics(String symbol) {
    return reactiveClient.get24HrTickerStatistics(symbol).block();
  }

  @Override
  public List<TickerPrice> getTickerPrice() {
    return reactiveClient.getTickerPrice().block();
  }

  @Override
  public TickerPrice getTickerPrice(String symbol) {
    return reactiveClient.getTickerPrice(symbol).block();
  }

  @Override
  public List<TickerOrderBook> getTickerOrderBook() {
    return reactiveClient.getTickerOrderBook().block();
  }

  @Override
  public TickerOrderBook getTickerOrderBook(String symbol) {
    return reactiveClient.getTickerOrderBook(symbol).block();
  }

}

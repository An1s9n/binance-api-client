package ru.an1s9n.binanceapiclient;

import lombok.RequiredArgsConstructor;
import ru.an1s9n.binanceapiclient.exception.BinanceApiException;
import ru.an1s9n.binanceapiclient.model.market.AggregateTradeItem;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;
import ru.an1s9n.binanceapiclient.model.market.OrderBook;
import ru.an1s9n.binanceapiclient.model.market.TradeItem;

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

  @Override
  public OrderBook getOrderBook(String symbol, Integer limit) {
    return reactiveClient.getOrderBook(symbol, limit).blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

  @Override
  public List<TradeItem> getRecentTrades(String symbol, Integer limit) {
    return reactiveClient.getRecentTrades(symbol, limit).blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

  @Override
  public List<TradeItem> getHistoricalTrades(String symbol, Integer limit, Long fromId) {
    return reactiveClient.getHistoricalTrades(symbol, limit, fromId).blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

  @Override
  public List<AggregateTradeItem> getAggregateTrades(String symbol, Long fromId, Long startTime, Long endTime, Integer limit) {
    return reactiveClient.getAggregateTrades(symbol, fromId, startTime, endTime, limit).blockOptional().orElseThrow(() -> new BinanceApiException(0, "something went wrong"));
  }

}

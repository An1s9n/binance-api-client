package ru.an1s9n.binance.api.client;

import ru.an1s9n.binance.api.client.model.market.KlineInterval;
import ru.an1s9n.binance.api.client.model.websocket.AggregateTradeEvent;
import ru.an1s9n.binance.api.client.model.websocket.BookTickerEvent;
import ru.an1s9n.binance.api.client.model.websocket.Depth;
import ru.an1s9n.binance.api.client.model.websocket.DepthUpdateEvent;
import ru.an1s9n.binance.api.client.model.websocket.KlineEvent;
import ru.an1s9n.binance.api.client.model.websocket.MiniTicker24HrEvent;
import ru.an1s9n.binance.api.client.model.websocket.PartialBookDepthEvent;
import ru.an1s9n.binance.api.client.model.websocket.Ticker24HrEvent;
import ru.an1s9n.binance.api.client.model.websocket.TradeEvent;
import ru.an1s9n.binance.api.client.model.websocket.UpdateSpeed;
import ru.an1s9n.binance.api.client.websocket.WebSocketSessionFacade;

import java.util.List;
import java.util.function.Consumer;

public interface BinanceApiWebSocketClient {

  WebSocketSessionFacade aggregateTrades(List<String> symbols, Consumer<? super AggregateTradeEvent> onEvent);
  WebSocketSessionFacade aggregateTrades(String symbol, Consumer<? super AggregateTradeEvent> onEvent);
  WebSocketSessionFacade trades(List<String> symbols, Consumer<? super TradeEvent> onEvent);
  WebSocketSessionFacade trades(String symbol, Consumer<? super TradeEvent> onEvent);
  WebSocketSessionFacade klines(List<String> symbols, KlineInterval klineInterval, Consumer<? super KlineEvent> onEvent);
  WebSocketSessionFacade klines(String symbol, KlineInterval klineInterval, Consumer<? super KlineEvent> onEvent);
  WebSocketSessionFacade miniTicker24Hr(List<String> symbols, Consumer<? super MiniTicker24HrEvent> onEvent);
  WebSocketSessionFacade miniTicker24Hr(String symbol, Consumer<? super MiniTicker24HrEvent> onEvent);
  WebSocketSessionFacade miniTicker24Hr(Consumer<? super MiniTicker24HrEvent> onEvent);
  WebSocketSessionFacade ticker24Hr(List<String> symbols, Consumer<? super Ticker24HrEvent> onEvent);
  WebSocketSessionFacade ticker24Hr(String symbol, Consumer<? super Ticker24HrEvent> onEvent);
  WebSocketSessionFacade ticker24Hr(Consumer<? super Ticker24HrEvent> onEvent);
  WebSocketSessionFacade bookTicker(List<String> symbols, Consumer<? super BookTickerEvent> onEvent);
  WebSocketSessionFacade bookTicker(String symbol, Consumer<? super BookTickerEvent> onEvent);
  WebSocketSessionFacade bookTicker(Consumer<? super BookTickerEvent> onEvent);
  WebSocketSessionFacade partialBookDepth(List<String> symbols, Depth depth, UpdateSpeed updateSpeed, Consumer<? super PartialBookDepthEvent> onEvent);
  WebSocketSessionFacade partialBookDepth(String symbol, Depth depth, UpdateSpeed updateSpeed, Consumer<? super PartialBookDepthEvent> onEvent);
  WebSocketSessionFacade depthUpdates(List<String> symbols, UpdateSpeed updateSpeed, Consumer<? super DepthUpdateEvent> onEvent);
  WebSocketSessionFacade depthUpdates(String symbol, UpdateSpeed updateSpeed, Consumer<? super DepthUpdateEvent> onEvent);

}

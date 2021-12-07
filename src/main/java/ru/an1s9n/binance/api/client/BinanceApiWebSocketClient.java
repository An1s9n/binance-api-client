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

  WebSocketSessionFacade getAggregateTrades(List<String> symbols, Consumer<? super AggregateTradeEvent> onEvent);
  WebSocketSessionFacade getAggregateTrades(String symbol, Consumer<? super AggregateTradeEvent> onEvent);
  WebSocketSessionFacade getTrades(List<String> symbols, Consumer<? super TradeEvent> onEvent);
  WebSocketSessionFacade getTrades(String symbol, Consumer<? super TradeEvent> onEvent);
  WebSocketSessionFacade getKlines(List<String> symbols, KlineInterval klineInterval, Consumer<? super KlineEvent> onEvent);
  WebSocketSessionFacade getKlines(String symbol, KlineInterval klineInterval, Consumer<? super KlineEvent> onEvent);
  WebSocketSessionFacade getMiniTicker24Hr(List<String> symbols, Consumer<? super MiniTicker24HrEvent> onEvent);
  WebSocketSessionFacade getMiniTicker24Hr(String symbol, Consumer<? super MiniTicker24HrEvent> onEvent);
  WebSocketSessionFacade getMiniTicker24Hr(Consumer<? super MiniTicker24HrEvent> onEvent);
  WebSocketSessionFacade getTicker24Hr(List<String> symbols, Consumer<? super Ticker24HrEvent> onEvent);
  WebSocketSessionFacade getTicker24Hr(String symbol, Consumer<? super Ticker24HrEvent> onEvent);
  WebSocketSessionFacade getTicker24Hr(Consumer<? super Ticker24HrEvent> onEvent);
  WebSocketSessionFacade getBookTicker(List<String> symbols, Consumer<? super BookTickerEvent> onEvent);
  WebSocketSessionFacade getBookTicker(String symbol, Consumer<? super BookTickerEvent> onEvent);
  WebSocketSessionFacade getBookTicker(Consumer<? super BookTickerEvent> onEvent);
  WebSocketSessionFacade getPartialBookDepth(List<String> symbols, Depth depth, UpdateSpeed updateSpeed, Consumer<? super PartialBookDepthEvent> onEvent);
  WebSocketSessionFacade getPartialBookDepth(String symbol, Depth depth, UpdateSpeed updateSpeed, Consumer<? super PartialBookDepthEvent> onEvent);
  WebSocketSessionFacade getDepthUpdates(List<String> symbols, UpdateSpeed updateSpeed, Consumer<? super DepthUpdateEvent> onEvent);
  WebSocketSessionFacade getDepthUpdates(String symbol, UpdateSpeed updateSpeed, Consumer<? super DepthUpdateEvent> onEvent);

}

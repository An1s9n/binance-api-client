package ru.an1s9n.binanceapiclient;

import ru.an1s9n.binanceapiclient.model.websocket.AggregateTradeEvent;
import ru.an1s9n.binanceapiclient.model.websocket.TradeEvent;
import ru.an1s9n.binanceapiclient.websocket.WebSocketSessionFacade;

import java.util.List;
import java.util.function.Consumer;

public interface BinanceApiWebSocketClient {

  WebSocketSessionFacade getAggregateTrades(List<String> symbols, Consumer<? super AggregateTradeEvent> onEvent);
  WebSocketSessionFacade getAggregateTrades(String symbol, Consumer<? super AggregateTradeEvent> onEvent);
  WebSocketSessionFacade getTrades(List<String> symbols, Consumer<? super TradeEvent> onEvent);
  WebSocketSessionFacade getTrades(String symbol, Consumer<? super TradeEvent> onEvent);

}

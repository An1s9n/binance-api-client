package ru.an1s9n.binanceapiclient;

import ru.an1s9n.binanceapiclient.model.websocket.AggregateTradeEvent;
import ru.an1s9n.binanceapiclient.websocket.WebSocketSessionFacade;

import java.util.function.Consumer;

public interface BinanceApiWebSocketClient {

  WebSocketSessionFacade getAggregateTrades(String symbol, Consumer<? super AggregateTradeEvent> onEvent);

}

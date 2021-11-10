package ru.an1s9n.binanceapiclient;

import ru.an1s9n.binanceapiclient.model.websocket.AggregateTradeEvent;

import java.util.function.Consumer;

public interface BinanceApiWebSocketClient {

  void getAggregateTrades(String symbol, Consumer<? super AggregateTradeEvent> onEvent);

}

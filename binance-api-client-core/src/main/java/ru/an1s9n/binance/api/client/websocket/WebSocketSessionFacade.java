package ru.an1s9n.binance.api.client.websocket;

public interface WebSocketSessionFacade {
  void close();
  boolean isOpen();
}

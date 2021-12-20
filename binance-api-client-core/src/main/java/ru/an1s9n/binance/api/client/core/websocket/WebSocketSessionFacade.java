package ru.an1s9n.binance.api.client.core.websocket;

public interface WebSocketSessionFacade {
  void close();
  boolean isOpen();
}

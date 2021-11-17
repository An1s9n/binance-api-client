package ru.an1s9n.binanceapiclient.websocket;

public interface WebSocketSessionFacade {
  void close();
  boolean isOpen();
}

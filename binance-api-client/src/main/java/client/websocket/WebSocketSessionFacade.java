package client.websocket;

public interface WebSocketSessionFacade {
  void close();
  boolean isOpen();
}

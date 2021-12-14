package ru.an1s9n.binance.api.client.websocket;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.reactive.socket.WebSocketSession;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class WebSocketSessionFacadeImpl implements WebSocketSessionFacade {

  private final Map<UUID, WebSocketSession> sessions;
  private final UUID sessionUuid;

  //TODO: if webSocketSession is null maybe it's worth adding a couple of retries
  @Override
  public void close() {
    final var webSocketSession = sessions.get(sessionUuid);
    if(webSocketSession != null) {
      webSocketSession.close().block();
    } else {
      log.warn("Session with UUID {} is not found, unable to close it", sessionUuid);
    }
  }

  @Override
  public boolean isOpen() {
    final var webSocketSession = sessions.get(sessionUuid);
    return webSocketSession != null && webSocketSession.isOpen();
  }

}

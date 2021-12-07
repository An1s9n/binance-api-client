package ru.an1s9n.binance.api.client.model.websocket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UpdateSpeed {
  HUNDRED_MS("100ms"),
  ONE_SECOND("1000ms");

  private final String id;
}

package ru.an1s9n.binance.api.client.core.model.websocket;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Depth {
  FIVE("5"),
  TEN("10"),
  TWENTY("20");

  private final String depthLevel;
}

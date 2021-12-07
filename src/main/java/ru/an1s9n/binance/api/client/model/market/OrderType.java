package ru.an1s9n.binance.api.client.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum OrderType {
  LIMIT,
  MARKET,
  STOP_LOSS,
  STOP_LOSS_LIMIT,
  TAKE_PROFIT,
  TAKE_PROFIT_LIMIT,
  LIMIT_MAKER,
  @JsonEnumDefaultValue UNKNOWN
}

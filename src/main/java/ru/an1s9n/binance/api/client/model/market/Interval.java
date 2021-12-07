package ru.an1s9n.binance.api.client.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Interval {
  SECOND,
  MINUTE,
  DAY,
  @JsonEnumDefaultValue UNKNOWN
}

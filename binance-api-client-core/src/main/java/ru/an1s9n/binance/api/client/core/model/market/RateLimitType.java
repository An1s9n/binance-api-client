package ru.an1s9n.binance.api.client.core.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum RateLimitType {
  REQUEST_WEIGHT,
  ORDERS,
  RAW_REQUESTS,
  @JsonEnumDefaultValue UNKNOWN
}

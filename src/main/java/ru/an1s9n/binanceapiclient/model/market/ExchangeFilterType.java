package ru.an1s9n.binanceapiclient.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum ExchangeFilterType {
  EXCHANGE_MAX_NUM_ORDERS,
  EXCHANGE_MAX_ALGO_ORDERS,
  @JsonEnumDefaultValue UNKNOWN
}
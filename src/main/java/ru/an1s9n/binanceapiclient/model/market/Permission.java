package ru.an1s9n.binanceapiclient.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Permission {
  SPOT,
  MARGIN,
  @JsonEnumDefaultValue UNKNOWN
}

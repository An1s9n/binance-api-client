package ru.an1s9n.binanceapiclient.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum SymbolStatus {
  PRE_TRADING,
  TRADING,
  POST_TRADING,
  END_OF_DAY,
  HALT,
  AUCTION_MATCH,
  BREAK,
  @JsonEnumDefaultValue UNKNOWN
}

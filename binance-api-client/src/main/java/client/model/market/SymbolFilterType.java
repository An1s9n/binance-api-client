package client.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum SymbolFilterType {
  PRICE_FILTER,
  PERCENT_PRICE,
  LOT_SIZE,
  MIN_NOTIONAL,
  ICEBERG_PARTS,
  MARKET_LOT_SIZE,
  MAX_NUM_ORDERS,
  MAX_NUM_ALGO_ORDERS,
  MAX_NUM_ICEBERG_ORDERS,
  MAX_POSITION,
  @JsonEnumDefaultValue UNKNOWN
}

package client.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum RateLimitType {
  REQUEST_WEIGHT,
  ORDERS,
  RAW_REQUESTS,
  @JsonEnumDefaultValue UNKNOWN
}

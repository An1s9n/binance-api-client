package client.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;

public enum Permission {
  SPOT,
  MARGIN,
  @JsonEnumDefaultValue UNKNOWN
}

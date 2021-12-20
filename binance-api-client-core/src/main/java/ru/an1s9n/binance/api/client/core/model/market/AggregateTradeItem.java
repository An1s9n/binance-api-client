package ru.an1s9n.binance.api.client.core.model.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AggregateTradeItem {
  @JsonProperty("a") private Long id;
  @JsonProperty("p") private BigDecimal price;
  @JsonProperty("q") private BigDecimal qty;
  @JsonProperty("f") private Long firstTradeId;
  @JsonProperty("l") private Long lastTradeId;
  @JsonProperty("T") private Long time;
  @JsonProperty("m") private Boolean isBuyerMaker;
  @JsonProperty("M") private Boolean isBestMatch;
}

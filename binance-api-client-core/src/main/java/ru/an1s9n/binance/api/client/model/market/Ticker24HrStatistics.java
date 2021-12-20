package ru.an1s9n.binance.api.client.model.market;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Ticker24HrStatistics {
  private String symbol;
  private BigDecimal priceChange;
  private BigDecimal priceChangePercent;
  private BigDecimal weightedAvgPrice;
  private BigDecimal prevClosePrice;
  private BigDecimal lastPrice;
  private BigDecimal lastQty;
  private BigDecimal bidPrice;
  private BigDecimal bidQty;
  private BigDecimal askPrice;
  private BigDecimal askQty;
  private BigDecimal openPrice;
  private BigDecimal highPrice;
  private BigDecimal lowPrice;
  private BigDecimal volume;
  private BigDecimal quoteVolume;
  private Long openTime;
  private Long closeTime;
  @JsonProperty("firstId") private Long firstTradeId;
  @JsonProperty("lastId") private Long lastTradeId;
  @JsonProperty("count") private Long tradeCount;
}

package ru.an1s9n.binanceapiclient.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.ALL_MARKET_TICKER_24_HR_STREAM_NAME;
import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.INDIVIDUAL_SYMBOL_TICKER_24_HR_STREAM_NAME;

@Data
public class Ticker24HrEvent {

  public static final String STREAM_NAME = INDIVIDUAL_SYMBOL_TICKER_24_HR_STREAM_NAME;
  public static final String ALL_MARKET_STREAM_NAME = ALL_MARKET_TICKER_24_HR_STREAM_NAME;

  @JsonProperty("e") private String eventType;
  @JsonProperty("E") private Long eventTime;
  @JsonProperty("s") private String symbol;
  @JsonProperty("p") private BigDecimal priceChange;
  @JsonProperty("P") private BigDecimal priceChangePercent;
  @JsonProperty("w") private BigDecimal weightedAveragePrice;
  @JsonProperty("x") private BigDecimal firstTradeBeforePeriodPrice;
  @JsonProperty("c") private BigDecimal lastPrice;
  @JsonProperty("Q") private BigDecimal lastQuantity;
  @JsonProperty("b") private BigDecimal bestBidPrice;
  @JsonProperty("B") private BigDecimal bestBidQuantity;
  @JsonProperty("a") private BigDecimal bestAskPrice;
  @JsonProperty("A") private BigDecimal bestAskQuantity;
  @JsonProperty("o") private BigDecimal openPrice;
  @JsonProperty("h") private BigDecimal highPrice;
  @JsonProperty("l") private BigDecimal lowPrice;
  @JsonProperty("v") private BigDecimal totalTradedBaseAssetVolume;
  @JsonProperty("q") private BigDecimal totalTradedQuoteAssetVolume;
  @JsonProperty("O") private Long statisticsOpenTime;
  @JsonProperty("C") private Long statisticsCloseTime;
  @JsonProperty("F") private Long firstTradeId;
  @JsonProperty("L") private Long lastTradeId;
  @JsonProperty("n") private Long totalNumberOfTrades;
}

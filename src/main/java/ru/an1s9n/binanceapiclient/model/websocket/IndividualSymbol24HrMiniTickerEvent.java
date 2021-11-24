package ru.an1s9n.binanceapiclient.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.INDIVIDUAL_SYMBOL_24_HR_MINI_TICKER_STREAM_NAME;

@Data
public class IndividualSymbol24HrMiniTickerEvent {

  public static final String STREAM_NAME = INDIVIDUAL_SYMBOL_24_HR_MINI_TICKER_STREAM_NAME;

  @JsonProperty("e") private String eventType;
  @JsonProperty("E") private Long eventTime;
  @JsonProperty("s") private String symbol;
  @JsonProperty("c") private BigDecimal closePrice;
  @JsonProperty("o") private BigDecimal openPrice;
  @JsonProperty("h") private BigDecimal highPrice;
  @JsonProperty("l") private BigDecimal lowPrice;
  @JsonProperty("v") private BigDecimal totalTradedBaseAssetVolume;
  @JsonProperty("q") private BigDecimal totalTradedQuoteAssetVolume;
}

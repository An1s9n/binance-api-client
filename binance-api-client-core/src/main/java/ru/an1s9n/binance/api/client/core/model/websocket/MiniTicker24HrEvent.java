package ru.an1s9n.binance.api.client.core.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.an1s9n.binance.api.client.core.config.BinanceApiConfig;

import java.math.BigDecimal;

@Data
public class MiniTicker24HrEvent {

  public static final String STREAM_NAME = BinanceApiConfig.Endpoints.INDIVIDUAL_SYMBOL_MINI_TICKER_24_HR_STREAM_NAME;
  public static final String ALL_MARKET_STREAM_NAME = BinanceApiConfig.Endpoints.ALL_MARKET_MINI_TICKER_24_HR_STREAM_NAME;

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

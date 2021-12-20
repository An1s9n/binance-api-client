package ru.an1s9n.binance.api.client.core.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.an1s9n.binance.api.client.core.config.BinanceApiConfig;

import java.math.BigDecimal;

@Data
public class BookTickerEvent {

  public static final String STREAM_NAME = BinanceApiConfig.Endpoints.INDIVIDUAL_SYMBOL_BOOK_TICKER_STREAM_NAME;
  public static final String ALL_MARKET_STREAM_NAME = BinanceApiConfig.Endpoints.ALL_MARKET_BOOK_TICKER_STREAM_NAME;

  @JsonProperty("u") private Long orderBookUpdateId;
  @JsonProperty("s") private String symbol;
  @JsonProperty("b") private BigDecimal bestBidPrice;
  @JsonProperty("B") private BigDecimal bestBidQty;
  @JsonProperty("a") private BigDecimal bestAskPrice;
  @JsonProperty("A") private BigDecimal bestAskQty;
}

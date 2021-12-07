package ru.an1s9n.binance.api.client.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

import static ru.an1s9n.binance.api.client.config.BinanceApiConfig.Endpoints.ALL_MARKET_BOOK_TICKER_STREAM_NAME;
import static ru.an1s9n.binance.api.client.config.BinanceApiConfig.Endpoints.INDIVIDUAL_SYMBOL_BOOK_TICKER_STREAM_NAME;

@Data
public class BookTickerEvent {

  public static final String STREAM_NAME = INDIVIDUAL_SYMBOL_BOOK_TICKER_STREAM_NAME;
  public static final String ALL_MARKET_STREAM_NAME = ALL_MARKET_BOOK_TICKER_STREAM_NAME;

  @JsonProperty("u") private Long orderBookUpdateId;
  @JsonProperty("s") private String symbol;
  @JsonProperty("b") private BigDecimal bestBidPrice;
  @JsonProperty("B") private BigDecimal bestBidQty;
  @JsonProperty("a") private BigDecimal bestAskPrice;
  @JsonProperty("A") private BigDecimal bestAskQty;
}

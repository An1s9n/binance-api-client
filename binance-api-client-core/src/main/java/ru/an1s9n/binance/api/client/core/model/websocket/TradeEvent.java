package ru.an1s9n.binance.api.client.core.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.an1s9n.binance.api.client.core.config.BinanceApiConfig;

import java.math.BigDecimal;

@Data
public class TradeEvent {

  public static final String STREAM_NAME = BinanceApiConfig.Endpoints.TRADE_STREAM_NAME;

  @JsonProperty("e") private String eventType;
  @JsonProperty("E") private Long eventTime;
  @JsonProperty("s") private String symbol;
  @JsonProperty("t") private Long tradeId;
  @JsonProperty("p") private BigDecimal price;
  @JsonProperty("q") private BigDecimal quantity;
  @JsonProperty("b") private Long buyerOrderId;
  @JsonProperty("a") private Long sellerOrderId;
  @JsonProperty("T") private Long tradeTime;
  @JsonProperty("m") private Boolean isBuyerMaker;
}

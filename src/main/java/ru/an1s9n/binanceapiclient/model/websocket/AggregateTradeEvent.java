package ru.an1s9n.binanceapiclient.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.AGGREGATE_TRADE_STREAM_NAME;

@Data
public class AggregateTradeEvent {

  public static final String STREAM_NAME = AGGREGATE_TRADE_STREAM_NAME;

  @JsonProperty("e") private String eventType;
  @JsonProperty("E") private Long  eventTime;
  @JsonProperty("s") private String symbol;
  @JsonProperty("a") private Long aggregateTradeId;
  @JsonProperty("p") private BigDecimal price;
  @JsonProperty("q") private BigDecimal quantity;
  @JsonProperty("f") private Long firstTradeId;
  @JsonProperty("l") private Long lastTradeId;
  @JsonProperty("T") private Long tradeTime;
  @JsonProperty("m") private Boolean isBuyerMaker;
}

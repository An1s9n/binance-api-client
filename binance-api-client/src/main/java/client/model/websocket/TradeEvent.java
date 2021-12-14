package client.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

import static ru.an1s9n.binance.api.client.config.BinanceApiConfig.Endpoints.TRADE_STREAM_NAME;

@Data
public class TradeEvent {

  public static final String STREAM_NAME = TRADE_STREAM_NAME;

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

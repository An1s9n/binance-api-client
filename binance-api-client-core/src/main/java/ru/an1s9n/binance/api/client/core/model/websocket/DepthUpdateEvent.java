package ru.an1s9n.binance.api.client.core.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.an1s9n.binance.api.client.core.config.BinanceApiConfig;
import ru.an1s9n.binance.api.client.core.model.market.OrderBookEntry;

import java.util.List;

@Data
public class DepthUpdateEvent {

  public static final String STREAM_NAME = BinanceApiConfig.Endpoints.DIFF_DEPTH_STREAM_NAME;

  @JsonProperty("e") private String eventType;
  @JsonProperty("E") private Long eventTime;
  @JsonProperty("s") private String symbol;
  @JsonProperty("U") private Long firstUpdateIdInEvent;
  @JsonProperty("u") private Long finalUpdateIdInEvent;
  @JsonProperty("b") private List<OrderBookEntry> bidsToBeUpdated;
  @JsonProperty("a") private List<OrderBookEntry> asksToBeUpdated;
}

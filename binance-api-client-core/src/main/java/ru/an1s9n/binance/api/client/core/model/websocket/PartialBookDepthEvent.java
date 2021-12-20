package ru.an1s9n.binance.api.client.core.model.websocket;

import lombok.Data;
import ru.an1s9n.binance.api.client.core.config.BinanceApiConfig;
import ru.an1s9n.binance.api.client.core.model.market.OrderBookEntry;

import java.util.List;

@Data
public class PartialBookDepthEvent {

  public static final String STREAM_NAME = BinanceApiConfig.Endpoints.PARTIAL_BOOK_DEPTH_STREAM_NAME;

  private Long lastUpdateId;
  private List<OrderBookEntry> bids;
  private List<OrderBookEntry> asks;
}

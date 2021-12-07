package ru.an1s9n.binanceapiclient.model.websocket;

import lombok.Data;
import ru.an1s9n.binanceapiclient.model.market.OrderBookEntry;

import java.util.List;

import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.PARTIAL_BOOK_DEPTH_STREAM_NAME;

@Data
public class PartialBookDepthEvent {

  public static final String STREAM_NAME = PARTIAL_BOOK_DEPTH_STREAM_NAME;

  private Long lastUpdateId;
  private List<OrderBookEntry> bids;
  private List<OrderBookEntry> asks;
}
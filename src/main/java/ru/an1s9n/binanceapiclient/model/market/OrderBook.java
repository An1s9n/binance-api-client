package ru.an1s9n.binanceapiclient.model.market;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderBook {

  private Long lastUpdateId;
  private List<OrderBookEntry> bids;
  private List<OrderBookEntry> asks;

  @JsonSetter
  public void setBids(List<List<String>> rawBids) {
    this.bids = new ArrayList<>();
    rawBids.forEach(rawEntry -> this.bids.add(orderBookEntryOf(rawEntry)));
  }

  @JsonSetter
  public void setAsks(List<List<String>> rawAsks) {
    this.asks = new ArrayList<>();
    rawAsks.forEach(rawEntry -> this.asks.add(orderBookEntryOf(rawEntry)));
  }

  private OrderBookEntry orderBookEntryOf(List<String> rawOrderBookEntry) {
    final var orderBookEntry = new OrderBookEntry();
    orderBookEntry.setPrice(new BigDecimal(rawOrderBookEntry.get(0)));
    orderBookEntry.setQty(new BigDecimal(rawOrderBookEntry.get(1)));
    return orderBookEntry;
  }

}

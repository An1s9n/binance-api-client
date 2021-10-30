package ru.an1s9n.binanceapiclient.model.market;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderBookEntry {
  private BigDecimal price;
  private BigDecimal qty;
}

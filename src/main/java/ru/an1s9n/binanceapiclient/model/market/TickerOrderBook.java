package ru.an1s9n.binanceapiclient.model.market;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TickerOrderBook {
  private String symbol;
  private BigDecimal bidPrice;
  private BigDecimal bidQty;
  private BigDecimal askPrice;
  private BigDecimal askQty;
}

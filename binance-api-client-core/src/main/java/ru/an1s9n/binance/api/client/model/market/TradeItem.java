package ru.an1s9n.binance.api.client.model.market;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TradeItem {
  private Long id;
  private BigDecimal price;
  private BigDecimal qty;
  private BigDecimal quoteQty;
  private Long time;
  private Boolean isBuyerMaker;
  private Boolean isBestMatch;
}

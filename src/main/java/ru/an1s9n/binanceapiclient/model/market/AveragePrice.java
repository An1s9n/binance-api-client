package ru.an1s9n.binanceapiclient.model.market;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AveragePrice {
  private Integer mins;
  private BigDecimal price;
}

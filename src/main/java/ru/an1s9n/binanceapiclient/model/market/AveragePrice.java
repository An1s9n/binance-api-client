package ru.an1s9n.binanceapiclient.model.market;

import lombok.Data;

import java.math.BigInteger;

@Data
public class AveragePrice {
  private Integer mins;
  private BigInteger price;
}

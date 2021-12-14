package ru.an1s9n.binance.api.client.model.market;

import lombok.Data;

@Data
public class ExchangeFilter {
  private ExchangeFilterType filterType;
  private Integer maxNumOrders;
  private Integer maxNumAlgoOrders;
}
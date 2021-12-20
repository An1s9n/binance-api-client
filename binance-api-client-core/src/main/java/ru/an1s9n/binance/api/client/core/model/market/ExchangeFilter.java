package ru.an1s9n.binance.api.client.core.model.market;

import lombok.Data;

@Data
public class ExchangeFilter {
  private ExchangeFilterType filterType;
  private Integer maxNumOrders;
  private Integer maxNumAlgoOrders;
}

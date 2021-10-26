package ru.an1s9n.binanceapiclient.model.market;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeInfo {
  private String timeZone;
  private Long serverTime;
  private List<RateLimit> rateLimits;
  private List<ExchangeFilter> exchangeFilters;
  private List<Symbol> symbols;
}

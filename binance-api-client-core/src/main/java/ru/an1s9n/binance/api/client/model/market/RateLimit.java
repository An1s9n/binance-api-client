package ru.an1s9n.binance.api.client.model.market;

import lombok.Data;

@Data
public class RateLimit {
  private RateLimitType rateLimitType;
  private Interval interval;
  private Integer intervalNum;
  private Integer limit;
}

package ru.an1s9n.binanceapiclient.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinanceApiConfig {

  private static final String BASE_DOMAIN = "binance.com";

  public static String apiBaseUrl() {
    return "https://api.%s".formatted(BASE_DOMAIN);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Endpoints {
    public static final String PING_ENDPOINT = "/api/v3/ping";
    public static final String SERVER_TIME_ENDPOINT = "/api/v3/time";
    public static final String EXCHANGE_INFO_ENDPOINT = "/api/v3/exchangeInfo";
    public static final String ORDER_BOOK_ENDPOINT = "/api/v3/depth";
    public static final String RECENT_TRADES_ENDPOINT = "/api/v3/trades";
  }

}

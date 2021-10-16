package ru.an1s9n.binanceapiclient.model.config;

public class BinanceApiConfig {

  private static final String BASE_DOMAIN = "binance.com";

  public static String apiBaseUrl() {
    return "https://api.%s".formatted(BASE_DOMAIN);
  }

}

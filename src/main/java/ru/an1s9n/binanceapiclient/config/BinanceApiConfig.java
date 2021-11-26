package ru.an1s9n.binanceapiclient.config;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BinanceApiConfig {

  public static final String BASE_DOMAIN = "binance.com";
  public static final String API_KEY_HEADER = "X-MBX-APIKEY";

  public static String apiBaseUrl() {
    return "https://api.%s".formatted(BASE_DOMAIN);
  }

  public static String apiWebSocketUrl() {
    return "wss://stream.%s:9443".formatted(BASE_DOMAIN);
  }

  @NoArgsConstructor(access = AccessLevel.PRIVATE)
  public static class Endpoints {
    public static final String PING_ENDPOINT = "/api/v3/ping";
    public static final String SERVER_TIME_ENDPOINT = "/api/v3/time";
    public static final String EXCHANGE_INFO_ENDPOINT = "/api/v3/exchangeInfo";
    public static final String ORDER_BOOK_ENDPOINT = "/api/v3/depth";
    public static final String RECENT_TRADES_ENDPOINT = "/api/v3/trades";
    public static final String HISTORICAL_TRADES_ENDPOINT = "/api/v3/historicalTrades";
    public static final String AGGREGATE_TRADES_ENDPOINT = "/api/v3/aggTrades";
    public static final String KLINES_ENDPOINT = "/api/v3/klines";
    public static final String AVERAGE_PRICE_ENDPOINT = "/api/v3/avgPrice";
    public static final String TICKER_24HR_STATISTICS_ENDPOINT = "/api/v3/ticker/24hr";
    public static final String TICKER_PRICE_ENDPOINT = "/api/v3/ticker/price";
    public static final String TICKER_ORDER_BOOK_ENDPOINT = "/api/v3/ticker/bookTicker";

    public static final String RAW_WEB_SOCKET_STREAM_ENDPOINT = "/ws";
    public static final String AGGREGATE_TRADE_STREAM_NAME = "%s@aggTrade";
    public static final String TRADE_STREAM_NAME = "%s@trade";
    public static final String KLINE_STREAM_NAME = "%s@kline_%s";
    public static final String INDIVIDUAL_SYMBOL_MINI_TICKER_24_HR_STREAM_NAME = "%s@miniTicker";
  }

}

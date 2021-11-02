package ru.an1s9n.binanceapiclient;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.exception.BinanceApiError;
import ru.an1s9n.binanceapiclient.exception.BinanceApiException;
import ru.an1s9n.binanceapiclient.model.market.AggregateTradeItem;
import ru.an1s9n.binanceapiclient.model.market.AveragePrice;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;
import ru.an1s9n.binanceapiclient.model.market.Kline;
import ru.an1s9n.binanceapiclient.model.market.KlineInterval;
import ru.an1s9n.binanceapiclient.model.market.OrderBook;
import ru.an1s9n.binanceapiclient.model.market.ServerTime;
import ru.an1s9n.binanceapiclient.model.market.Ticker24HrStatistics;
import ru.an1s9n.binanceapiclient.model.market.TickerOrderBook;
import ru.an1s9n.binanceapiclient.model.market.TickerPrice;
import ru.an1s9n.binanceapiclient.model.market.TradeItem;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.API_KEY_HEADER;
import static ru.an1s9n.binanceapiclient.config.BinanceApiConfig.Endpoints.*;

@RequiredArgsConstructor
public class BinanceApiReactiveClientImpl implements BinanceApiReactiveClient {

  private final WebClient webClient;
  private final String apiKey;
  private final String secret;

  //TODO: handle errors

  @Override
  public Mono<Void> ping() {
    return webClient.get()
      .uri(PING_ENDPOINT)
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(Void.class);
  }

  @Override
  public Mono<Long> getServerTime() {
    return webClient.get()
      .uri(SERVER_TIME_ENDPOINT)
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(ServerTime.class).flatMap(st -> Mono.just(st.getServerTime()));
  }

  @Override
  public Mono<ExchangeInfo> getExchangeInfo() {
    return webClient.get()
      .uri(EXCHANGE_INFO_ENDPOINT)
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(ExchangeInfo.class);
  }

  @Override
  public Mono<ExchangeInfo> getExchangeInfo(String symbol) {
    return getExchangeInfo(List.of(symbol));
  }

  @Override
  public Mono<ExchangeInfo> getExchangeInfo(List<String> symbols) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(EXCHANGE_INFO_ENDPOINT)
        .queryParam("symbols", prepareSymbolsString(symbols))
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(ExchangeInfo.class);
  }

  @Override
  public Mono<OrderBook> getOrderBook(String symbol, Integer limit) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(ORDER_BOOK_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .queryParamIfPresent("limit", Optional.ofNullable(limit))
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(OrderBook.class);
  }

  @Override
  public Mono<List<TradeItem>> getRecentTrades(String symbol, Integer limit) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(RECENT_TRADES_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .queryParamIfPresent("limit", Optional.ofNullable(limit))
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  @Override
  public Mono<List<TradeItem>> getHistoricalTrades(String symbol, Integer limit, Long fromId) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(HISTORICAL_TRADES_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .queryParamIfPresent("limit", Optional.ofNullable(limit))
        .queryParamIfPresent("fromId", Optional.ofNullable(fromId))
        .build()
      )
      .header(API_KEY_HEADER, apiKey)
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  @Override
  public Mono<List<AggregateTradeItem>> getAggregateTrades(String symbol, Long fromId, Long startTime, Long endTime, Integer limit) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(AGGREGATE_TRADES_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .queryParamIfPresent("fromId", Optional.ofNullable(fromId))
        .queryParamIfPresent("startTime", Optional.ofNullable(startTime))
        .queryParamIfPresent("endTime", Optional.ofNullable(endTime))
        .queryParamIfPresent("limit", Optional.ofNullable(limit))
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  @Override
  public Mono<List<Kline>> getKlines(String symbol, KlineInterval interval, Long startTime, Long endTime, Integer limit) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(KLINES_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .queryParam("interval", interval.getId())
        .queryParamIfPresent("startTime", Optional.ofNullable(startTime))
        .queryParamIfPresent("endTime", Optional.ofNullable(endTime))
        .queryParamIfPresent("limit", Optional.ofNullable(limit))
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  @Override
  public Mono<AveragePrice> getAveragePrice(String symbol) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(AVERAGE_PRICE_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(AveragePrice.class);
  }

  @Override
  public Mono<List<Ticker24HrStatistics>> get24HrTickerStatistics() {
    return webClient.get()
      .uri(TICKER_24HR_STATISTICS_ENDPOINT)
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  @Override
  public Mono<Ticker24HrStatistics> get24HrTickerStatistics(String symbol) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(TICKER_24HR_STATISTICS_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(Ticker24HrStatistics.class);
  }

  @Override
  public Mono<List<TickerPrice>> getTickerPrice() {
    return webClient.get()
      .uri(TICKER_PRICE_ENDPOINT)
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  @Override
  public Mono<TickerPrice> getTickerPrice(String symbol) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(TICKER_PRICE_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(TickerPrice.class);
  }

  @Override
  public Mono<List<TickerOrderBook>> getTickerOrderBook() {
    return webClient.get()
      .uri(TICKER_ORDER_BOOK_ENDPOINT)
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  @Override
  public Mono<TickerOrderBook> getTickerOrderBook(String symbol) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(TICKER_ORDER_BOOK_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .build()
      )
      .retrieve()
      .onStatus(HttpStatus::is4xxClientError, response -> response.bodyToMono(BinanceApiError.class).map(it -> new BinanceApiException(response.rawStatusCode(), it)))
      .bodyToMono(TickerOrderBook.class);
  }

  private String prepareSymbolsString(List<String> symbols) {
    return symbols
      .stream()
      .map(String::toUpperCase)
      .map(it -> "\"" + it + "\"")
      .collect(Collectors.joining(",", "[", "]"));
  }

}

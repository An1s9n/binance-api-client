package ru.an1s9n.binanceapiclient;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.an1s9n.binanceapiclient.exception.BinanceApiException;
import ru.an1s9n.binanceapiclient.model.market.ExchangeInfo;
import ru.an1s9n.binanceapiclient.model.market.OrderBook;
import ru.an1s9n.binanceapiclient.model.market.ServerTime;
import ru.an1s9n.binanceapiclient.model.market.TradeItem;

import java.util.List;
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
    return webClient.get().uri(PING_ENDPOINT).retrieve().bodyToMono(Void.class);
  }

  @Override
  public Mono<Long> getServerTime() {
    return webClient.get().uri(SERVER_TIME_ENDPOINT).retrieve().bodyToMono(ServerTime.class).flatMap(st -> Mono.just(st.getServerTime()));
  }

  @Override
  public Mono<ExchangeInfo> getExchangeInfo() {
    return webClient.get().uri(EXCHANGE_INFO_ENDPOINT).retrieve().bodyToMono(ExchangeInfo.class);
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
      .bodyToMono(ExchangeInfo.class);
  }

  @Override
  public Mono<OrderBook> getOrderBook(String symbol, int limit) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(ORDER_BOOK_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .queryParam("limit", limit)
        .build()
      )
      .retrieve()
      .bodyToMono(OrderBook.class);
  }

  @Override
  public Mono<List<TradeItem>> getRecentTrades(String symbol, int limit) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(RECENT_TRADES_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .queryParam("limit", limit)
        .build()
      )
      .retrieve()
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  @Override
  public Mono<List<TradeItem>> getHistoricalTrades(String symbol, int limit, long fromId) {
    return webClient.get()
      .uri(uriBuilder -> uriBuilder
        .path(HISTORICAL_TRADES_ENDPOINT)
        .queryParam("symbol", symbol.toUpperCase())
        .queryParam("limit", limit)
        .queryParam("fromId", fromId)
        .build()
      )
      .header(API_KEY_HEADER, apiKey)
      .retrieve()
      .onStatus(HttpStatus.UNAUTHORIZED::equals, response -> response.bodyToMono(BinanceApiException.class))
      .bodyToMono(new ParameterizedTypeReference<>() {});
  }

  private String prepareSymbolsString(List<String> symbols) {
    return symbols
      .stream()
      .map(String::toUpperCase)
      .map(it -> "\"" + it + "\"")
      .collect(Collectors.joining(",", "[", "]"));
  }

}

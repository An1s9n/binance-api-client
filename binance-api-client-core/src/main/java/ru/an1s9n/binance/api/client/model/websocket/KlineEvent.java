package ru.an1s9n.binance.api.client.model.websocket;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import ru.an1s9n.binance.api.client.model.market.KlineInterval;

import java.math.BigDecimal;
import java.util.Map;

import static java.util.Optional.ofNullable;
import static ru.an1s9n.binance.api.client.config.BinanceApiConfig.Endpoints.KLINE_STREAM_NAME;

@Data
public class KlineEvent {

  public static final String STREAM_NAME = KLINE_STREAM_NAME;

  @JsonProperty("e") private String eventType;
  @JsonProperty("E") private Long eventTime;
  @JsonProperty("s") private String symbol;
  private Long startTime;
  private Long closeTime;
  private KlineInterval interval;
  private Long firstTradeId;
  private Long lastTradeId;
  private BigDecimal openPrice;
  private BigDecimal closePrice;
  private BigDecimal highPrice;
  private BigDecimal lowPrice;
  private BigDecimal baseAssetVolume;
  private Long numberOfTrades;
  private Boolean closed;
  private BigDecimal quoteAssetVolume;
  private BigDecimal takerBuyBaseAssetVolume;
  private BigDecimal takerBuyQuoteAssetVolume;

  @JsonProperty("k")
  private void unpackNested(Map<String, String> klineEventDetails) {
    this.startTime = ofNullable(klineEventDetails.get("t")).map(Long::valueOf).orElse(null);
    this.closeTime = ofNullable(klineEventDetails.get("T")).map(Long::valueOf).orElse(null);
    this.interval = ofNullable(klineEventDetails.get("i")).map(KlineInterval::getById).orElse(null);
    this.firstTradeId = ofNullable(klineEventDetails.get("f")).map(Long::valueOf).orElse(null);
    this.lastTradeId = ofNullable(klineEventDetails.get("L")).map(Long::valueOf).orElse(null);
    this.openPrice = ofNullable(klineEventDetails.get("o")).map(BigDecimal::new).orElse(null);
    this.closePrice = ofNullable(klineEventDetails.get("c")).map(BigDecimal::new).orElse(null);
    this.highPrice = ofNullable(klineEventDetails.get("h")).map(BigDecimal::new).orElse(null);
    this.lowPrice = ofNullable(klineEventDetails.get("l")).map(BigDecimal::new).orElse(null);
    this.baseAssetVolume = ofNullable(klineEventDetails.get("v")).map(BigDecimal::new).orElse(null);
    this.numberOfTrades = ofNullable(klineEventDetails.get("n")).map(Long::valueOf).orElse(null);
    this.closed = ofNullable(klineEventDetails.get("x")).map(Boolean::valueOf).orElse(null);
    this.quoteAssetVolume = ofNullable(klineEventDetails.get("q")).map(BigDecimal::new).orElse(null);
    this.takerBuyBaseAssetVolume = ofNullable(klineEventDetails.get("V")).map(BigDecimal::new).orElse(null);
    this.takerBuyQuoteAssetVolume = ofNullable(klineEventDetails.get("Q")).map(BigDecimal::new).orElse(null);
  }
}

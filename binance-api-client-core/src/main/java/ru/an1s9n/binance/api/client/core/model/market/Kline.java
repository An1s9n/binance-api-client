package ru.an1s9n.binance.api.client.core.model.market;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonFormat(shape = JsonFormat.Shape.ARRAY)
public class Kline {
  private Long openTime;
  private BigDecimal open;
  private BigDecimal high;
  private BigDecimal low;
  private BigDecimal close;
  private BigDecimal volume;
  private Long closeTime;
  private BigDecimal quoteAssetVolume;
  private Long numberOfTrades;
  private BigDecimal takerBuyBaseAssetVolume;
  private BigDecimal takerBuyQuoteAssetVolume;
}



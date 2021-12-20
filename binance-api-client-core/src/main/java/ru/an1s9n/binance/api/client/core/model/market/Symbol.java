package ru.an1s9n.binance.api.client.core.model.market;

import lombok.Data;

import java.util.List;

@Data
public class Symbol {
  private String symbol;
  private SymbolStatus status;
  private String baseAsset;
  private Integer baseAssetPrecision;
  private String quoteAsset;
  private Integer quotePrecision;
  private Integer quoteAssetPrecision;
  private List<OrderType> orderTypes;
  private Boolean icebergAllowed;
  private Boolean ocoAllowed;
  private Boolean isSpotTradingAllowed;
  private Boolean isMarginTradingAllowed;
  private List<SymbolFilter> filters;
  private List<Permission> permissions;
}

package client.model.market;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SymbolFilter {
  private SymbolFilterType filterType;

  private BigDecimal minPrice;          // PRICE_FILTER
  private BigDecimal maxPrice;          // PRICE_FILTER
  private BigDecimal tickSize;          // PRICE_FILTER

  private BigDecimal multiplierUp;      // PERCENT_PRICE
  private BigDecimal multiplierDown;    // PERCENT_PRICE
  private Integer avgPriceMins;         // PERCENT_PRICE, MIN_NOTIONAL

  private BigDecimal minQty;            // LOT_SIZE, MARKET_LOT_SIZE
  private BigDecimal maxQty;            // LOT_SIZE, MARKET_LOT_SIZE
  private BigDecimal stepSize;          // LOT_SIZE, MARKET_LOT_SIZE

  private BigDecimal minNotional;       // MIN_NOTIONAL
  private Boolean applyToMarket;        // MIN_NOTIONAL

  private Integer limit;                // ICEBERG_PARTS

  private Integer maxNumOrders;         // MAX_NUM_ORDERS

  private Integer maxNumAlgoOrders;     // MAX_NUM_ALGO_ORDERS

  private Integer maxNumIcebergOrders;  // MAX_NUM_ICEBERG_ORDERS

  private BigDecimal maxPosition;       // MAX_POSITION
}

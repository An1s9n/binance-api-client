package client.model.market;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TickerPrice {
  private String symbol;
  private BigDecimal price;
}

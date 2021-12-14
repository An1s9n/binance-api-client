package client.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class BinanceApiException extends RuntimeException {

  private final int httpStatus;
  private final BinanceApiError error;

  public BinanceApiException(int httpStatus, BinanceApiError error) {
    super(error.msg());
    this.httpStatus = httpStatus;
    this.error = error;
  }

  public BinanceApiException(int httpStatus) {
    this.httpStatus = httpStatus;
    this.error = null;
  }

}

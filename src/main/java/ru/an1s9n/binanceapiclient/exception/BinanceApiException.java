package ru.an1s9n.binanceapiclient.exception;

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
    super(error.getMsg());
    this.httpStatus = httpStatus;
    this.error = error;
  }

}

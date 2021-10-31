package ru.an1s9n.binanceapiclient.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BinanceApiException extends RuntimeException {

  private final Integer code;
  private final String msg;

  public BinanceApiException(
    @JsonProperty("code") Integer code,
    @JsonProperty("msg") String msg
  ) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

}



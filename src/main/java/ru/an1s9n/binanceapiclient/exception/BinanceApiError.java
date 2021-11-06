package ru.an1s9n.binanceapiclient.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class BinanceApiError implements Serializable {

  private final Integer code;
  private final String msg;

  public BinanceApiError(
    @JsonProperty("code") Integer code,
    @JsonProperty("msg") String msg
  ) {
    this.code = code;
    this.msg = msg;
  }

}

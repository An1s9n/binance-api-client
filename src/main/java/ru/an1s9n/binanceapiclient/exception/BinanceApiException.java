package ru.an1s9n.binanceapiclient.exception;

public class BinanceApiException extends RuntimeException {

  private final int code;
  private final String msg;

  public BinanceApiException(int code, String msg) {
    super(msg);
    this.code = code;
    this.msg = msg;
  }

}



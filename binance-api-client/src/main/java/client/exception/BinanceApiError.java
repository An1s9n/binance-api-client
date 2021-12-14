package client.exception;

import java.io.Serializable;

public record BinanceApiError(Integer code, String msg) implements Serializable {}

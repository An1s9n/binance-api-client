package ru.an1s9n.binance.api.client.exception;

import java.io.Serializable;

public record BinanceApiError(Integer code, String msg) implements Serializable {}

package ru.an1s9n.binance.api.client.core.exception;

import java.io.Serializable;

public record BinanceApiError(Integer code, String msg) implements Serializable {}

package ru.an1s9n.binance.api.client.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("binance.api")
public record BinanceApiClientProperties (String key, String secretKey) {}

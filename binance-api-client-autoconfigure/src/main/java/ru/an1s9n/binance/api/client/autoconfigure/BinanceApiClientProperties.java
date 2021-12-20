package ru.an1s9n.binance.api.client.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @param key       Binance api key for accessing endpoints which require it
 * @param secretKey Binance api secret key for accessing endpoints which require it
 */
@ConfigurationProperties("binance.api")
public record BinanceApiClientProperties(String key, String secretKey) {}

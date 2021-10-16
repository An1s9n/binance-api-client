package ru.an1s9n.binanceapiclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class BinanceApiRestClientImpl implements BinanceApiRestClient {

  private final ObjectMapper mapper;
  private final WebClient webClient;
  private final String apiKey;
  private final String secret;

  @Override
  public Long getServerTime() {
    return null; //TODO
  }

}

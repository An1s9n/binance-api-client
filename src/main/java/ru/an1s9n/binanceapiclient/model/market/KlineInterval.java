package ru.an1s9n.binanceapiclient.model.market;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum KlineInterval {
  ONE_MINUTE("1m"),
  THREE_MINUTES("3m"),
  FIVE_MINUTES("5m"),
  FIFTEEN_MINUTES("15m"),
  THIRTY_MINUTES("30m"),
  ONE_HOUR("1h"),
  TWO_HOURS("2h"),
  FOUR_HOURS("4h"),
  SIX_HOURS("6h"),
  EIGHT_HOURS("8h"),
  TWELVE_HOURS("12h"),
  ONE_DAY("1d"),
  THREE_DAYS("3d"),
  ONE_WEEK("1w"),
  ONE_MONTH("1M"),
  @JsonEnumDefaultValue UNKNOWN("unknown");

  private final String id;

  private static final Map<String, KlineInterval> id2KlineInterval;
  static {
    id2KlineInterval = new HashMap<>();
    for (KlineInterval i : KlineInterval.values()) {
      id2KlineInterval.put(i.id, i);
    }
  }

  public static KlineInterval getById(String id) {
    final var klineInterval = id2KlineInterval.get(id);
    return klineInterval == null ? UNKNOWN : klineInterval;
  }

}

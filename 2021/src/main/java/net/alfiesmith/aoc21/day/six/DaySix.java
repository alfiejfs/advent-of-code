package net.alfiesmith.aoc21.day.six;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import net.alfiesmith.aoc21.Pair;
import net.alfiesmith.aoc21.day.Day;

public final class DaySix extends Day {

  public DaySix() {
    super(6);
  }

  @Override
  public Object partOne(String input) {
    BigInteger total = BigInteger.ZERO;
    Map<Pair<Integer, Integer>, BigInteger> cache = new HashMap<>();
    for (String numberString : input.split(",")) {
      int value = Integer.parseInt(numberString);
      Pair<Integer, Integer> pair = new Pair<>(80, value);
      if (cache.containsKey(pair)) {
        total = total.add(cache.get(pair));
      } else {
        BigInteger handled = handle(80, value, cache);
        cache.put(pair, handled);
        total = total.add(handle(80, value, cache));
      }
    }

    return total.toString();
  }

  @Override
  public Object partTwo(String input) {
    BigInteger total = BigInteger.ZERO;
    Map<Pair<Integer, Integer>, BigInteger> cache = new HashMap<>();
    for (String numberString : input.split(",")) {
      int value = Integer.parseInt(numberString);
      Pair<Integer, Integer> pair = new Pair<>(256, value);
      if (cache.containsKey(pair)) {
        total = total.add(cache.get(pair));
      } else {
        BigInteger handled = handle(256, value, cache);
        cache.put(pair, handled);
        total = total.add(handle(256, value, cache));
      }
    }

    return total.toString();
  }

  private BigInteger handle(int days, int value, Map<Pair<Integer, Integer>, BigInteger> cache) {
    BigInteger count = new BigInteger("1");

    while (days > value) {
      days -= value + 1;

      Pair<Integer, Integer> pair = new Pair<>(days, 8);
      if (cache.containsKey(pair)) {
        count = count.add(cache.get(pair));
      } else {
        BigInteger handled = handle(days, 8, cache);
        count = count.add(handled);
        cache.put(pair, handled);
      }
      value = 6;
    }

    return count;
  }

}

package net.alfiesmith.aoc21.day.one;

import net.alfiesmith.aoc21.day.Day;

public final class DayOne extends Day {

  public DayOne() {
    super(1);
  }

  @Override
  public Object partOne(String input) {
    int previous = Integer.MIN_VALUE;
    int count = 0;
    for (String line : input.split("\n")) {
      int value = Integer.parseInt(line);
      if (previous != Integer.MIN_VALUE && value > previous) {
        count++;
      }
      previous = value;
    }

    return count;
  }

  @Override
  public Object partTwo(String input) {
    int count = 0;
    String[] split = input.split("\n");
    for (int i = 3; i < split.length; i++) {
      int zero = Integer.parseInt(split[i - 3]);
      int one = Integer.parseInt(split[i - 2]);
      int two = Integer.parseInt(split[i - 1]);
      int three = Integer.parseInt(split[i]);
      if (zero + one + two < one + two + three) {
        count++;
      }
    }

    return count;
  }

}

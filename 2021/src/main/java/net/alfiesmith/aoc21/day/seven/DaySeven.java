package net.alfiesmith.aoc21.day.seven;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.alfiesmith.aoc21.day.Day;

public final class DaySeven extends Day {

  public DaySeven() {
    super(7);
  }

  @Override
  public Object partOne(String input) {
    List<Integer> crabs = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt)
        .sorted()
        .boxed()
        .collect(Collectors.toList());

    int position = crabs.get(crabs.size() / 2);
    int value = 0;
    for (int crab : crabs) {
      value += Math.abs(position - crab);
    }

    return value;
  }

  @Override
  public Object partTwo(String input) {
    List<Integer> crabs = Arrays.stream(input.split(",")).mapToInt(Integer::parseInt)
        .sorted()
        .boxed()
        .collect(Collectors.toList());

    int minValue = Integer.MAX_VALUE;

    for (int position : crabs) {
      int value = 0;
      for (int crab : crabs) {
        int distance = Math.abs(position - crab);
        value += (distance * (distance + 1)) / 2;
      }

      if (value < minValue) {
        minValue = value;
      }
    }

    return minValue;
  }

}

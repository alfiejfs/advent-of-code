package net.alfiesmith.aoc21.day.two;

import net.alfiesmith.aoc21.day.Day;

public final class DayTwo extends Day {

  public DayTwo() {
    super(2);
  }

  @Override
  public Object partOne(String input) {
    int x = 0;
    int y = 0;
    for (String line : input.split("\n")) {
      if (line.startsWith("forward")) {
        int value = Integer.parseInt(line.substring(8));
        x += value;
      } else if (line.startsWith("down")) {
        int value = Integer.parseInt(line.substring(5));
        y += value;
      } else if (line.startsWith("up")) {
        int value = Integer.parseInt(line.substring(3));
        y -= value;
      }
    }

    return x * y;
  }

  @Override
  public Object partTwo(String input) {
    int x = 0;
    int y = 0;
    int aim = 0;
    for (String line : input.split("\n")) {
      if (line.startsWith("forward")) {
        int value = Integer.parseInt(line.substring(8));
        x += value;
        y += aim * value;
      } else if (line.startsWith("down")) {
        int value = Integer.parseInt(line.substring(5));
        aim += value;
      } else if (line.startsWith("up")) {
        int value = Integer.parseInt(line.substring(3));
        aim -= value;
      }
    }

    return x * y;
  }

}

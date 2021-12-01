package net.alfiesmith.aoc15.day.one;

import net.alfiesmith.aoc15.day.Day;

public final class DayOne extends Day {

  public DayOne() {
    super(1);
  }

  @Override
  public Object partOne(String input) {
    System.out.println(input);
    int count = 0;
    for (char character : input.toCharArray()) {
      if (character == '(') {
        count++;
      } else if (character == ')') {
        count--;
      }
    }

    return count;
  }

  @Override
  public Object partTwo(String input) {
    int count = 0;
    int position = 1;

    for (char character : input.toCharArray()) {
      if (character == '(') {
        count++;
      } else if (character == ')') {
        count--;
      }

      if (count == -1) {
        return position;
      }

      position++;
    }

    return -1;
  }
}

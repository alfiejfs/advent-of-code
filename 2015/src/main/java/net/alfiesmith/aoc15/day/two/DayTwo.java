package net.alfiesmith.aoc15.day.two;

import net.alfiesmith.aoc15.day.Day;

public final class DayTwo extends Day {

  public DayTwo() {
    super(2);
  }

  @Override
  public Object partOne(String input) {
    int total = 0;
    for (String line : input.split("\n")) {
      String[] dimensions = line.split("x");
      int length = Integer.parseInt(dimensions[0]);
      int width = Integer.parseInt(dimensions[1]);
      int height = Integer.parseInt(dimensions[2]);

      int lw = length * width;
      int lh = length * height;
      int wh = width * height;

      total += 2 * lw + 2 * wh + 2 * lh + Math.min(lw, Math.min(lh, wh));
    }

    return total;
  }

  @Override
  public Object partTwo(String input) {
    int total = 0;
    for (String line : input.split("\n")) {
      String[] dimensions = line.split("x");
      int length = Integer.parseInt(dimensions[0]);
      int width = Integer.parseInt(dimensions[1]);
      int height = Integer.parseInt(dimensions[2]);

      int perimeter;

      if (length <= width && length <= height) {
        perimeter = length * 2 + Math.min(width, height) * 2;
      } else if (width <= length && width <= height) {
        perimeter = width * 2 + Math.min(length, height) * 2;
      } else {
        perimeter = height * 2 + Math.min(length, width) * 2;
      }

      total += length * width * height + perimeter;
    }

    return total;
  }
}

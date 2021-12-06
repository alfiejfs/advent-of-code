package net.alfiesmith.aoc21.day.five;

import java.util.HashSet;
import java.util.Set;
import net.alfiesmith.aoc21.Pair;
import net.alfiesmith.aoc21.day.Day;

public final class DayFive extends Day {

  public DayFive() {
    super(5);
  }

  @Override
  public Object partOne(String input) {
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    Set<Pair<Integer, Integer>> counted = new HashSet<>();
    for (String line : input.split("\n")) {
        String[] data = line.split(" -> ");
        String[] left = data[0].split(",");
        String[] right = data[1].split(",");
        int x1 = Integer.parseInt(left[0]);
        int x2 = Integer.parseInt(right[0]);
        int y1 = Integer.parseInt(left[1]);
        int y2 = Integer.parseInt(right[1]);

        if (x1 == x2) {
          for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
            Pair<Integer, Integer> value = new Pair<>(x1, i);
            if (!visited.add(value)) {
              counted.add(value);
            }
          }
        } else if (y1 == y2) {
          for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
            Pair<Integer, Integer> value = new Pair<>(i, y1);
            if (!visited.add(value)) {
              counted.add(value);
            }
          }
        }
    }

    return counted.size();
  }



  @Override
  public Object partTwo(String input) {
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    Set<Pair<Integer, Integer>> counted = new HashSet<>();
    for (String line : input.split("\n")) {
      String[] data = line.split(" -> ");
      String[] left = data[0].split(",");
      String[] right = data[1].split(",");
      int x1 = Integer.parseInt(left[0]);
      int x2 = Integer.parseInt(right[0]);
      int y1 = Integer.parseInt(left[1]);
      int y2 = Integer.parseInt(right[1]);

      if (x1 == x2) {
        for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
          Pair<Integer, Integer> value = new Pair<>(x1, i);
          if (!visited.add(value)) {
            counted.add(value);
          }
        }
      } else if (y1 == y2) {
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
          Pair<Integer, Integer> value = new Pair<>(i, y1);
          if (!visited.add(value)) {
            counted.add(value);
          }
        }
      } else {

        int startX, startY, endX, endY;
        if (x1 < x2) {
          startX = x1;
          startY = y1;
          endX = x2;
          endY = y2;
        } else {
          startX = x2;
          startY = y2;
          endX = x1;
          endY = y1;
        }

        int gradient = (startY < endY) ? 1 : -1;

        for (int i = 0; i <= endX - startX; i++) {
          Pair<Integer, Integer> value = new Pair<>(startX + i, startY + (i * gradient));
          if (!visited.add(value)) {
            counted.add(value);
          }
        }
      }

    }
      return counted.size();
  }

}

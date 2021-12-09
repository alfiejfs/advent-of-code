package net.alfiesmith.aoc21.day.nine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.alfiesmith.aoc21.Pair;
import net.alfiesmith.aoc21.day.Day;

public final class DayNine extends Day {

  public DayNine() {
    super(9);
  }

  @Override
  public Object partOne(String input) {
    Map<Pair<Integer, Integer>, Integer> data = new HashMap<>();

    int currentY = 0;
    int maxX = Integer.MIN_VALUE;
    for (String line : input.split("\n")) {
      int currentX = 0;
      for (char numberChar : line.toCharArray()) {
        int value = Integer.parseInt("" + numberChar);
        maxX = Math.max(maxX, currentX);
        data.put(new Pair<>(currentX++, currentY), value);
      }
      currentY++;
    }

    int maxY = currentY - 1;

    int sum = 0;
    for (Map.Entry<Pair<Integer, Integer>, Integer> entry : data.entrySet()) {
      int x = entry.getKey().key;
      int y = entry.getKey().value;
      int value = entry.getValue();

      if (x == 0) {
        if (data.get(new Pair<>(1, y)) <= value) {
          continue;
        }
      } else if (x == maxX) {
        if (data.get(new Pair<>(maxX - 1, y)) <= value) {
          continue;
        }
      } else {
        if (data.get(new Pair<>(x + 1, y)) <= value || data.get(new Pair<>(x - 1, y)) <= value) {
          continue;
        }
      }

      if (y == 0) {
        if (data.get(new Pair<>(x, 1)) <= value) {
          continue;
        }
      } else if (y == maxY) {
        if (data.get(new Pair<>(x, maxY - 1)) <= value) {
          continue;
        }
      } else {
        if (data.get(new Pair<>(x, y + 1)) <= value || data.get(new Pair<>(x, y - 1)) <= value) {
          continue;
        }
      }

      sum += value + 1;
    }

    return sum;
  }

  @Override
  public Object partTwo(String input) {
    Map<Pair<Integer, Integer>, Integer> data = new HashMap<>();

    int currentY = 0;
    int maxX = Integer.MIN_VALUE;
    for (String line : input.split("\n")) {
      int currentX = 0;
      for (char numberChar : line.toCharArray()) {
        int value = Integer.parseInt("" + numberChar);
        maxX = Math.max(maxX, currentX);
        data.put(new Pair<>(currentX++, currentY), value);
      }
      currentY++;
    }

    int maxY = currentY - 1;

    List<Integer> basinSizes = new ArrayList<>();

    for (Map.Entry<Pair<Integer, Integer>, Integer> entry : data.entrySet()) {
      int x = entry.getKey().key;
      int y = entry.getKey().value;
      int value = entry.getValue();

      if (x == 0) {
        if (data.get(new Pair<>(1, y)) <= value) {
          continue;
        }
      } else if (x == maxX) {
        if (data.get(new Pair<>(maxX - 1, y)) <= value) {
          continue;
        }
      } else {
        if (data.get(new Pair<>(x + 1, y)) <= value || data.get(new Pair<>(x - 1, y)) <= value) {
          continue;
        }
      }

      if (y == 0) {
        if (data.get(new Pair<>(x, 1)) <= value) {
          continue;
        }
      } else if (y == maxY) {
        if (data.get(new Pair<>(x, maxY - 1)) <= value) {
          continue;
        }
      } else {
        if (data.get(new Pair<>(x, y + 1)) <= value || data.get(new Pair<>(x, y - 1)) <= value) {
          continue;
        }
      }

      Set<Pair<Integer, Integer>> counted = new HashSet<>();
      basinSizes.add(
          getValue(x, y, maxX, maxY, data, true, new HashSet<>(), counted) +
              getValue(x, y, maxX, maxY, data, false, new HashSet<>(), counted)
      );
    }

    basinSizes.sort((o1, o2) -> o2 - o1);

    return basinSizes.get(0) * basinSizes.get(1) * basinSizes.get(2);
  }


  private int getValue(int x, int y, int maxX, int maxY, Map<Pair<Integer, Integer>, Integer> data,
      boolean sideways, Set<Pair<Integer, Integer>> called, Set<Pair<Integer, Integer>> counted) {
    int count = 0;
    int traverse = sideways ? x : y;

    if (counted.add(new Pair<>(x, y))) {
      count++;
    }

    if (sideways) {
      if (x < maxX) {
        while (data.get(new Pair<>(++traverse, y)) != 9 && called.add(new Pair<>(traverse, y))) {
          count += getValue(traverse, y, maxX, maxY, data, false, called, counted);
          if (traverse == maxX) {
            break;
          }
        }
      }

      if (x > 0) {
        traverse = x;

        while (data.get(new Pair<>(--traverse, y)) != 9 && called.add(new Pair<>(traverse, y))) {
          count += getValue(traverse, y, maxX, maxY, data, false, called, counted);
          if (traverse == 0) {
            break;
          }
        }
      }

    } else {
      if (y < maxY) {
        while (data.get(new Pair<>(x, ++traverse)) != 9 && called.add(new Pair<>(x, traverse))) {
          count += getValue(x, traverse, maxX, maxY, data, true, called, counted);
          if (traverse == maxY) {
            break;
          }
        }
      }

      if (y > 0) {
        traverse = y;
        while (data.get(new Pair<>(x, --traverse)) != 9 && called.add(new Pair<>(x, traverse))) {
          count += getValue(x, traverse, maxX, maxY, data, true, called, counted);

          if (traverse == 0) {
            break;
          }
        }
      }

    }

    return count;
  }


}

package net.alfiesmith.aoc15.day.six;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.alfiesmith.aoc15.Pair;
import net.alfiesmith.aoc15.day.Day;

public final class DaySix extends Day {

  enum Type {
    ON,
    OFF,
    TOGGLE;
  }

  public DaySix() {
    super(6);
  }


  @Override
  public Object partOne(String input) {
    Set<Pair<Integer, Integer>> on = new HashSet<>();

    for (String line : input.split("\n")) {
      Type type = null;

      if (line.startsWith("turn on ")) {
        line = line.substring(8);
        type = Type.ON;
      } else if (line.startsWith("turn off ")) {
        line = line.substring(9);
        type = Type.OFF;
      } else if (line.startsWith("toggle ")) {
        line = line.substring(7);
        type = Type.TOGGLE;
      }

      String[] splitLine = line.split(" through ");
      int x1, y1, x2, y2;

      String[] sideSplit = splitLine[0].split(",");
      x1 = Integer.parseInt(sideSplit[0]);
      y1 = Integer.parseInt(sideSplit[1]);

      sideSplit = splitLine[1].split(",");
      x2 = Integer.parseInt(sideSplit[0]);
      y2 = Integer.parseInt(sideSplit[1]);

      for (int i = x1; i <= x2; i++) {
        for (int j = y1; j <= y2; j++) {
          switch (type) {
            case ON -> on.add(new Pair<>(i, j));
            case OFF -> on.remove(new Pair<>(i, j));
            case TOGGLE -> {
              Pair<Integer, Integer> loc = new Pair<>(i, j);
              if (!on.add(loc)) {
                on.remove(loc);
              }
            }
          }
        }
      }
    }

    return on.size();
  }

  @Override
  public Object partTwo(String input) {
    Map<Pair<Integer, Integer>, Integer> on = new HashMap<>();

    for (String line : input.split("\n")) {
      Type type = null;

      if (line.startsWith("turn on ")) {
        line = line.substring(8);
        type = Type.ON;
      } else if (line.startsWith("turn off ")) {
        line = line.substring(9);
        type = Type.OFF;
      } else if (line.startsWith("toggle ")) {
        line = line.substring(7);
        type = Type.TOGGLE;
      }

      String[] splitLine = line.split(" through ");
      int x1, y1, x2, y2;

      String[] sideSplit = splitLine[0].split(",");
      x1 = Integer.parseInt(sideSplit[0]);
      y1 = Integer.parseInt(sideSplit[1]);

      sideSplit = splitLine[1].split(",");
      x2 = Integer.parseInt(sideSplit[0]);
      y2 = Integer.parseInt(sideSplit[1]);

      for (int i = x1; i <= x2; i++) {
        for (int j = y1; j <= y2; j++) {
          Pair<Integer, Integer> loc = new Pair<>(i, j);
          switch (type) {
            case ON -> on.put(loc, on.getOrDefault(loc, 0) + 1);
            case OFF -> on.put(loc, Math.max(on.getOrDefault(loc, 0) - 1, 0));
            case TOGGLE -> on.put(loc, on.getOrDefault(loc, 0) + 2);
          }
        }
      }
    }

    return on.values().stream().mapToInt(i -> i).sum();
  }
}

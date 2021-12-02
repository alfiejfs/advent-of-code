package net.alfiesmith.aoc16.day.one;

import java.util.HashSet;
import java.util.Set;
import net.alfiesmith.aoc16.Pair;
import net.alfiesmith.aoc16.day.Day;

public final class DayOne extends Day {

  public DayOne() {
    super(1);
  }

  @Override
  public Object partOne(String input) {
    int x = 0;
    int y = 0;
    int direction = 0;
    for (String instruction : input.split(", ")) {
      char character = instruction.charAt(0);
      int amount = Integer.parseInt(instruction.substring(1));

      if (character == 'R') {
        direction += 90;
      } else if (character == 'L') {
        direction -= 90;
      }

      if (direction == -90) {
        direction = 270;
      } if (direction == 360) {
        direction = 0;
      }

      switch (direction) {
        case 90 -> x += amount;
        case 180 -> y -= amount;
        case 270 -> x -= amount;
        case 0 -> y += amount;
      }
    }

    return x + y;
  }

  @Override
  public Object partTwo(String input) {
    int x = 0;
    int y = 0;
    int direction = 0;
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    for (String instruction : input.split(", ")) {
      char character = instruction.charAt(0);
      int amount = Integer.parseInt(instruction.substring(1));

      if (character == 'R') {
        direction += 90;
      } else if (character == 'L') {
        direction -= 90;
      }

      if (direction == -90) {
        direction = 270;
      } if (direction == 360) {
        direction = 0;
      }

      switch (direction) {
        case 90 -> {
          for (int i = 0; i < amount; i++) {
            x += 1;
            if (!visited.add(new Pair<>(x, y))) {
              return x + y;
            }
          }
        }
        case 180 -> {
          for (int i = 0; i < amount; i++) {
            y -= 1;
            if (!visited.add(new Pair<>(x, y))) {
              return x + y;
            }
          }
        }
        case 270 -> {
          for (int i = 0; i < amount; i++) {
            x -= 1;
            if (!visited.add(new Pair<>(x, y))) {
              return x + y;
            }
          }
        }
        case 0 -> {
          for (int i = 0; i < amount; i++) {
            y += 1;
            if (!visited.add(new Pair<>(x, y))) {
              return x + y;
            }
          }
        }
      }


    }

    return -1;
  }

}

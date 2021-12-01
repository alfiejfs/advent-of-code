package net.alfiesmith.aoc15.day.three;

import java.util.HashSet;
import java.util.Set;
import net.alfiesmith.aoc15.Pair;
import net.alfiesmith.aoc15.day.Day;

public final class DayThree extends Day {

  public DayThree() {
    super(3);
  }

  @Override
  public Object partOne(String input) {
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    visited.add(new Pair<>(0, 0));

    int x = 0;
    int y = 0;
    for (char direction : input.toCharArray()) {
      switch (direction) {
        case '^' -> y++;
        case '>' -> x++;
        case 'v' -> y--;
        case '<' -> x--;
      }

      Pair<Integer, Integer> coord = new Pair<>(x, y);
      visited.add(coord);
    }

    return visited.size();
  }

  @Override
  public Object partTwo(String input) {
    Set<Pair<Integer, Integer>> visited = new HashSet<>();
    visited.add(new Pair<>(0, 0));

    int x1 = 0;
    int y1 = 0;
    int x2 = 0;
    int y2 = 0;
    boolean santa = true;
    for (char direction : input.toCharArray()) {
      Pair<Integer, Integer> coord;
      if (santa) {
        switch (direction) {
          case '^' -> y1++;
          case '>' -> x1++;
          case 'v' -> y1--;
          case '<' -> x1--;
        }
        coord = new Pair<>(x1, y1);
      } else {
        switch (direction) {
          case '^' -> y2++;
          case '>' -> x2++;
          case 'v' -> y2--;
          case '<' -> x2--;
        }
        coord = new Pair<>(x2, y2);
      }

      santa = !santa;
      visited.add(coord);
    }

    return visited.size();
  }
}

package net.alfiesmith.aoc16.day.two;

import net.alfiesmith.aoc16.day.Day;

public final class DayTwo extends Day {

  public DayTwo() {
    super(2);
  }

  @Override
  public Object partOne(String input) {
    int[][] grid = new int[][]{
        {
            1, 2, 3,
        },
        {
            4, 5, 6,
        },
        {
            7, 8, 9,
        }
    };

    int y = 1;
    int x = 1;

    StringBuilder code = new StringBuilder();

    for (String line : input.split("\n")) {
      for (char character : line.toCharArray()) {
        switch (character) {
          case 'U' -> y -= 1;
          case 'D' -> y += 1;
          case 'L' -> x -= 1;
          case 'R' -> x += 1;
        }

        if (y == -1) {
          y = 0;
        } else if (y == 3) {
          y = 2;
        }

        if (x == -1) {
          x = 0;
        } else if (x == 3) {
          x = 2;
        }
      }

      code.append(grid[y][x]);
    }

    return code.toString();
  }

  @Override
  public Object partTwo(String input) {
    String[][] grid = new String[][]{
        {
            "", "", "1", "", ""
        },
        {
            "", "2", "3", "4", ""
        },
        {
          "5", "6", "7", "8", "9"
        },
        {
          "", "A", "B", "C", ""
        },
        {
          "", "", "D", "", ""
        }
    };

    int y = 1;
    int x = 1;

    StringBuilder code = new StringBuilder();

    for (String line : input.split("\n")) {
      for (char character : line.toCharArray()) {
        switch (character) {
          case 'U' -> {
            y -= 1;
            if (y == -1) {
              y = 0;
            } else if (grid[y][x].equals("")) {
              y += 1;
            }
          }
          case 'D' -> {
            y += 1;
            if (y == 5) {
              y = 4;
            } else if (grid[y][x].equals("")) {
              y -= 1;
            }
          }
          case 'L' -> {
            x -= 1;
            if (x == -1) {
              x = 0;
            } else if (grid[y][x].equals("")) {
              x += 1;
            }
          }
          case 'R' -> {
            x += 1;
            if (x == 5) {
              x = 4;
            } else if (grid[y][x].equals("")) {
              x -= 1;
            }
          }
        }
      }

      code.append(grid[y][x]);
    }

    return code.toString();
  }

}

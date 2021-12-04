package net.alfiesmith.aoc21.day.four;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.alfiesmith.aoc21.day.Day;

public final class DayFour extends Day {

  public DayFour() {
    super(4);
  }

  @Override
  public Object partOne(String input) {
    String[] split = input.split("\n");
    List<Integer> numbers = Arrays.stream(split[0].split(",")).map(Integer::parseInt).collect(Collectors.toList());
    Set<Board> boards = new HashSet<>();
    Set<Integer> done = new HashSet<>(); // Quicker than sublists

    int[][] stack = new int[5][5];
    int row = 0;
    for (int i = 2; i < split.length; i++) {
      String line = split[i];
      if (line.isBlank()) {
        boards.add(new Board(stack));
        stack = new int[5][5];
        row = 0;
      } else {
        stack[row++] = Arrays.stream(line.split(" ")).filter(l -> !l.isBlank())
            .mapToInt(Integer::parseInt).toArray();
      }
    }

    boards.add(new Board(stack));


    for (int number : numbers) {
      done.add(number);
      for (Board board : boards) {
        if (board.hasWon(done)) {
          return number * board.getUnmarkedSum(done);
        }
      }
    }

    return -1;
  }

  @Override
  public Object partTwo(String input) {
    String[] split = input.split("\n");
    List<Integer> numbers = Arrays.stream(split[0].split(",")).map(Integer::parseInt).collect(Collectors.toList());
    Set<Board> boards = new HashSet<>();
    Set<Integer> done = new HashSet<>(); // Quicker than sublists

    int[][] stack = new int[5][5];
    int row = 0;
    for (int i = 2; i < split.length; i++) {
      String line = split[i];
      if (line.isBlank()) {
        boards.add(new Board(stack));
        stack = new int[5][5];
        row = 0;
      } else {
        stack[row++] = Arrays.stream(line.split(" ")).filter(l -> !l.isBlank())
            .mapToInt(Integer::parseInt).toArray();
      }
    }

    boards.add(new Board(stack));


    for (int number : numbers) {
      done.add(number);
      Iterator<Board> iterator = boards.iterator();
      while (iterator.hasNext()) {
        Board board = iterator.next();
        if (board.hasWon(done)) {
          iterator.remove();

          if (boards.isEmpty()) {
            System.out.println(number);
            System.out.println(board.getUnmarkedSum(done));
            return board.getUnmarkedSum(done) * number;
          }
        }
      }
    }

    return -1;
  }


  static class Board {
    private final int[][] cards;

    public Board(int[][] cards) {
      this.cards = cards;
    }

    public boolean hasWon(Set<Integer> picked) {
      // Vertical
      outer:
      for (int x = 0; x < cards.length; x++) {
        if (Arrays.stream(cards[x]).filter(picked::contains).count() == 5) {
          return true;
        }
      }

      for (int i = 0; i < 5; i++) {
        for (int j = 0; j < 5; j++) {
          if (!picked.contains(cards[j][i])) {
            break;
          }

          if (j == 4) {
            return true;
          }
        }
      }

      return false;
    }

    public int getUnmarkedSum(Set<Integer> marked) {
      int sum = 0;
      for (int[] line : this.cards) {
        for (int x : line) {
          if (!marked.contains(x)) {
            sum += x;
          }
        }
      }

      return sum;
    }
  }
}

package net.alfiesmith.aoc21.day.ten;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import net.alfiesmith.aoc21.day.Day;

public final class DayTen extends Day {

  private static final Map<Character, Character> mappings = Map.of(
      '(', ')',
      '[', ']',
      '{', '}',
      '<', '>'
  );

  public DayTen() {
    super(10);
  }

  @Override
  public Object partOne(String input) {
    int score = 0;
    outer:
    for (String line : input.split("\n")) {
      Stack<Character> stack = new Stack<>();
      for (char character : line.toCharArray()) {
        switch (character) {
          case '(':
          case '[':
          case '{':
          case '<':
            stack.add(character);
            break;
          default:
            char popped = stack.pop();

            if (mappings.get(popped) != character) {
              switch (character) {
                case ')' -> score += 3;
                case ']' -> score += 57;
                case '}' -> score += 1197;
                case '>' -> score += 25137;
              }

              continue outer;
            }
        }
      }
    }

    return score;
  }

  @Override
  public Object partTwo(String input) {
    List<String> lines = new ArrayList<>();
    outer:
    for (String line : input.split("\n")) {
      Stack<Character> stack = new Stack<>();
      for (char character : line.toCharArray()) {
        switch (character) {
          case '(':
          case '[':
          case '{':
          case '<':
            stack.add(character);
            break;
          default:
            char popped = stack.pop();

            if (mappings.get(popped) != character) {
              continue outer;
            }
        }
      }
      lines.add(line);
    }

    List<BigInteger> scores = new ArrayList<>();
    for (String line : lines) {
      Stack<Character> stack = new Stack<>();
      for (char character : line.toCharArray()) {
        switch (character) {
          case '(':
          case '[':
          case '{':
          case '<':
            stack.add(character);
            break;
          default:
            stack.pop();
        }
      }

      BigInteger score = BigInteger.ZERO;
      for (int i = stack.size() - 1; i >= 0; i--) {
        int value = 0;
        switch (mappings.get(stack.get(i))) {
          case ')' -> value = 1;
          case ']' -> value = 2;
          case '}' -> value = 3;
          case '>' -> value = 4;
        }
        score = score.multiply(BigInteger.valueOf(5)).add(BigInteger.valueOf(value));
      }

      scores.add(score);
    }

    Collections.sort(scores);
    return scores.get(scores.size() / 2);
  }

}

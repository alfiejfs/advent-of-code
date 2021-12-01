package net.alfiesmith.aoc15.day.five;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import net.alfiesmith.aoc15.day.Day;

public final class DayFive extends Day {

  public DayFive() {
    super(5);
  }

  @Override
  public Object partOne(String input) {
    int nice = 0;

    Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u');
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    for (String line : input.split("\n")) {
      if (line.chars().filter(c -> vowels.contains((char) c)).count() < 3) {
        continue;
      }

      boolean found = false;
      for (char character : alphabet) {
        if (line.contains(character + "" + character)) {
          found = true;
          break;
        }
      }

      if (!found) {
        continue;
      }

      if (line.contains("ab") || line.contains("cd") || line.contains("pq") || line.contains(
          "xy")) {
        continue;
      }

      nice++;
    }

    return nice;
  }

  @Override
  public Object partTwo(String input) {
    int nice = 0;
    for (String line : input.split("\n")) {
      boolean firstCondition = false;
      boolean secondCondition = false;
      String secondrep="";
      String firstchar="";
      for (int i = 0; i < line.length() - 1; i++) {
        if (!firstCondition && i + 2 < line.length()) {
          if (line.charAt(i) == line.charAt(i + 2)) {
            firstCondition = true;
            firstchar = ""+line.charAt(i);
          }
        }

        if (!secondCondition) {
          String sequence = line.charAt(i) + "" + line.charAt(i + 1);
          String[] split = line.split(sequence, 2);
          if (split[0].contains(sequence) || (split.length > 1 && split[1].contains(sequence))) {
            secondCondition = true;
            secondrep = sequence;
          }
        }

        if (firstCondition && secondCondition) {
          nice++;
          System.out.println(line + " " + firstchar + " / " + secondrep);
          break;
        }
      }

    }


    return nice;
  }
}

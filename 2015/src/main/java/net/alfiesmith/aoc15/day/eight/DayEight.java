package net.alfiesmith.aoc15.day.eight;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.alfiesmith.aoc15.day.Day;

public final class DayEight extends Day {

  public DayEight() {
    super(8);
  }

  @Override
  public Object partOne(String input) {
    int value = 0;

    for (String line : input.split("\n")) {
      line = line.trim().replace(" ", "").substring(1, line.length() - 1);
      value += line.length() + 2;

      int past = 0;
      while (true) {
        int index = line.substring(past).indexOf('\\');
        if (index == -1 || index + 1 >= line.substring(past).length()) {
          break;
        }

        String sequence = line.substring(past + index, past + index + 2);

        switch (sequence) {
          case "\\\"" -> line = line.replaceFirst(Pattern.quote("\\\""),
              Matcher.quoteReplacement("\""));
          case "\\\\" -> line = line.replaceFirst(Pattern.quote("\\\\"),
              Matcher.quoteReplacement("\\"));
          case "\\x" -> {
            sequence = line.substring(past + index, past + index + 4);
            line = line.replaceFirst(Pattern.quote(sequence), Matcher.quoteReplacement("c"));
          }
        }
        past = past + index + 1;
      }

      value -= line.length();
    }

    return value;
  }

  @Override
  public Object partTwo(String input) {
    int value = 0;
    for (String line : input.split("\n")) {
      value -= line.length();
      int newLength = line.length();
      for (int i = 0; i < line.length(); i++) {
        if (line.charAt(i) == '"' || line.charAt(i) == '\\') {
          newLength++;
        }
      }

      value += newLength + 2;
    }

    return value;
  }

}

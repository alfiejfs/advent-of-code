package net.alfiesmith.aoc21.day.three;

import net.alfiesmith.aoc21.day.Day;

public final class DayThree extends Day {

  public DayThree() {
    super(3);
  }

  @Override
  public Object partOne(String input) {
    String[] split = input.split("\n");

    int length = split[0].length();
    StringBuilder sequence = new StringBuilder();
    for (int i = 0; i < length; i++) {
      int oneCount = 0;
      for (String s : split) {
        if (s.toCharArray()[i] == '1') {
          oneCount++;
        }
      }

      if (oneCount > split.length - oneCount) {
        sequence.append("1");
      } else {
        sequence.append("0");
      }
    }

    String gamma = sequence.toString();
    StringBuilder epsilon = new StringBuilder();
    for (char character : gamma.toCharArray()) {
      if (character == '1') {
        epsilon.append(0);
      } else {
        epsilon.append(1);
      }
    }

    return (Integer.parseUnsignedInt(gamma, 2)) * (Integer.parseUnsignedInt(epsilon.toString(), 2));
  }

  @Override
  public Object partTwo(String input) {
    String[] split = input.split("\n");

    int length = split[0].length();
    int oxygen = 0;
    int co2 = 0;

    for (int i = 0; i < length; i++) {
      int oneCount = 0;
      for (String s : split) {
        if (s.toCharArray()[i] == '1') {
          oneCount++;
        }
      }

      String[] newSplit = new String[Math.max(oneCount, split.length - oneCount)];
      int j = 0;
      for (String s : split) {
        if (s.toCharArray()[i] == ((oneCount >= split.length - oneCount) ? '1' : '0')) {
          newSplit[j++] = s;
        }
      }

      split = newSplit;
      if (split.length == 1) {
        oxygen = Integer.parseUnsignedInt(split[0], 2);
        break;
      }
    }


    split = input.split("\n");
    for (int i = 0; i < length; i++) {
      int zeroCount = 0;
      for (String s : split) {
        if (s.toCharArray()[i] == '0') {
          zeroCount++;
        }
      }

      String[] newSplit = new String[Math.min(zeroCount, split.length - zeroCount)];
      int j = 0;
      for (String s : split) {
        if (s.toCharArray()[i] == ((zeroCount <= split.length - zeroCount) ? '0' : '1')) {
          newSplit[j++] = s;
        }
      }

      split = newSplit;
      if (split.length == 1) {
        co2 = Integer.parseUnsignedInt(split[0], 2);
        break;
      }
    }

    return oxygen * co2;
  }

}

package net.alfiesmith.aoc21.day.eight;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import net.alfiesmith.aoc21.day.Day;

public final class DayEight extends Day {

  private final Map<Integer, Set<Character>> characterLengths = Map.of(
      0, Set.of('a', 'b', 'c', 'e', 'f', 'g'), // 6
      1, Set.of('c', 'f'), // 2 - unique
      2, Set.of('a', 'c', 'd', 'e', 'g'), // 5
      3, Set.of('a', 'c', 'd', 'f', 'g'), // 5
      4, Set.of('b', 'c', 'd', 'f'), //4 - unique
      5, Set.of('a', 'b', 'd', 'f', 'g'), // 5
      6, Set.of('a', 'b', 'd', 'e', 'f', 'g'), // 6
      7, Set.of('a', 'c', 'f'), // 3 - unique
      8, Set.of('a', 'b', 'c', 'd', 'e', 'f', 'g'), // 7 - unique
      9, Set.of('a', 'b', 'c', 'd', 'f', 'g') // 6
  );

  public DayEight() {
    super(8);
  }

  @Override
  public Object partOne(String input) {
    int count = 0;
    for (String line : input.split("\n")) {
      String[] splitLine = line.split(Pattern.quote(" | "));
      String[] characterSets = splitLine[1].split(" ");
      for (String characterString : characterSets) {
        switch (characterString.length()) {
          case 2, 3, 4, 7 -> count++;
        }
      }
    }

    return count;
  }

  @Override
  public Object partTwo(String input) {
    int value = 0;
    for (String line : input.split("\n")) {
      Map<String, Integer> stringMap = generateStringMap(line);
      String[] splitLine = line.split(Pattern.quote(" | "));
      String[] characterSets = splitLine[1].split(" ");
      StringBuilder integer = new StringBuilder();
      for (String characterString : characterSets) {
        characterString = characterString.chars()
            .sorted()
            .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
            .toString();
        integer.append(stringMap.get(characterString));
      }

      value += Integer.parseInt(integer.toString());
    }

    return value;
  }

  private Map<String, Integer> generateStringMap(String line) {
    Map<String, Integer> map = new HashMap<>();

    List<String> data = new ArrayList<>();
    String[] splitLine = line.split(Pattern.quote(" | "));
    String[] characterSets = splitLine[0].split(" ");

    for (String set : characterSets) {
      set = set.chars()
          .sorted()
          .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
          .toString();
      data.add(set);
    }

    data.sort(Comparator.comparingInt(String::length));

    String one = data.get(0);
    String seven = data.get(1);
    String four = data.get(2);
    String eight = data.get(data.size() - 1);

    map.put(one, 1); // Shortest length is one, unique
    map.put(seven, 7); // Seven is second shortest, unique
    map.put(four, 4); // Four is third shortest, unique
    map.put(eight, 8); // Longest length is 8, unique

    List<String> sixDigits = data.subList(data.size() - 4, data.size() - 1);
    String six = null;
    for (String candidate : sixDigits) {
      if (getCommonCharacters(candidate, one).size() == 1) {
        six = candidate;
        break;
      }
    }
    map.put(six, 6);

    String zero = null;
    for (String candidate : sixDigits) {
      if (candidate.equals(six)) {
        continue;
      }

      List<Character> uncommon = getUncommonCharacters(candidate, six);
      String uncommonJoined = toString(uncommon);
      if (getCommonCharacters(uncommonJoined, four).size() == 2) {
        zero = candidate;
        break;
      }
    }
    map.put(zero, 0);

    String nine = null;
    for (String candidate : sixDigits) {
      if (!candidate.equals(zero) && !candidate.equals(six)) {
        nine = candidate;
        break;
      }
    }
    map.put(nine, 9);

    // 2, 3, 5 are left
    List<String> fiveDigits = data.subList(3, 7);
    String three = null;
    for (String candidate : fiveDigits) {
      if (getCommonCharacters(one, candidate).size() == 2) {
        three = candidate;
        break;
      }
    }
    map.put(three, 3);

    String two = null;
    for (String candidate : fiveDigits) {
      if (getCommonCharacters(four, candidate).size() == 2) {
        two = candidate;
        break;
      }
    }
    map.put(two, 2);

    String five = null;
    for (String candidate : fiveDigits) {
      if (!candidate.equals(two) && !candidate.equals(three)) {
        five = candidate;
        break;
      }
    }
    map.put(five, 5);

    return map;
  }

  private List<Character> getCommonCharacters(String a, String b) {
    List<Character> characters = new ArrayList<>();
    for (int i = 0; i < a.length(); i++) {
      char character = a.charAt(i);
      if (b.indexOf(character) != -1) {
        characters.add(character);
      }
    }

    Collections.sort(characters);

    return characters;
  }

  private List<Character> getUncommonCharacters(String a, String b) {
    List<Character> characters = new ArrayList<>();
    for (int i = 0; i < Math.max(a.length(), b.length()); i++) {
      if (a.length() > i) {
        char character = a.charAt(i);
        if (b.indexOf(character) == -1) {
          characters.add(character);
        }
      }

      if (b.length() > i) {
        char character = b.charAt(i);
        if (a.indexOf(character) == -1 && !characters.contains(character)) {
          characters.add(character);
        }
      }
    }

    Collections.sort(characters);

    return characters;
  }

  private String toString(List<Character> characters) {
    characters = characters.stream().sorted().collect(Collectors.toList());
    StringBuilder builder = new StringBuilder();
    for (char character : characters) {
      builder.append(character);
    }
    return builder.toString();
  }

}

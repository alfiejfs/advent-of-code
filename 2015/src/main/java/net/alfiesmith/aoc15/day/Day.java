package net.alfiesmith.aoc15.day;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public abstract class Day {

  public Day(int day) {
    String input = readInput(day);

    long time = System.nanoTime();
    System.out.println("Part one: " + partOne(input));
    System.out.println("Completed in: " + (System.nanoTime() - time) + "ns");

    time = System.nanoTime();
    System.out.println("Part one: " + partTwo(input));
    System.out.println("Completed in: " + (System.nanoTime() - time) + "ns");


  }

  public abstract Object partOne(String input);

  public abstract Object partTwo(String input);

  private static String readInput(int day) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(day + File.separator + "input.txt");
    if (is == null) {
      return null;
    }

    StringBuilder input = new StringBuilder();

    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    try {
      String line;
      while ((line = reader.readLine()) != null) {
        if (!input.isEmpty()) {
          input.append('\n');
        }
        input.append(line);
      }

      reader.close();
      is.close();
    } catch (IOException err) {
      err.printStackTrace();
    }

    return input.toString();
  }

}

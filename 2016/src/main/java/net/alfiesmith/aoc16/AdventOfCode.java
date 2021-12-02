package net.alfiesmith.aoc16;

import java.util.Scanner;
import net.alfiesmith.aoc16.day.one.DayOne;
import net.alfiesmith.aoc16.day.two.DayTwo;

public class AdventOfCode {

  public static void main(String[] args) {
    System.out.println("Enter day:");
    Scanner scanner = new Scanner(System.in);
    int day = scanner.nextInt();

    switch (day) {
      case 1 -> new DayOne();
      case 2-> new DayTwo();
    }
  }

}

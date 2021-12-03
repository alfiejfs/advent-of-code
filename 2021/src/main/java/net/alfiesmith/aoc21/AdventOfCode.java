package net.alfiesmith.aoc21;

import net.alfiesmith.aoc21.day.one.DayOne;
import java.util.Scanner;
import net.alfiesmith.aoc21.day.three.DayThree;
import net.alfiesmith.aoc21.day.two.DayTwo;

public class AdventOfCode {

  public static void main(String[] args) {
    System.out.println("Enter day: ");
    Scanner scanner = new Scanner(System.in);
    int day = scanner.nextInt();

    switch (day) {
      case 1 -> new DayOne();
      case 2 -> new DayTwo();
      case 3 -> new DayThree();
    }
  }

}

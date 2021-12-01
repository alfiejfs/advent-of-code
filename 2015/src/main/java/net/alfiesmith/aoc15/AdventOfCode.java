package net.alfiesmith.aoc15;

import java.util.Scanner;
import net.alfiesmith.aoc15.day.eight.DayEight;
import net.alfiesmith.aoc15.day.five.DayFive;
import net.alfiesmith.aoc15.day.four.DayFour;
import net.alfiesmith.aoc15.day.one.DayOne;
import net.alfiesmith.aoc15.day.seven.DaySeven;
import net.alfiesmith.aoc15.day.six.DaySix;
import net.alfiesmith.aoc15.day.three.DayThree;
import net.alfiesmith.aoc15.day.two.DayTwo;

public class AdventOfCode {

  public static void main(String[] args) {
    System.out.println("Enter day:");
    Scanner scanner = new Scanner(System.in);
    int day = scanner.nextInt();

    switch (day) {
      case 1 -> new DayOne();
      case 2 -> new DayTwo();
      case 3 -> new DayThree();
      case 4 -> new DayFour();
      case 5 -> new DayFive();
      case 6 -> new DaySix();
      case 7 -> new DaySeven();
      case 8 -> new DayEight();
    }
  }

}

package net.alfiesmith.aoc21;

import net.alfiesmith.aoc21.day.eight.DayEight;
import net.alfiesmith.aoc21.day.five.DayFive;
import net.alfiesmith.aoc21.day.four.DayFour;
import net.alfiesmith.aoc21.day.nine.DayNine;
import net.alfiesmith.aoc21.day.one.DayOne;
import java.util.Scanner;
import net.alfiesmith.aoc21.day.seven.DaySeven;
import net.alfiesmith.aoc21.day.six.DaySix;
import net.alfiesmith.aoc21.day.ten.DayTen;
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
      case 4 -> new DayFour();
      case 5 -> new DayFive();
      case 6 -> new DaySix();
      case 7 -> new DaySeven();
      case 8 -> new DayEight();
      case 9 -> new DayNine();
      case 10 -> new DayTen();
    }
  }

}

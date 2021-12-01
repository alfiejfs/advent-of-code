package net.alfiesmith.aoc15;

import java.util.Scanner;
import net.alfiesmith.aoc15.day.one.DayOne;

public class AdventOfCode {

  public static void main(String[] args) {
    System.out.println("Enter day:");
    Scanner scanner = new Scanner(System.in);
    int day = scanner.nextInt();

    switch (day) {
      case 1 -> new DayOne();
    }
  }

}

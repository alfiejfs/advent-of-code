package net.alfiesmith.aoc21;

import net.alfiesmith.aoc21.day.one.DayOne;
import java.util.Scanner;

public class AdventOfCode {

  public static void main(String[] args) {
    System.out.println("Enter day: ");
    Scanner scanner = new Scanner(System.in);
    int day = scanner.nextInt();

    switch (day) {
      case 1 -> new DayOne();
    }
  }

}

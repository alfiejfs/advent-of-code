package net.alfiesmith.aoc15.day.four;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import jdk.swing.interop.SwingInterOpUtils;
import net.alfiesmith.aoc15.day.Day;

public final class DayFour extends Day {

  public DayFour() {
    super(4);
  }

  @Override
  public Object partOne(String input) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException err) {
      err.printStackTrace();
      return -1;
    }

    int number = 1;
    do {
      String aggregate = input + number;
      digest.reset();
      digest.update(aggregate.getBytes());
      byte[] output = digest.digest();
      BigInteger bigInt = new BigInteger(1, output);
      StringBuilder text = new StringBuilder(bigInt.toString(16));
      while (text.length() < 32) {
        text.insert(0, "0");
      }
      if (text.toString().startsWith("00000")) {
        return number;
      }
      number++;
    } while (true);
  }

  @Override
  public Object partTwo(String input) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException err) {
      err.printStackTrace();
      return -1;
    }

    int number = 1;
    do {
      String aggregate = input + number;
      digest.reset();
      digest.update(aggregate.getBytes());
      byte[] output = digest.digest();
      BigInteger bigInt = new BigInteger(1, output);
      StringBuilder text = new StringBuilder(bigInt.toString(16));
      while (text.length() < 32) {
        text.insert(0, "0");
      }
      if (text.toString().startsWith("000000")) {
        return number;
      }
      number++;
    } while (true);
  }
}

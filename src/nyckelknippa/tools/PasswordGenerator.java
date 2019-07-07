package nyckelknippa.tools;

import java.lang.StringBuilder;

import java.util.ArrayList;

public class PasswordGenerator {

  public PasswordGenerator() {

  }

  public String generate(ArrayList<Character> chars, int length) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < length; i++) {
      result.append(chars.get((int)(Math.random() * chars.size())));
      // System.out.println((int)(Math.random() * chars.size()));
    }
    return result.toString();
  }

}

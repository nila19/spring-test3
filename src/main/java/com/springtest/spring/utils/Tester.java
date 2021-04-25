package com.springtest.spring.utils;

import java.util.Base64;

public class Tester {
  public static void main(String[] args) {
    String originalInput = "12345";
    String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes());
    String decoded = new String(Base64.getDecoder().decode(encodedString.getBytes()));
    System.out.println(encodedString + " ==> " + decoded);
  }
}

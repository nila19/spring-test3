package com.springtest.spring.utils;

import lombok.Generated;
import org.jasypt.util.text.AES256TextEncryptor;

@Generated // exclude from Jacoco test coverage
public class TestEncryptor {
  public static void main(String[] args) {
    System.out.println("TestEncryptor => start");
    String salt = "C0mpl3xS@1t";
    String pwd = "H3ll0W0rld";

    AES256TextEncryptor encryptor = new AES256TextEncryptor();
    encryptor.setPassword(salt);
    String encrypted = encryptor.encrypt(pwd);
    String decrypted = encryptor.decrypt(encrypted);
    System.out.println("@@Original: " + pwd + " @@Encrypted: " + encrypted +
                       " @@Decrypted: " + decrypted);
  }
}

package com.ml.shortener.infraestructure.entrypoints.generator;

import org.springframework.stereotype.Component;

@Component
public class UrlGenerator {

  final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
  final Integer BASE = ALPHABET.length();

  public String fromBase10(Integer i) {
    StringBuilder sb = new StringBuilder("");
    if (i == 0) {
      return "a";
    }
    while (i > 0) {
      i = fromBase10(i, sb);
    }
    return sb.reverse().toString();
  }

  private Integer fromBase10(Integer i, final StringBuilder sb) {
    int rem = i % BASE;
    sb.append(ALPHABET.charAt(rem));
    return i / BASE;
  }

  public Integer toBase10(String str) {
    return toBase10(new StringBuilder(str).reverse().toString().toCharArray());
  }

  private Integer toBase10(char[] chars) {
    int n = 0;
    for (int i = chars.length - 1; i >= 0; i--) {
      n += toBase10(ALPHABET.indexOf(chars[i]), i);
    }
    return n;
  }

  private Integer toBase10(int n, int pow) {
    return n * (int) Math.pow(BASE, pow);
  }

}

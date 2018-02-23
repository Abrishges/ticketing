package com.walmart.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator {

  public boolean isValidEmailAddress(final String email) {

    Pattern pattern;
    Matcher matcher;

    final String EMAIL_PATTERN =
        "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    pattern = Pattern.compile(EMAIL_PATTERN);

    matcher = pattern.matcher(email);
    return matcher.matches();
  }

}


package com.example.BankingApp.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;


@Component
public class EmailValidator {
    private static final String emailPattern="^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

//    private static final String pattern = "^(\\+\\d{1,3}[- ]?)?\\d{10}$";
//    private static final Pattern emailPattern = Pattern.compile(pattern);

    public boolean isValidEmail(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches(emailPattern);
    }
//public boolean isValidEmail(String phoneNumber) {
//    if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
//        return false;
//    }
//    return pattern.matches(emailPattern).matches();
//}
}

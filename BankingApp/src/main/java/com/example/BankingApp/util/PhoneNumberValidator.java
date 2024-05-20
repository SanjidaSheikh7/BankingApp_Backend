package com.example.BankingApp.util;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class PhoneNumberValidator {

    private static final String PHONE_NUMBER_PATTERN ="013\\d{8}|014\\d{8}|015\\d{8}|016\\d{8}|017\\d{8}|018\\d{8}|019\\d{8}|096\\d{9}";

    public boolean isValid(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        return phoneNumber.matches(PHONE_NUMBER_PATTERN);
    }
}



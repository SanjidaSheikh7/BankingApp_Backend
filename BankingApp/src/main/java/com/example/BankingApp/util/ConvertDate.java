package com.example.BankingApp.util;

import com.example.BankingApp.exception.NotValid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class ConvertDate {

    public static final String YYYY_MM_DD= "yyyy-mm-dd";
    public static Date stringToDate(String dateString, String format) {
        if (dateString == null || dateString.isEmpty()) {
            throw new NotValid("Input date string is null or empty");
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new NotValid("Date parsing stringToDate failed for input: " + dateString + " with format: " + format);
        }
    }

    public static String dateToString(Date date, String format) {
        if (date == null) {
            throw new NotValid("Date is null or empty");
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (Exception e) {
            throw new NotValid("Date parsing dateToString failed for input: " + date + " with format: " + format);
        }
    }

    public static int calculateAge(String dateString){
        Date dob=ConvertDate.stringToDate(dateString,ConvertDate.YYYY_MM_DD);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dob);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1; //month start with 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        LocalDate dateOfBirth = LocalDate.of(year, month, day);
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dateOfBirth, currentDate);
        return period.getYears();
    }


}

package com.example.BankingApp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class ConvertDate {

    public static final String DD_MM_YYYY = "dd-MM-yyyy";
    public static Date stringToDate(String dateString, String format) {
        if (dateString == null || dateString.isEmpty()) {
            throw new RuntimeException("Input date string is null or empty");
        }

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException("Date parsing failed for input: " + dateString + " with format: " + format);
        }
    }

    public static String dateToString(Date date, String format) {
        if (date == null) {
            throw new RuntimeException("Date is null or empty");
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(date);
        } catch (Exception e) {
            throw new RuntimeException("Date parsing failed for input: " + date + " with format: " + format);
        }
    }

    public static int calculateAge(String dateString){
        Date dob=ConvertDate.stringToDate(dateString,ConvertDate.DD_MM_YYYY);
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

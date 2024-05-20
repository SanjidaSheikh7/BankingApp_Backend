package com.example.BankingApp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConvertDate {

    public static final String DD_MM_YYYY = "dd-MM-yyyy";

    private static final Logger log = LoggerFactory.getLogger(ConvertDate.class);

    public static Date stringToDate(String dateString, String format){
        if(dateString == null) return null;
        Date date = new Date();
        try{
           date=new SimpleDateFormat(format).parse(dateString);
        }catch(Throwable t){
            log.error(t.getMessage());
        }
        return date;
    }
    public static String dateString(Date date, String format){
        if(date == null) return "";

        String dateString = "";
        try{
            dateString = new SimpleDateFormat(format).format(date);
        }catch(Throwable t){
            log.error(t.getMessage());
        }
        return dateString;
    }
}

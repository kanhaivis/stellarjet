package com.ns.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConstantDateFormate {

    /**
     * returns the Desired date/time format
     */
    public static String getFormattedCalendarBookDate(long millis){
        String pattern = "EEEE dd MMMM, hh:mm a";
//        String pattern = "dd MMM , hh:mm aa";
        // Creating date format
        DateFormat simple = new SimpleDateFormat(pattern);

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(millis);
        return simple.format(result);
    }

    public static String getFormattedCompeltedDate(long millis){
        String pattern = "EEE, dd-MM-yyyy";
        // Creating date format
        DateFormat simple = new SimpleDateFormat(pattern);

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(millis);
        return simple.format(result);
    }

    public static String getFormattedhours(long millis){
        String pattern = "HH:mm";
        // Creating date format
        DateFormat simple = new SimpleDateFormat(pattern);

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(millis);
        return simple.format(result);
    }

    public static String getReachByPlaneHours(long millis){
        String pattern = "hh:mm a";
        // Creating date format
        DateFormat simple = new SimpleDateFormat(pattern);

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(millis);
//        Date result = new Date(millis - 15*60*1000);

        return simple.format(result);
    }

    public static String getFormattedBookingsDate(long millis){
        String pattern = "EEEE, dd MMMM";
        // Creating date format
        DateFormat simple = new SimpleDateFormat(pattern);

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(millis);
        return simple.format(result);
    }

    /**
     * returns the reach by plane hour format YYYY-MM-DD
     */
    public static String getPersonalizationHours(long millis){
        String pattern = "yyyy-MM-dd";
        // Creating date format
        @SuppressLint("SimpleDateFormat") DateFormat simple = new SimpleDateFormat(pattern);

        // Creating date from milliseconds
        // using Date() constructor
        Date result = new Date(millis - 4*60*60*1000);

        return simple.format(result);
    }
}

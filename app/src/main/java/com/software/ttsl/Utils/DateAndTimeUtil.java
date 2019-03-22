package com.software.ttsl.Utils;

import android.text.format.DateFormat;

import java.sql.Time;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateAndTimeUtil {

    public static long currentTimeStamp() {
        return System.currentTimeMillis() - 1000;
    }


    public static  String longToDate(long date) {

        // or you already have long value of date, use this instead of milliseconds variable.
        return DateFormat.format("dd-MM-yyyy", new Date(date)).toString();
    }


    public static  String longToDate1(long date) {

        // or you already have long value of date, use this instead of milliseconds variable.
        return DateFormat.format("dd-MM-yyyy", new Date(date)).toString();
    }


    public static Date stringToDate(String date){
        Date date1 = null;
        try {
           date1 =new SimpleDateFormat("dd/MM/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

       return date1 ;
    }


    public static String getNextDate(String curDate) {
        final SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        try {
            date = format.parse(curDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return format.format(calendar.getTime());
    }


    public static String longToDate(long date, String format){

        return DateFormat.format(format, new Date(date)).toString();

    }


    public static String dateToString(String date){



        Format formatter = new SimpleDateFormat("E, MMM dd yyyy");
        return formatter.format(stringToDate(date));

    }
    public static long DateToLong(String date){
        long milliseconds= 0;
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return milliseconds;
    }


    //make first latter upper

    public static String firstLatterUpper(String word){
        if(word!=null && !word.isEmpty()) {
            return word.substring(0, 1).toUpperCase() + word.substring(1);
        }
        return "";
    }


    private String getCurrentTime() {
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-dd-MM");
        Date now = new Date();
        return simpleDateFormat1.format(now);

    }

    public long dateToLong(String date){

        long milliseconds=0;
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return milliseconds;

    }

    public static long dateToLong(String date, String format){
        long milliseconds=0;
        SimpleDateFormat f = new SimpleDateFormat(format);
        try {
            Date d = f.parse(date);
            milliseconds = d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return milliseconds;

    }

    public static long timeToLong(String time,String format){
        long milliseconds=0;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            Date date = sdf.parse(time);
            milliseconds = date.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return milliseconds;
    }

    public static String getCurrentTimeUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        return dateFormat.format(date);


    }

    public static String longToTime(Long time){
        return DateFormat.format("HH:mm", new Time(time)).toString();

    }

    public static String longToTime(Long time,String format){
        return DateFormat.format(format, new Time(time)).toString();

    }

    public static String getCurrentDateUsingCalendar() {
        Calendar cal = Calendar.getInstance();
        Date date=cal.getTime();
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, MMM dd yyyy");
        return dateFormat.format(date);


    }



}

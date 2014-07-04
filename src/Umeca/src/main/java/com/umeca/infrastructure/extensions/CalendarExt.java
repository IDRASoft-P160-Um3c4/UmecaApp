package com.umeca.infrastructure.extensions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Project: Umeca
 * User: Israel
 * Date: 6/14/14
 * Time: 9:26 PM
 */
public class CalendarExt {

    public static Calendar stringToCalendar(String sCalendar){
        Calendar calendar;
        //2014/MM/DD|14:15
        try{
            String[] arrDateTime = sCalendar.split("\\|");
            String[] arrDate = arrDateTime[0].split("\\/");
            String[] arrTime = arrDateTime[1].split(":");

            calendar = Calendar.getInstance();
            calendar.set(Integer.parseInt(arrDate[0]), Integer.parseInt(arrDate[1]) - 1, Integer.parseInt(arrDate[2]), Integer.parseInt(arrTime[0]), Integer.parseInt(arrTime[1]), 0);

            return calendar;
        }catch (Exception ex){
            return null;
        }
    }

    public static Calendar getToday(){
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);

        return today;
    }

    //2014/MM/DD|14:15
    public static String calendarToString(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd|HH:mm");
        String sCalendar = sdf.format(calendar.getTime());
        return sCalendar;
    }

    public static String calendarToDateString(Calendar calendar) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy|HH:mm");
        String sCalendar = sdf.format(calendar.getTime());
        return sCalendar;
    }

    public static String calendarToFormatString(Calendar calendar, String format) {

        if(calendar == null){
            return "NA";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String sCalendar = sdf.format(calendar.getTime());
        return sCalendar;
    }
}

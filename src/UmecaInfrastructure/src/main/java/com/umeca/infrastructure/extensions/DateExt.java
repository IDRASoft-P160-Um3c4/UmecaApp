package com.umeca.infrastructure.extensions;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateExt {

    public static String dateToString(Date calendar) {

        if(calendar == null)
            return "NA";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String sCalendar = sdf.format(calendar.getTime());
        return sCalendar;
    }

}

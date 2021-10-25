package com.example.atmostask.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static final String DATE_FORMAT_MM = "yyyy-MM-dd HH:mm";

    public static String dateLongToString(long longDate) {
        if (longDate == 0)
            return null;
        return dateLongToStringByFormat(longDate, DATE_FORMAT_MM);
    }

    public static String dateLongToStringByFormat(long longDate, String format) {
        Date date = new Date(longDate);
        SimpleDateFormat df2 = new SimpleDateFormat(format);
        return df2.format(date);
    }
}


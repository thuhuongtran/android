package kr.changhan.mytravels.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    private static final String DATE_PATTERN_DEFAULT = "yyyy/MM/dd";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN_DEFAULT);

    /**
     * Formats a Date into a date string with a default format.
     *
     * @param date the date value to be formatted into a date string.
     * @return the formatted date string.
     */
    public static String getString(Date date) {
        return SDF.format(date);
    }

    /**
     * Formats a time value in milliseconds into a date string with a default format.
     *
     * @param date the milliseconds since January 1, 1970, 00:00:00 GMT.
     * @return the formatted date string.
     */
    public static String getString(long date) {
        return getString(new Date(date));
    }
}

package vtcac.thuhuong.mytrip.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    private static final String DATE_PATTERN_DEFAULT = "yyyy-MM-dd";
    private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN_DEFAULT);

    /**
     * format a date into a default string
     * @param date
     * @return
     */
    public static String getString(Date date) {
        return SDF.format(date);
    }

    /**
     * format a time value in milliseconds into a date string with a default format.
     * @param date
     * @return
     */
    public static String getString(long date) {
        return getString(new Date(date));
    }

}

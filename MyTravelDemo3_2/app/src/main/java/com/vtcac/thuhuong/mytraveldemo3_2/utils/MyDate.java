package com.vtcac.thuhuong.mytraveldemo3_2.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDate {
    public static final String DATE_PATTERN_DEFAULT = "yyyy/MM/dd";
    public static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_PATTERN_DEFAULT);
    public static String getString(Date date) {
        return SDF.format(date);
    }
    public static String getString(long date) {
        return getString(new Date(date));
    }
}

package com.vtcac.thuhuong.mytraveldemo3_2.utils;

public class MyString {
    /**
    * if s is empty return true
    * */
    public static boolean isEmpty(String s) {
        if (s == null || s.trim().length() == 0) return true;
        return false;
    }
    /***
     * if s is not empty return true
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}

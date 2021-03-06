package kr.changhan.mytravels.utils;

public class MyString {

    /**
     * if s is empty, return true
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s == null || s.trim().length() == 0) return true;
        return false;
    }

    /**
     * is s is not empty, return true
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }
}

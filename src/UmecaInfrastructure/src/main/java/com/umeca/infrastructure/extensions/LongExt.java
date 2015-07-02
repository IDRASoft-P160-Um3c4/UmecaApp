package com.umeca.infrastructure.extensions;

public class LongExt {
    public static Long TryParse(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

    public static String paddingLeft(String sPad, String sLen, Long consecutive) {
        if(consecutive == null)
            return "0000";
        return String.format("%" + sPad + sLen + "d", consecutive);
    }
}

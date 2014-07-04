package com.umeca.infrastructure.extensions;

/**
 * Project: Umeca
 * User: Israel
 * Date: 4/30/14
 * Time: 6:07 PM
 */
public class StringExt {
    public static boolean isNullOrWhiteSpace(java.lang.String param) {
        return param == null || param.trim().length() == 0;
    }

    public static String naOnEmpty(String supUserDone) {

        if(isNullOrWhiteSpace(supUserDone))
            return "NA";

        return supUserDone;
    }
}

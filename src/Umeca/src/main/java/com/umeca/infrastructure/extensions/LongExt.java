package com.umeca.infrastructure.extensions;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/20/14
 * Time: 10:15 PM
 */
public class LongExt {
    public static Long TryParse(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}

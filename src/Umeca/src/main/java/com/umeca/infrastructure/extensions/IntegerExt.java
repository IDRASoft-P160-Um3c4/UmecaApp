package com.umeca.infrastructure.extensions;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/20/14
 * Time: 10:15 PM
 */
public class IntegerExt {
    public static Integer TryParse(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}

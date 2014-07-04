package com.umeca.infrastructure.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Project: Umeca
 * User: Israel
 * Date: 5/6/14
 * Time: 6:33 PM
 */
public class BcryptUtil {
    public static String encode(String plainText){
        BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        String hashedPass = passwordEncoder.encode(plainText);
        return hashedPass;
    }

    public static boolean match(String plain, String encode){
        BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        return passwordEncoder.matches(plain, encode);
    }

}

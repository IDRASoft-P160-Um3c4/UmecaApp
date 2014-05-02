package com.umeca.account;

import org.junit.Test;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.lang.System;

public class PasswordHash {

    @Test
    public void testBcrypt(){
        String password = "99630110";
        BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        String hashedPass = passwordEncoder.encode(password);
        System.out.println(hashedPass);
    }

}
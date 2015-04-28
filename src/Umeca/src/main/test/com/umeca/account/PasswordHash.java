package com.umeca.account;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordHash {

    @Test
    public void testBcrypt(){
        String password = "admin2015@..";
        BCryptPasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();
        String hashedPass = passwordEncoder.encode(password);
        System.out.println(hashedPass);
    }

}
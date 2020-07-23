package com.store.writers.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Random;

@Component
public class SecurityUtil {

    // just for demo purposes, Should be somewhere else, protected
    private static final String HASH = "hashingcode";

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12, new SecureRandom(HASH.getBytes()));
    }



    @Bean
    public static String randomPassword()
    {
        String HASHCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder strBuilder = new StringBuilder();
        Random random = new Random();

        while (strBuilder.length()<18) {
            int index = (int) random.nextFloat()*HASHCHARS.length();
            strBuilder.append(HASHCHARS.charAt(index));
        }
        return strBuilder.toString();
    }
}

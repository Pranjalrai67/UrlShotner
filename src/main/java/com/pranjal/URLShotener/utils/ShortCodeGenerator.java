package com.pranjal.URLShotener.utils;


import java.security.SecureRandom;
public class ShortCodeGenerator{
    private static final String CHARACTERS =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    public static String createShortCode(int length){

        StringBuilder shortCode = new StringBuilder();
        for(int i = 0;i<length;i++){
            int index=  random.nextInt(CHARACTERS.length());
            shortCode.append(CHARACTERS.charAt(index));
        }
        return shortCode.toString();
    }
}

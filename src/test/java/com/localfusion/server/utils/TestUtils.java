package com.localfusion.server.utils;

import com.localfusion.server.configuration.SecurityConfiguration;

import java.util.Random;

public class TestUtils {

    public final static String auth = "Authorization";
    public final static String pass = "Basic " + SecurityConfiguration.BASIC_AUTH_CODE;

    public static String getRandomString() {
        return getRandomString(128);
    }

    private static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(str.charAt(random.nextInt(str.length())));
        }
        return stringBuilder.toString();
    }

    public static int getBigInt() {
        return (int) ((1 + Math.random()) * 1e9);
    }

    public static int getRand(int upper) {
        return (int) (upper * Math.random());
    }

}

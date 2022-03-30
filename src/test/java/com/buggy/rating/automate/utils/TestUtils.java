package com.buggy.rating.automate.utils;

import java.util.Random;

public class TestUtils {
    public static String getRandomNumberString() {

        Random rnd = new Random();
        int number = rnd.nextInt(9999);
        return String.format("%06d", number);
    }
}

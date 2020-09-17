package com.example.criminalintent.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

public class DateUtils {
    public static final int YEAR_START = 2000;
    public static final int YEAR_END = 2020;
//    public static final int BEGIN_TIME = 0;
//    public static final int END_TIME = 59;

    public static Date randomDate() {
        GregorianCalendar gc = new GregorianCalendar();
        int year = randBetween(YEAR_START, YEAR_END);
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        Random random = new Random();
//        int hour = randBetween( 0 , 23);
//        int minute = randBetween(BEGIN_TIME, END_TIME);
//        int second = randBetween(BEGIN_TIME, END_TIME);
        for (int i = 0; i < 10; i++) {
            LocalDateTime time = LocalDateTime.of(LocalDate.now(),
                    LocalTime.of(random.nextInt(24), random.nextInt(60),
                            random.nextInt(60), random.nextInt(999999999 + 1)));
            gc.setTimeInMillis(time.toInstant(ZoneOffset.MAX).toEpochMilli());
        }

        return gc.getTime();
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }
//
//    public static void test(){
//        Crime.Builder builder = new Crime.Builder()
//                .setTitle("ali")
//                .setDate(new Date());
//
//        Crime crime = builder.create();
//    }
}

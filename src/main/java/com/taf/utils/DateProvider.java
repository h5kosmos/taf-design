package com.taf.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.Month;
import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class DateProvider {

    public static String getRandomDateOfBirth() {
        LocalDate start = LocalDate.of(1950, Month.JANUARY, 1);
        LocalDate end = LocalDate.now();
        return between(start, end).toString();
    }

    private static LocalDate between(LocalDate startInclusive, LocalDate endExclusive) {
        long startEpochDay = startInclusive.toEpochDay();
        long endEpochDay = endExclusive.toEpochDay();
        long randomDay = ThreadLocalRandom
                .current()
                .nextLong(startEpochDay, endEpochDay);

        return LocalDate.ofEpochDay(randomDay);
    }
}

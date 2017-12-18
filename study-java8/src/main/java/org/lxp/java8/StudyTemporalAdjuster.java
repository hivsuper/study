package org.lxp.java8;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class StudyTemporalAdjuster {
    public static String nextFirday(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, formatter);
        localDate = localDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.FRIDAY));
        return formatter.format(localDate);
    }

    public static String firstDayOfMonth(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, formatter);
        localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return formatter.format(localDate);
    }

    public static String firstDayOfNextMonth(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, formatter);
        localDate = localDate.with(TemporalAdjusters.firstDayOfNextMonth());
        return formatter.format(localDate);
    }

    public static String nextWorkDay(String date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(date, formatter);
        localDate = localDate.with(temporal -> {
            int days = 0;
            switch (DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK))) {
            case FRIDAY:
                days += 3;
                break;
            case SATURDAY:
                days += 2;
                break;
            default:
                days++;
                break;
            }
            return temporal.plus(days, ChronoUnit.DAYS);
        });
        return formatter.format(localDate);
    }
}

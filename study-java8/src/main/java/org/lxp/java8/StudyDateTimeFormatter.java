package org.lxp.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class StudyDateTimeFormatter {
    public static String format(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String format(LocalDate localDate) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendText(ChronoField.DAY_OF_MONTH)
                .appendLiteral(".").appendText(ChronoField.MONTH_OF_YEAR).appendLiteral("^")
                .appendText(ChronoField.YEAR).toFormatter();
        return localDate.format(formatter);
    }
}

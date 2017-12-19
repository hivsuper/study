package org.lxp.java8;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public class StudyLocalDateTime {
    public static String get20170701ViaCalendar(String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 6, 1, 0, 0, 0);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    public static String get20170701ViaLocalDateTime(String format) {
        LocalDateTime localDateTime = LocalDateTime.of(2017, 7, 1, 0, 0);
        return StudyDateTimeFormatter.format(localDateTime, format);
    }

    public static long getMillis(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static LocalDateTime parseViaLocalDateTime(String date, String format) {
        return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(format));
    }

    public static Date parseViaDateFromat(String date, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(date);
    }

    public static LocalDateTime addOneDay(LocalDateTime localDateTime) {
        return localDateTime.plusDays(1);
    }

    public static LocalDateTime setDayOfMonth(LocalDateTime localDateTime, int dayOfMonth) {
        return localDateTime.withDayOfMonth(dayOfMonth);
    }

    public static boolean after(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return localDateTime1.isAfter(localDateTime2);
    }

    public static boolean afterViaCompareTo(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return localDateTime1.compareTo(localDateTime2) > 0;
    }

    public static long differentSeconds(LocalDateTime localDateTime1, LocalDateTime localDateTime2,
            ZoneOffset zoneOffset) {
        return localDateTime1.toEpochSecond(zoneOffset) - localDateTime2.toEpochSecond(zoneOffset);
    }

    public static long differentDays(LocalDateTime localDateTime1, LocalDateTime localDateTime2) {
        return localDateTime1.toLocalDate().toEpochDay() - localDateTime2.toLocalDate().toEpochDay();
    }
}

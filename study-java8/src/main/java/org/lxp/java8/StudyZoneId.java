package org.lxp.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class StudyZoneId {
    public static String format(ZoneId zoneId, LocalDate localDate, String pattern) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(zoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return zonedDateTime.format(formatter);
    }

    /**
     * Convert DateTime from fromZoneId to toZoneId
     * 
     * @param localDateTime
     * @param fromZoneId
     * @param toZoneId
     * @param pattern
     * @return
     */
    public static String withZoneSameInstant(LocalDateTime localDateTime, ZoneId fromZoneId, ZoneId toZoneId,
            String pattern) {
        ZonedDateTime fromDateTime = localDateTime.atZone(fromZoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return fromDateTime.withZoneSameInstant(toZoneId).format(formatter);
    }

    /**
     * Return DateTime with different TimeZone
     * 
     * @param localDateTime
     * @param fromZoneId
     * @param toZoneId
     * @param pattern
     * @return
     */
    public static String withZoneSameLocal(LocalDateTime localDateTime, ZoneId fromZoneId, ZoneId toZoneId,
            String pattern) {
        ZonedDateTime fromDateTime = localDateTime.atZone(fromZoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return fromDateTime.withZoneSameLocal(toZoneId).format(formatter);
    }

    /**
     * use {@link ZoneId#ofOffset(String, ZoneOffset)} to define TimeZone
     * 
     * @param localDateTime
     * @param fromZoneId
     * @param offset
     * @param pattern
     * @return
     */
    public static String withZoneSameInstant(LocalDateTime localDateTime, ZoneId fromZoneId, int offset,
            String pattern) {
        ZonedDateTime fromDateTime = localDateTime.atZone(fromZoneId);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return fromDateTime.withZoneSameInstant(ZoneId.ofOffset("GMT", ZoneOffset.ofHours(offset))).format(formatter);
    }
}

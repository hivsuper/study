package org.lxp.java8;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class StudyLocalDateTimeTest {

    @Test
    public void testGet20170701() throws Exception {
        String format = "yyyy-MM-dd HH:mm:ss";
        Assert.assertEquals(StudyLocalDateTime.get20170701ViaCalendar(format),
                StudyLocalDateTime.get20170701ViaLocalDateTime(format));
        Assert.assertEquals("2017-07-01 00:00:00", StudyLocalDateTime.get20170701ViaLocalDateTime(format));
    }

    @Test
    public void testGetMillisOfNow() throws Exception {
        Assert.assertEquals(System.currentTimeMillis() / 1000,
                StudyLocalDateTime.getMillis(LocalDateTime.now()) / 1000);
    }

    @Test
    public void testParse() throws Exception {
        String format = "yyyy-MM-dd HH:mm:ss";
        String date = "2017-07-01 00:00:00";
        Assert.assertEquals(StudyLocalDateTime.parseViaDateFromat(date, format).getTime(),
                StudyLocalDateTime.getMillis(StudyLocalDateTime.parseViaLocalDateTime(date, format)));
    }

    @Test
    public void testAddOneDay() throws Exception {
        String format = "yyyy-MM-dd HH:mm:ss";
        String date = "2017-07-31 00:00:00";
        Assert.assertEquals("2017-08-01 00:00:00", StudyDateTimeFormatter.format(
                StudyLocalDateTime.addOneDay(StudyLocalDateTime.parseViaLocalDateTime(date, format)), format));
    }

    @Test
    public void testSetDayOfMonth() throws Exception {
        String format = "yyyy-MM-dd HH:mm:ss";
        String date = "2017-07-01 00:00:00";
        Assert.assertEquals("2017-07-15 00:00:00", StudyDateTimeFormatter.format(
                StudyLocalDateTime.setDayOfMonth(StudyLocalDateTime.parseViaLocalDateTime(date, format), 15), format));
    }
}

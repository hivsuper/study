package org.lxp.java8;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

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
        Assert.assertEquals("2017-08-01 00:00:00", StudyDateTimeFormatter
                .format(StudyLocalDateTime.addOneDay(StudyLocalDateTime.parseViaLocalDateTime(date, format)), format));
    }

    @Test
    public void testSetDayOfMonth() throws Exception {
        String format = "yyyy-MM-dd HH:mm:ss";
        String date = "2017-07-01 00:00:00";
        Assert.assertEquals("2017-07-15 00:00:00", StudyDateTimeFormatter.format(
                StudyLocalDateTime.setDayOfMonth(StudyLocalDateTime.parseViaLocalDateTime(date, format), 15), format));
    }

    @Test
    public void testAfter() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2017, 7, 1, 0, 0);
        Assert.assertTrue(StudyLocalDateTime.after(localDateTime, localDateTime.minusSeconds(1)));
    }

    @Test
    public void testAfterViaCompareTo() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2017, 7, 1, 0, 0);
        Assert.assertFalse(StudyLocalDateTime.afterViaCompareTo(localDateTime.minusSeconds(1), localDateTime));
        Assert.assertTrue(StudyLocalDateTime.afterViaCompareTo(localDateTime.plusSeconds(1), localDateTime));
        Assert.assertTrue(StudyLocalDateTime.afterViaCompareTo(localDateTime.plusNanos(1), localDateTime));
    }

    @Test
    public void testDifferentSeconds() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2017, 7, 1, 0, 0);
        Assert.assertEquals(-1L, StudyLocalDateTime.differentSeconds(localDateTime.minusSeconds(1), localDateTime,
                ZoneOffset.ofHours(6)));
    }

    @Test
    public void testDifferentDays() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2017, 6, 1, 0, 0);
        Assert.assertEquals(-1L, StudyLocalDateTime.differentDays(localDateTime.minusSeconds(1), localDateTime));
        Assert.assertEquals(0L, StudyLocalDateTime.differentDays(localDateTime.plusSeconds(1), localDateTime));
        Assert.assertEquals(30L, StudyLocalDateTime.differentDays(localDateTime.plusMonths(1), localDateTime));
    }
}

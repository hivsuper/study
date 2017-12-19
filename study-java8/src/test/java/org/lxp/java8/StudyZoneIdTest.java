package org.lxp.java8;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimeZone;

import org.junit.Assert;
import org.junit.Test;

public class StudyZoneIdTest {

    @Test
    public void testMain() throws Exception {
        Assert.assertEquals(TimeZone.getTimeZone("Asia/Shanghai").toZoneId(), ZoneId.of("Asia/Shanghai"));
    }

    @Test
    public void testFormatZonedDateTimeZoneIdString() throws Exception {
        Assert.assertEquals("2017-01-01 00:00:00+0800",
                StudyZoneId.format(ZoneId.of("Asia/Shanghai"), LocalDate.of(2017, 1, 1), "yyyy-MM-dd HH:mm:ssZ"));
    }

    @Test
    public void testWithZoneSameInstant() throws Exception {
        Assert.assertEquals("2017-12-19 04:49:00-0600",
                StudyZoneId.withZoneSameInstant(LocalDateTime.of(2017, 12, 19, 18, 49), ZoneId.of("GMT+8"),
                        ZoneId.of("UTC-6"), "yyyy-MM-dd HH:mm:ssZ"));
    }

    @Test
    public void testWithZoneSameLocal() throws Exception {
        Assert.assertEquals("2017-12-19 18:49:00-0600",
                StudyZoneId.withZoneSameLocal(LocalDateTime.of(2017, 12, 19, 18, 49), ZoneId.of("GMT+8"),
                        ZoneId.of("UTC-6"), "yyyy-MM-dd HH:mm:ssZ"));
    }

    @Test
    public void testWithZoneSameInstantLocalDateTimeZoneIdIntString() throws Exception {
        Assert.assertEquals("2017-12-19 04:49:00-0600", StudyZoneId.withZoneSameInstant(
                LocalDateTime.of(2017, 12, 19, 18, 49), ZoneId.of("GMT+8"), -6, "yyyy-MM-dd HH:mm:ssZ"));
    }
}

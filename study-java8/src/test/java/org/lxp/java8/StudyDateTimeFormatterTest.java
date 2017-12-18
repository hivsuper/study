package org.lxp.java8;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

public class StudyDateTimeFormatterTest {

    @Test
    public void testFormatLocalDateString() throws Exception {
        Assert.assertEquals("2017/01/01", StudyDateTimeFormatter.format(LocalDate.of(2017, 1, 1), "yyyy/MM/dd"));
    }

    @Test
    public void testFormatLocalDateTimeString() throws Exception {
        Assert.assertEquals("2017-01-01 00:00:00",
                StudyDateTimeFormatter.format(LocalDate.of(2017, 1, 1).atStartOfDay(), "yyyy-MM-dd HH:mm:ss"));
    }

    @Test
    public void testFormatLocalDate() throws Exception {
        Assert.assertEquals("1.一月^2017", StudyDateTimeFormatter.format(LocalDate.of(2017, 1, 1)));
    }

}

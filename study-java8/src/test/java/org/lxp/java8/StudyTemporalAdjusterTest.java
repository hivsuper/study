package org.lxp.java8;

import org.junit.Assert;
import org.junit.Test;

public class StudyTemporalAdjusterTest {

    @Test
    public void testNextFirday() throws Exception {
        Assert.assertEquals("2017/12/22", StudyTemporalAdjuster.nextFirday("2017/12/18", "yyyy/MM/dd"));
    }

    @Test
    public void testFirstDayOfMonth() throws Exception {
        Assert.assertEquals("2017/12/01", StudyTemporalAdjuster.firstDayOfMonth("2017/12/18", "yyyy/MM/dd"));
    }

    @Test
    public void testFirstDayOfNextMonth() throws Exception {
        Assert.assertEquals("2018/01/01", StudyTemporalAdjuster.firstDayOfNextMonth("2017/12/18", "yyyy/MM/dd"));
    }

    @Test
    public void testNextWorkDay() throws Exception {
        Assert.assertEquals("2017/12/19", StudyTemporalAdjuster.nextWorkDay("2017/12/18", "yyyy/MM/dd"));
        Assert.assertEquals("2017/12/25", StudyTemporalAdjuster.nextWorkDay("2017/12/22", "yyyy/MM/dd"));
        Assert.assertEquals("2017/12/25", StudyTemporalAdjuster.nextWorkDay("2017/12/23", "yyyy/MM/dd"));
        Assert.assertEquals("2017/12/25", StudyTemporalAdjuster.nextWorkDay("2017/12/24", "yyyy/MM/dd"));
    }

}

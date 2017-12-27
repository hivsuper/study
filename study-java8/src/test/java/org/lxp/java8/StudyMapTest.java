package org.lxp.java8;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudyMapTest {
    private static Map<String, Integer> map;

    @Before
    public void setUp() throws Exception {
        map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", null);
    }

    @Test
    public void testComputeIfAbsent() throws Exception {
        Assert.assertEquals(3, StudyMap.computeIfAbsent(map, "3"));
        Assert.assertEquals(0, StudyMap.computeIfAbsent(map, "5"));
    }

    @Test
    public void testComputeIfPresent() throws Exception {
        Assert.assertEquals(4, StudyMap.computeIfPresent(map, "3"));
        Assert.assertEquals(5, StudyMap.computeIfPresent(map, "3"));
    }

    @Test
    public void testCompute() throws Exception {
        Assert.assertEquals(4, StudyMap.compute(map, "3"));
        Assert.assertEquals(0, StudyMap.compute(map, "5"));
    }

    @Test
    public void testPutIfAbsent() throws Exception {
        Assert.assertThat(StudyMap.putIfAbsent(map, "3", 4), CoreMatchers.is(3));

        Assert.assertThat(StudyMap.putIfAbsent(map, "4", 4), CoreMatchers.nullValue());
        Assert.assertThat(map.get("4"), CoreMatchers.is(4));

        Assert.assertThat(StudyMap.putIfAbsent(map, "5", 4), CoreMatchers.nullValue());
        Assert.assertThat(map.get("5"), CoreMatchers.is(4));
    }

}

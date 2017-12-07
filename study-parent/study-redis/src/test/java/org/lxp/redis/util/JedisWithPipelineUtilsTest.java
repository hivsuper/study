package org.lxp.redis.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JedisWithPipelineUtilsTest {
    private final String key = "test_pipeline_sorted_set";

    @Before
    public void setUp() {
        JedisWithPipelineUtils.del(key);
    }

    @Test
    public void doTest() throws Exception {
        List<String> list = Arrays.asList("1", "2");
        for (String s : list) {
            Assert.assertEquals(1, JedisWithPipelineUtils.zadd(key, 1, s));
        }
        Assert.assertEquals("[1, 2]", JedisWithPipelineUtils.zrange(key, 0, list.size() * 2).toString());
        Assert.assertEquals(2, JedisWithPipelineUtils.zrem(key, list));
    }

    @Test
    public void testZpop() throws Exception {
        List<String> list = Arrays.asList("1", "2");
        int size = list.size() * 2;
        for (String s : list) {
            Assert.assertEquals(1, JedisWithPipelineUtils.zadd(key, 1, s));
        }
        Assert.assertEquals("[1, 2]", JedisWithPipelineUtils.zpop(key, size).toString());
        Assert.assertTrue(JedisWithPipelineUtils.zrange(key, 0, size).isEmpty());
    }

    @Test
    public void testBatchZadd() throws Exception {
        // fake data
        final int size = 10000;
        Map<String, Double> values = new HashMap<>();
        for (int index = 0; index < size; index++) {
            values.put(String.valueOf(index), Double.valueOf(index + 0.1));
        }
        // round 1
        long startTime1 = System.currentTimeMillis();
        JedisWithPipelineUtils.zadd(key, values);
        long costTime1 = System.currentTimeMillis() - startTime1;
        // round 2
        long startTime2 = System.currentTimeMillis();
        JedisUtils.zadd(key, values);
        long costTime2 = System.currentTimeMillis() - startTime2;
        // verify
        System.out.println("costTime1:" + costTime1 + ", costTime2:" + costTime2);
        Assert.assertTrue(costTime2 > costTime1);
    }
}

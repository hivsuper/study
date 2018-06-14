package org.lxp.redis.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JedisWithPipelineUtilsIT {
    private final String key = "test_pipeline_sorted_set";
    private JedisUtils jedisUtils;
    private JedisWithPipelineUtils jedisWithPipelineUtils;

    @Before
    public void setUp() {
        jedisUtils = new JedisUtils();
        jedisWithPipelineUtils = new JedisWithPipelineUtils(jedisUtils);
        jedisWithPipelineUtils.del(key);
    }

    @Test
    public void doTest() throws Exception {
        List<String> list = Arrays.asList("1", "2");
        for (String s : list) {
            Assert.assertEquals(1, jedisWithPipelineUtils.zadd(key, 1, s));
        }
        Assert.assertEquals("[1, 2]", jedisWithPipelineUtils.zrange(key, 0, list.size() * 2).toString());
        Assert.assertEquals(2, jedisWithPipelineUtils.zrem(key, list));
    }

    @Test
    public void testZpop() throws Exception {
        List<String> list = Arrays.asList("1", "2");
        int size = list.size() * 2;
        for (String s : list) {
            Assert.assertEquals(1, jedisWithPipelineUtils.zadd(key, 1, s));
        }
        Assert.assertEquals("[1, 2]", jedisWithPipelineUtils.zpop(key, size).toString());
        Assert.assertTrue(jedisWithPipelineUtils.zrange(key, 0, size).isEmpty());
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
        jedisWithPipelineUtils.zadd(key, values);
        long costTime1 = System.currentTimeMillis() - startTime1;
        // round 2
        long startTime2 = System.currentTimeMillis();
        jedisUtils.zadd(key, values);
        long costTime2 = System.currentTimeMillis() - startTime2;
        // verify
        System.out.println("costTime1:" + costTime1 + ", costTime2:" + costTime2);
        Assert.assertTrue(costTime2 > costTime1);
    }

    @Test
    public void testBatch() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("batch_1", "1");
        map.put("batch_2", "2");
        map.put("batch_3", "3");
        map.put("batch_4", "4");
        Assert.assertEquals("{batch_2=OK, batch_1=OK, batch_4=OK, batch_3=OK}",
                jedisWithPipelineUtils.setBatch(map).toString());
        Assert.assertEquals("{batch_2=2, batch_1=1, batch_4=4, batch_3=3}",
                jedisWithPipelineUtils.getBatch(map.keySet()).toString());
        Assert.assertEquals("{batch_2=1, batch_1=1, batch_4=1, batch_3=1}",
                jedisWithPipelineUtils.delBatch(map.keySet()).toString());
    }
}

package org.lxp.redis.util;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.BuilderFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

public class JedisWithPipelineUtilsTest {
    private final String key = "test_pipeline_sorted_set";
    private JedisUtils jedisUtils;
    private JedisWithPipelineUtils jedisWithPipelineUtils;

    @Before
    public void setUp() {
        jedisUtils = mock(JedisUtils.class);
        jedisWithPipelineUtils = new JedisWithPipelineUtils(jedisUtils);
    }

    @Test
    public void testDel() throws Exception {
        Jedis jedis = mock(Jedis.class);
        Pipeline pipeline = mock(Pipeline.class);
        doReturn(jedis).when(jedisUtils).getResource();
        doReturn(pipeline).when(jedis).pipelined();
        Response<Long> response = new Response<>(BuilderFactory.LONG);
        response.set(1L);
        doReturn(response).when(pipeline).del(key);
        // execute and verify
        Assert.assertThat(jedisWithPipelineUtils.del(key), Matchers.is(1L));
    }
}

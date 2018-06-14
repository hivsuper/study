package org.lxp.redis.util;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import ai.grakn.redismock.RedisServer;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * https://dzone.com/articles/java-redis-mock
 */
public class JedisUtilsTest {
    private RedisServer redisServer;
    private JedisPool jedisPool;

    @Before
    public void setUp() throws Exception {
        redisServer = RedisServer.newRedisServer();
        redisServer.start();
        jedisPool = new JedisPool(redisServer.getHost(), redisServer.getBindPort());
    }

    /**
     * Unit test via redis-mock
     */
    @Test
    public void testSetAndGetViaRedisMock() throws Exception {
        final String key = "11";
        JedisUtils jedisUtils = new JedisUtils(jedisPool);
        assertEquals("OK", jedisUtils.set(key, "1"));
        assertEquals("1", jedisUtils.get(key));
    }

    /**
     * Unit test via Mockito
     */
    @Test
    public void testSetAndGetViaMockito() throws Exception {
        final String key = "11";
        // given
        jedisPool = mock(JedisPool.class);
        Jedis jedis = mock(Jedis.class);
        doReturn(jedis).when(jedisPool).getResource();
        doReturn("OK").when(jedis).set(eq(key), eq("1"));
        doReturn("1").when(jedis).get(eq(key));
        // execute and verify
        JedisUtils jedisUtils = new JedisUtils(jedisPool);
        assertEquals("OK", jedisUtils.set(key, "1"));
        assertEquals("1", jedisUtils.get(key));
    }
}

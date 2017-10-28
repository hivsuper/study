package org.lxp.redis.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class JedisUtils {
    static final String PASSWORD = "testpwd";
    static final int PORT = 6379;
    static final String HOST = "192.168.68.24";
    private static final Logger LOG = LoggerFactory.getLogger(JedisUtils.class);
    private static final JedisPool POOL;

    static {
        JedisPoolConfig config = new JedisPoolConfig();// Jedis池配置
        config.setMaxIdle(1000 * 60);// 对象最大空闲时间
        config.setTestOnBorrow(true);
        POOL = new JedisPool(config, HOST, PORT, 0, PASSWORD);
    }

    /**
     * 发布一个消息
     * 
     * @param channel
     * @param message
     */
    public static void publishMsg(String channel, String message) {
        Jedis jedis = null;
        try {
            jedis = getResource();

            jedis.publish(channel, message);
            LOG.debug("publishMsg {} = {}", channel, message);
        } catch (Exception e) {
            LOG.error("publishMsg {} = {}", channel, message, e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 发布一个消息
     * 
     * @param channel
     * @param message
     */
    public static void publishMsg(byte[] channel, byte[] message) {
        Jedis jedis = null;
        try {
            jedis = getResource();

            jedis.publish(channel, message);
            LOG.debug("publishMsg {} = {}", channel, message);
        } catch (Exception e) {
            LOG.error("publishMsg {} = {}", channel, message, e);
        } finally {
            returnResource(jedis);
        }
    }

    /**
     * 接收消息。在main方法调用后，会一直执行下去。当有发布对应消息时，就会在jedisPubSub中接收到！
     * 
     * @param jedisPubSub
     * @param channels
     */
    public static void subscribeMsg(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = null;
        try {
            jedis = getResource();
            jedis.subscribe(jedisPubSub, channels);
            LOG.debug("subscribeMsg {} = {}", jedisPubSub, channels);
        } catch (Exception e) {
            LOG.error("subscribeMsg {} = {}", jedisPubSub, channels, e);
        } finally {
            returnResource(jedis);
        }
    }

    private static Jedis getResource() {
        return POOL.getResource();
    }

    private static void returnResource(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}

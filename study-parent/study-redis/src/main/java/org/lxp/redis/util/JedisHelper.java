package org.lxp.redis.util;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

public class JedisHelper {
    private static final JedisPool POOL;

    static {
        JedisPoolConfig config = new JedisPoolConfig();// Jedis池配置
        config.setMaxIdle(1000 * 60);// 对象最大空闲时间
        config.setTestOnBorrow(true);
        POOL = new JedisPool(config, JedisUtils.HOST, JedisUtils.PORT, 0, JedisUtils.PASSWORD);
    }

    public static void subscribe(JedisPubSub jedisPubSub, String channel) {
        jedisPubSub.proceed(POOL.getResource().getClient(), channel);
    }

    public static void unsubscribe(JedisPubSub jedisPubSub, String channel) {
        jedisPubSub.unsubscribe(channel);
    }

    public static void publishMsg(String channel, String message) {
        POOL.getResource().publish(channel, message);
    }
}

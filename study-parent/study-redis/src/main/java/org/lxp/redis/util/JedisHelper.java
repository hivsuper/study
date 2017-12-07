package org.lxp.redis.util;

import static org.lxp.redis.util.JedisUtils.POOL;

import redis.clients.jedis.JedisPubSub;

public class JedisHelper {
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

package org.lxp.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class JedisHelper {
    private JedisUtils jedisUtils;

    public JedisHelper(JedisUtils jedisUtils) {
        this.jedisUtils = jedisUtils;
    }

    public void subscribe(JedisPubSub jedisPubSub, String channel) {
        try (Jedis jedis = jedisUtils.getResource()) {
            jedisPubSub.proceed(jedis.getClient(), channel);
        }
    }

    public void unsubscribe(JedisPubSub jedisPubSub, String channel) {
        jedisPubSub.unsubscribe(channel);
    }

    public void publishMsg(String channel, String message) {
        try (Jedis jedis = jedisUtils.getResource()) {
            jedis.publish(channel, message);
        }
    }
}

package org.lxp.redis.pubsub;

import redis.clients.jedis.BinaryJedisPubSub;

public class RedisBinaryJedisPubSubListener extends BinaryJedisPubSub {
    @Override
    public void onMessage(byte[] channel, byte[] message) {
    }

    @Override
    public void onPMessage(byte[] pattern, byte[] channel, byte[] message) {
    }
}

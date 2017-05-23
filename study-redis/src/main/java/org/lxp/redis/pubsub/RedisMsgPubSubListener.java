package org.lxp.redis.pubsub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPubSub;

public class RedisMsgPubSubListener extends JedisPubSub {
    private static final Logger LOG = LoggerFactory.getLogger(RedisMsgPubSubListener.class);

    @Override
    public void onMessage(String channel, String message) {
        LOG.info("onMessage:channel[" + channel + "],message[" + message + "]");
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        LOG.info("onPMessage:pattern[" + pattern + "],channel[" + channel + "],message[" + message + "]");
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        LOG.info("onSubscribe: channel[" + channel + "],subscribedChannels[" + subscribedChannels + "]");
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        LOG.info("onUnsubscribe: channel[" + channel + "],subscribedChannels[" + subscribedChannels + "]");
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        LOG.info("onPUnsubscribe: pattern[" + pattern + "],subscribedChannels[" + subscribedChannels + "]");

    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        LOG.info("onPSubscribe: pattern[" + pattern + "],subscribedChannels[" + subscribedChannels + "]");
    }
}

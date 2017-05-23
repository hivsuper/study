package org.lxp.redis.pubsub;

import java.util.concurrent.CountDownLatch;

import org.junit.BeforeClass;
import org.junit.Test;
import org.lxp.redis.util.JedisHelper;
import org.lxp.redis.util.JedisUtils;

public class RedisMsgPubSubListenerTest {
    private static final String NEWS_SHARE_CHANNEL = "news.share";
    private static RedisMsgPubSubListener pubsub;

    @BeforeClass
    public static void setUp() {
        pubsub = new RedisMsgPubSubListener();
    }

    @Test
    public void testSubscribeMsg() throws InterruptedException {
        JedisUtils.subscribeMsg(pubsub, NEWS_SHARE_CHANNEL);
    }

    @Test
    public void testPublishMsg() {
        JedisUtils.publishMsg(NEWS_SHARE_CHANNEL, "测试sssssssssss#@$%&*()*&");
    }

    @Test
    public void testUnsubscribe() throws InterruptedException {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        new Thread(new Runnable() {
            @Override
            public void run() {
                JedisHelper.subscribe(pubsub, NEWS_SHARE_CHANNEL);
                countDownLatch.countDown();
            }
        }, "sub").start();
        countDownLatch.await();
        // subscribe always keep the thread blocked. still don't know why
        JedisHelper.unsubscribe(pubsub, NEWS_SHARE_CHANNEL);
    }
}

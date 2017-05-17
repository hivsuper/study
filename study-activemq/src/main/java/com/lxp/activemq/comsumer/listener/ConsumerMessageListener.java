package com.lxp.activemq.comsumer.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lxp.activemq.common.model.CustomMessage;

public class ConsumerMessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerMessageListener.class);

    public void onMessage(CustomMessage message) {
        LOG.info("consumer accept object message={}", message);
    }

    public void onMessage(String message) {
        LOG.info("consumer accept text message={}", message);
    }
}
package com.lxp.activemq.comsumer.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerMessageListener implements MessageListener {
    private static final Logger LOG = LoggerFactory.getLogger(ConsumerMessageListener.class);

    public void onMessage(Message message) {
        TextMessage textMsg = (TextMessage) message;
        try {
            LOG.info("consumer accept message={}", textMsg.getText());
        } catch (JMSException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
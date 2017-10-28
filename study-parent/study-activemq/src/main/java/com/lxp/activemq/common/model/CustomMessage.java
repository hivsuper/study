package com.lxp.activemq.common.model;

import java.io.Serializable;
import java.util.Date;

public class CustomMessage implements Serializable {
    private static final long serialVersionUID = 1450987654L;
    private String sender;
    private String receiver;
    private String content;
    private Date effectiveTime;
    private Date expiredTime;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Date effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getExpiredTime() {
        return expiredTime;
    }

    public void setExpiredTime(Date expiredTime) {
        this.expiredTime = expiredTime;
    }

    @Override
    public String toString() {
        return "CustomMessage [sender=" + sender + ", receiver=" + receiver + ", content=" + content
                + ", effectiveTime=" + effectiveTime + ", expiredTime=" + expiredTime + "]";
    }
}

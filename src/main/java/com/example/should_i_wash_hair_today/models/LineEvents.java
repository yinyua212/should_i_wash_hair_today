package com.example.should_i_wash_hair_today.models;

import org.springframework.stereotype.Component;

@Component
public class LineEvents {
    private String type;

    private LineMessage message;

    private long timestamp;

    private LineSource source;

    private String replyToken;

    private String mode;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LineMessage getMessage() {
        return message;
    }

    public void setMessage(LineMessage message) {
        this.message = message;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public LineSource getSource() {
        return source;
    }

    public void setSource(LineSource source) {
        this.source = source;
    }

    public String getReplyToken() {
        return replyToken;
    }

    public void setReplyToken(String replyToken) {
        this.replyToken = replyToken;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

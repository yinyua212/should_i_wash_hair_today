package com.example.should_i_wash_hair_today.models;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LineReplyMessage {
    private String replyToken;

    private List<LineMessage> messages;

    public String getReplyToken() {
        return replyToken;
    }

    public void setReplyToken(String replyToken) {
        this.replyToken = replyToken;
    }

    public List<LineMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<LineMessage> messages) {
        this.messages = messages;
    }
}

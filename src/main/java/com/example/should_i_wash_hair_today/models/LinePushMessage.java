package com.example.should_i_wash_hair_today.models;


import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LinePushMessage {
    private String to;

    private List<LineMessage> messages;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public List<LineMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<LineMessage> messages) {
        this.messages = messages;
    }
}

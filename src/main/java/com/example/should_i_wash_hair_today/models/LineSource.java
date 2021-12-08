package com.example.should_i_wash_hair_today.models;

import org.springframework.stereotype.Component;

@Component
public class LineSource {
    private String type;

    private String userId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

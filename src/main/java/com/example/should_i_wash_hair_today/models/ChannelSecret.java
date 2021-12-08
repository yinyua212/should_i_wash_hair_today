package com.example.should_i_wash_hair_today.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ChannelSecret {
    private String token;

    @Value("${channel.secret.token}")
    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}

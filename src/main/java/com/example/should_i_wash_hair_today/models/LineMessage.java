package com.example.should_i_wash_hair_today.models;

import org.springframework.stereotype.Component;

@Component
public class LineMessage {
    private String type;

    private String id;

    private String text;

    private String contents;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}

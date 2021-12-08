package com.example.should_i_wash_hair_today.models;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LineRequestBody{
    private String destination;

    private List<LineEvents> events;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<LineEvents> getEvents() {
        return events;
    }

    public void setEvents(List<LineEvents> events) {
        this.events = events;
    }
}

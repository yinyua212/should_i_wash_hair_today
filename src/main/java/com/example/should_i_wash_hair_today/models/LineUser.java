package com.example.should_i_wash_hair_today.models;

import lombok.AllArgsConstructor;

import javax.persistence.*;


@Entity
@Table(name="line_user")
@AllArgsConstructor
public class LineUser {

    public LineUser() {
    }




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name ="line_id")
    private String lineId;

    @Column(name = "last_wash_date")
    private String lastWashDate;

    @Column(name ="frequency")
    private int frequency;

    @Column(name ="follow")
    private boolean follow;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLineId() {
        return lineId;
    }

    public void setLineId(String lineId) {
        this.lineId = lineId;
    }

    public String getLastWashDate() {
        return lastWashDate;
    }

    public void setLastWashDate(String lastWashDate) {
        this.lastWashDate = lastWashDate;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public boolean isFollow() {
        return follow;
    }

    public void setFollow(boolean follow) {
        this.follow = follow;
    }
}

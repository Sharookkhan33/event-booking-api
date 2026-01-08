package com.maddyverse.event_booking_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "events")
public class Event {

    @Id
    private int id;

    private String name;
    private int available_slots;

    // getters & setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAvailable_slots() {
        return available_slots;
    }
    public void setAvailable_slots(int available_slots) {
        this.available_slots = available_slots;
    }
}

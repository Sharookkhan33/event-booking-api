package com.maddyverse.event_booking_api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 🔥 Better for H2
    private Long id;

    // 🔗 Many bookings → one event
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    @JsonIgnore // 🔥 CRITICAL FIX
    private Event event;

    private int quantity;

    private LocalDateTime bookedAt;

    public Booking() {}

    public Booking(Event event, int quantity) {
        this.event = event;
        this.quantity = quantity;
        this.bookedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getBookedAt() {
        return bookedAt;
    }

    public void setBookedAt(LocalDateTime bookedAt) {
        this.bookedAt = bookedAt;
    }
}

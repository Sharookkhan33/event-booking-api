package com.maddyverse.event_booking_api.dto;

import jakarta.validation.constraints.Min;

public class BookingRequest {

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

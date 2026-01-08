package com.maddyverse.event_booking_api.controller;

import com.maddyverse.event_booking_api.model.Event;
import com.maddyverse.event_booking_api.model.Booking;
import com.maddyverse.event_booking_api.repository.EventRepository;
import com.maddyverse.event_booking_api.repository.BookingRepository;
import com.maddyverse.event_booking_api.dto.ApiResponse;
import com.maddyverse.event_booking_api.dto.BookingRequest;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;   // ✅ REQUIRED IMPORT

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;

    public EventController(EventRepository eventRepository,
                           BookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }

    // ✅ GET all events
    @GetMapping
    public ResponseEntity<?> getAllEvents() {
        return ResponseEntity.ok(
            new ApiResponse(true, "Events fetched successfully",
                    eventRepository.findAll())
        );
    }

    // ✅ GET event by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getEventById(@PathVariable int id) {
        return eventRepository.findById(id)
                .map(event -> ResponseEntity.ok(
                        new ApiResponse(true, "Event found", event)
                ))
                .orElse(ResponseEntity.status(404).body(
                        new ApiResponse(false, "Event not found", null)
                ));
    }

    // ✅ POST book tickets
  @PostMapping("/{id}/book")
public ResponseEntity<?> bookEvent(
        @PathVariable int id,
        @Valid @RequestBody BookingRequest request) {

    Event event = eventRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Event not found"));

    if (request.getQuantity() <= 0) {
        return ResponseEntity.badRequest().body(
            new ApiResponse(false, "Quantity must be at least 1", null)
        );
    }

    if (event.getAvailable_slots() < request.getQuantity()) {
        return ResponseEntity.badRequest().body(
            new ApiResponse(false,
                "Only " + event.getAvailable_slots() + " tickets available", null)
        );
    }

    // ✅ Update slots
    event.setAvailable_slots(event.getAvailable_slots() - request.getQuantity());
    eventRepository.save(event);

    // ✅ Save booking
    Booking booking = new Booking(event, request.getQuantity());
    bookingRepository.save(booking);

    // ✅ Return safe DTO / Map instead of Booking entity
    return ResponseEntity.status(201).body(
        new ApiResponse(true, "🎉 Tickets booked successfully", Map.of(
            "booking_id", booking.getId(),
            "event_name", event.getName(),
            "quantity", booking.getQuantity(),
            "booked_at", booking.getBookedAt(),
            "remaining_slots", event.getAvailable_slots()
        ))
    );
}

}

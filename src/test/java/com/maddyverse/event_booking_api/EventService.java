package com.maddyverse.event_booking_api;

import com.maddyverse.event_booking_api.model.Booking;
import com.maddyverse.event_booking_api.model.Event;
import com.maddyverse.event_booking_api.repository.BookingRepository;
import com.maddyverse.event_booking_api.repository.EventRepository;

import org.springframework.stereotype.Service;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;

    public EventService(EventRepository eventRepository,
                        BookingRepository bookingRepository) {
        this.eventRepository = eventRepository;
        this.bookingRepository = bookingRepository;
    }

    public Booking bookEvent(Event event, int quantity) {
        if (event.getAvailable_slots() < quantity) {
            throw new IllegalArgumentException("Not enough slots");
        }

        event.setAvailable_slots(event.getAvailable_slots() - quantity);
        eventRepository.save(event);

        Booking booking = new Booking(event, quantity);
        return bookingRepository.save(booking);
    }
}

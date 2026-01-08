package com.maddyverse.event_booking_api;

import com.maddyverse.event_booking_api.model.Event;

import com.maddyverse.event_booking_api.repository.EventRepository;
import com.maddyverse.event_booking_api.repository.BookingRepository;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class EventServiceTest {

    @Test
    void bookingReducesAvailableSlots() {
        Event event = new Event();
        event.setAvailable_slots(10);

        EventRepository eventRepo = Mockito.mock(EventRepository.class);
        BookingRepository bookingRepo = Mockito.mock(BookingRepository.class);

        EventService service = new EventService(eventRepo, bookingRepo);

        service.bookEvent(event, 3);

        assertEquals(7, event.getAvailable_slots());
    }

    @Test
    void bookingFailsIfNotEnoughSlots() {
        Event event = new Event();
        event.setAvailable_slots(2);

        EventRepository eventRepo = Mockito.mock(EventRepository.class);
        BookingRepository bookingRepo = Mockito.mock(BookingRepository.class);

        EventService service = new EventService(eventRepo, bookingRepo);

        assertThrows(IllegalArgumentException.class, () -> {
            service.bookEvent(event, 5);
        });
    }
}

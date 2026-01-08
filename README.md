<<<<<<< HEAD
# event-booking-api
A Spring Boot REST API for managing and booking tickets for events. Includes endpoints to list events, get event details, and book tickets with real-time slot updates. Uses H2 in-memory database for development and testing.
=======
# Event Booking API

A simple **Spring Boot** REST API for booking tickets to events.  
It uses **H2 in-memory database** for storage.

---

## Features

- List all events
- Get event details by ID
- Book tickets for an event
- Shows remaining available slots
- Validations for ticket quantity

---

## Technologies

- Java 25
- Spring Boot 4.x
- Spring Data JPA
- H2 Database
- Maven

---

## How to Run

1. **Clone the repository:**

```bash
git clone https://github.com/Sharookkhan33/event-booking-api.git
cd event-booking-api
 
2. **Build and run the project:**

mvn clean install
mvn spring-boot:run

3. Access the API:

Base URL: http://localhost:8080/api/events

H2 Console: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (leave empty)

API Endpoints
Method	Endpoint	               Description
GET	    /api/events	               List all events
GET	    /api/events/{id}	       Get event details
POST	/api/events/{id}/book	   Book tickets for an event

POST request body example:

{
  "quantity": 2
}


Sample response:

{
  "success": true,
  "message": "🎉 Tickets booked successfully",
  "data": 
  {
    "booking_id": 1,
    "event": "Concert",
    "quantity": 2,
    "booked_at": "2026-01-08T18:00:00",
    "remaining_slots": 48
           }
}


Notes

Database is in-memory; all data resets on application restart.

Booking entity ignores Event serialization to avoid JSON errors.

License

MIT License
>>>>>>> d192213 (Initial commit - Event Booking API)

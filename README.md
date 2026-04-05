# flight-booking-backend

REST API backend for booking flight tickets and receiving confirmation details.

## Overview

This Spring Boot application exposes a single REST endpoint to create flight bookings. It uses in-memory storage for flights and bookings, validates requests, and prevents overbooking in a thread-safe way.

## How to Run

### Prerequisites

- Java 21
- Maven 3.x

### Build the application

```bash
mvn clean package -DskipTests
```

### Run the application

```bash
mvn spring-boot:run
```

The application runs on `http://localhost:8080` by default.

## API Endpoint

### Create a booking

`POST /api/bookings`

#### Request body

```json
{
  "flightNumber": "FL003",
  "passengerName": "Ravi Kishore",
  "numberOfSeats": 5
}
```

#### Example curl request

```bash
curl -X POST http://localhost:8080/api/bookings \
  -H "Content-Type: application/json" \
  -d '{
    "flightNumber": "FL003",
    "passengerName": "Ravi Kishore",
    "numberOfSeats": 5
  }'
```

#### Example response

```json
{
  "bookingId": "BKBF2FA572",
  "flightNumber": "FL003",
  "departure": "San Francisco",
  "destination": "Boston",
  "passengerName": "Ravi Kishore",
  "numberOfSeats": 5,
  "status": "CONFIRMED",
  "bookingTime": "2026-04-05T17:46:06.8404911",
  "message": "Booking confirmed successfully"
}
```

## What Could Be Included with More Time

- Flight search and listing endpoints
- Booking retrieval, update, and cancellation
- Persistence using a database instead of in-memory storage
- Authentication and authorization
- Request/response contract documentation with OpenAPI / Swagger
- Integration and unit tests for controllers and services

## Notes

- In-memory storage is used intentionally for simplicity and rapid prototyping as requested.
- The service is designed as a single application instance, without distributed coordination.

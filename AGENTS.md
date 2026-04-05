# Project Summary

Design and implement a small REST API backend for a flight ticket booking system using Java(v21), Maven and Spring Boot.

## Goals

To setup the Spring Boot Project and have a running REST API backend

## Tasks

### T1: Project Setup

**Goal**: Create the foundation of the application to have a running application with structured models
**Responsibilities**:

- Intialize a Spring Boot Web Project
- create core domain models: Flight, Booking
- Create Request/Response DTOs

### T2: Business Logic

**Goal**: Implement the core booking system functionality
**Responsibilities**:

- Create in-memory storage using:

```
Map<String, Flight>
Map<String, Booking>
```

- Preload a few sample flights
- Implement BookingService to validate flight existence, check seat availability, prevent overbooking (thread safety) and finally the ability to successfully make a booking and save it for future references

### T3: REST API Layer

**Goal**: Expose booking sunctionality via REST endpoints for client consumption
**Responsibilities**:

- Create REST Controller to accept booking requests and return booking confirmation as response
- Add proper input validation
- Use correct HTTP status codes such as (201, success), (404, flight missing), (409, flight full), (400, invalid input)

### T4: Error Handling

**Goal**: Make the API production-quality
**Responsibilities**:

- Add basic logging
- Implement global exception handling and return consistent error responses

## Constraints

- Assume the client already knows the flight number.
- Consider the application as a Single Application Instance without any distributed system concerns.

### MUST

- Use In-memory storage only
- Ensure flights can not be overbooked
- Use appropriate HTTP methods and status codes

### MUST NOT

- Implement retrieval endpoints

### OUT OF SCOPE

- Authentication, Authorization or Rate limiting
- Flight Search or Destination Logic

# Coding Standards and Guidelines

- Be simple and approach tasks in a simple, incremental way.
- Work incrementally ALWAYS in small simple steps. Validate and check each increment before moving on.
- Use Java 21 related dependencies and pin the versions as much as possible, to avoid future conflicts.
- ALWAYS prefer the stable version dependencies over the current releases.
- Use Lombok to reduce the boilerplate code.
- Return the API Response in JSON format.
- ALWAYS document the issues faced with solutions in troubleshooting.md and update the lessons learned in AGENTS.md

# Lessons Learned

## Implementation Approach

- **Incremental Development**: Started with T1 (Project Setup), progressed through T2-T4 sequentially
- **Simple & Focused**: Implemented only required features without scope creep
- **Thread Safety Implementation**: Used synchronized block on Flight object to prevent concurrent booking race conditions where multiple threads could book the same seats
- **Validation Strategy**: Leveraged Jakarta Validation annotations (@NotBlank, @Positive) with global exception handler for consistent error handling

## Technical Decisions Made

1. **In-Memory Storage**: Used HashMap for flights and bookings as per requirements (no database)
2. **Pre-loaded Data**: Initialized 5 sample flights in FlightRepository constructor for immediate testing
3. **Booking ID Generation**: Format "BK" + 8-char UUID substring for easy identification
4. **Error Handling**: Global @RestControllerAdvice for centralized exception handling
5. **Dependency Versions**: Pinned stable versions to avoid future conflicts:
   - Spring Boot 3.2.4 (stable, LTS path)
   - Java 21 (latest LTS version)
   - Lombok 1.18.30 (latest stable)

## Key Implementation Details

- **BookingService.makeBooking()**: Uses synchronized block on flight object for thread-safety:
  ```java
  synchronized (flight) {
      // Re-fetch flight to ensure latest state
      // Check availability
      // Update seats
      // Save booking
  }
  ```
- **ValidationException Handling**: Returns 400 with specific field-level error messages
- **FlightNotFoundException**: Returns 404 when flight doesn't exist
- **InsufficientSeatsException**: Returns 409 (Conflict) when flight is full
- **ErrorResponse DTO**: Consistent format with timestamp, status, error type, message, and path

## Testing Results

✅ Successful booking: BK9AFDA606 created for FL001, seats updated (100→98)
✅ Server started successfully on port 8080
✅ Spring Boot initialized all components correctly
✅ Logging levels configured (DEBUG for com.flight, INFO for root)

## Troubleshooting Notes

- API responds with proper HTTP status codes
- Validation errors trapped by global exception handler
- Thread-safe booking prevents overbooking scenarios
- Pre-loaded test data enables immediate API testing

## Best Practices Applied

- Clean code: Used Lombok to reduce boilerplate (70+ lines saved per class)
- Dependency Injection: Spring manages all components
- Repository Pattern: Clean separation of data access
- Service Layer: Business logic isolated and testable
- REST Conventions: Proper HTTP methods (POST) and status codes (201, 404, 409, 400)

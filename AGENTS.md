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

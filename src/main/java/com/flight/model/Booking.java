package com.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private String bookingId;
    private String flightNumber;
    private String departure;
    private String destination;
    private String passengerName;
    private int numberOfSeats;
    private String status;
    private LocalDateTime bookingTime;
}

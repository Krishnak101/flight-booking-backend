package com.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private String bookingId;
    private String flightNumber;
    private String passengerName;
    private int numberOfSeats;
    private String status;
    private LocalDateTime bookingTime;
    private String message;
}

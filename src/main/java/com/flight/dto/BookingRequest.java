package com.flight.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    
    @NotBlank(message = "Flight number is required")
    private String flightNumber;
    
    @NotBlank(message = "Passenger name is required")
    private String passengerName;
    
    @Positive(message = "Number of seats must be greater than 0")
    @Min(value = 1, message = "Minimum 1 seat must be booked")
    private int numberOfSeats;
}

package com.flight.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Flight {
    private String flightNumber;
    private String departure;
    private String destination;
    private int totalSeats;
    private int availableSeats;
}

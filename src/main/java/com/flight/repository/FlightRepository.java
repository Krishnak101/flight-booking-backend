package com.flight.repository;

import com.flight.model.Flight;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class FlightRepository {
    private final Map<String, Flight> flights = new HashMap<>();
    
    public FlightRepository() {
        initializeSampleFlights();
    }
    
    private void initializeSampleFlights() {
        flights.put("FL001", new Flight("FL001", "New York", "Los Angeles", 100, 100));
        flights.put("FL002", new Flight("FL002", "Chicago", "Miami", 80, 80));
        flights.put("FL003", new Flight("FL003", "San Francisco", "Boston", 120, 120));
        flights.put("FL004", new Flight("FL004", "Denver", "Seattle", 90, 90));
        flights.put("FL005", new Flight("FL005", "Atlanta", "Philadelphia", 110, 110));
    }
    
    public Optional<Flight> findByFlightNumber(String flightNumber) {
        return Optional.ofNullable(flights.get(flightNumber));
    }
    
    public void save(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }
    
    public Map<String, Flight> findAll() {
        return new HashMap<>(flights);
    }
}

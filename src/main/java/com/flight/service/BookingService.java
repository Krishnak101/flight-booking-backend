package com.flight.service;

import com.flight.dto.BookingRequest;
import com.flight.exception.FlightNotFoundException;
import com.flight.exception.InsufficientSeatsException;
import com.flight.model.Booking;
import com.flight.model.Flight;
import com.flight.repository.BookingRepository;
import com.flight.repository.FlightRepository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingService {
    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);
    
    private final FlightRepository flightRepository;
    private final BookingRepository bookingRepository;
    
    public BookingService(FlightRepository flightRepository, BookingRepository bookingRepository) {
        this.flightRepository = flightRepository;
        this.bookingRepository = bookingRepository;
    }
    
    /**
     * Make a booking for a flight with thread-safe seat management
     * @param bookingRequest the booking request containing flight number, passenger name, and number of seats
     * @return the confirmed booking
     * @throws FlightNotFoundException if flight doesn't exist
     * @throws InsufficientSeatsException if not enough seats available
     */
    public Booking makeBooking(BookingRequest bookingRequest) {
        String flightNumber = bookingRequest.getFlightNumber();
        int requestedSeats = bookingRequest.getNumberOfSeats();
        
        logger.info("Processing booking request for flight {} with {} seats", flightNumber, requestedSeats);
        
        // Validate flight exists
        Flight flight = flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> new FlightNotFoundException(
                        "Flight not found: " + flightNumber
                ));
        
        // Thread-safe booking: synchronize on the flight object to prevent overbooking
        synchronized (flight) {
            // Re-fetch to ensure we have the latest state
            flight = flightRepository.findByFlightNumber(flightNumber)
                    .orElseThrow(() -> new FlightNotFoundException(
                            "Flight not found: " + flightNumber
                    ));
            
            // Check seat availability
            if (flight.getAvailableSeats() < requestedSeats) {
                logger.warn("Insufficient seats on flight {}. Available: {}, Requested: {}", 
                        flightNumber, flight.getAvailableSeats(), requestedSeats);
                throw new InsufficientSeatsException(
                        "Not enough seats available. Available: " + flight.getAvailableSeats() + 
                        ", Requested: " + requestedSeats
                );
            }
            
            // Update flight seat availability
            flight.setAvailableSeats(flight.getAvailableSeats() - requestedSeats);
            flightRepository.save(flight);
            
            logger.info("Seats updated for flight {}. Remaining seats: {}", 
                    flightNumber, flight.getAvailableSeats());
        }
        
        // Create and save booking
        String bookingId = generateBookingId();
        Booking booking = new Booking(
                bookingId,
                flightNumber,
                bookingRequest.getPassengerName(),
                requestedSeats,
                "CONFIRMED",
                LocalDateTime.now()
        );
        
        bookingRepository.save(booking);
        logger.info("Booking confirmed with ID: {} for flight {}", bookingId, flightNumber);
        
        return booking;
    }
    
    private String generateBookingId() {
        return "BK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

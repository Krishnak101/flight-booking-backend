package com.flight.controller;

import com.flight.dto.BookingRequest;
import com.flight.dto.BookingResponse;
import com.flight.model.Booking;
import com.flight.service.BookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private static final Logger logger = LoggerFactory.getLogger(BookingController.class);
    
    private final BookingService bookingService;
    
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }
    
    /**
     * Create a new booking
     * @param bookingRequest the booking request
     * @return ResponseEntity with booking confirmation and 201 status
     */
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@Valid @RequestBody BookingRequest bookingRequest) {
        logger.info("Received booking request for flight: {} by passenger: {}", 
                bookingRequest.getFlightNumber(), bookingRequest.getPassengerName());
        
        try {
            Booking booking = bookingService.makeBooking(bookingRequest);
            
            BookingResponse response = BookingResponse.builder()
                    .bookingId(booking.getBookingId())
                    .flightNumber(booking.getFlightNumber())
                    .passengerName(booking.getPassengerName())
                    .numberOfSeats(booking.getNumberOfSeats())
                    .status(booking.getStatus())
                    .bookingTime(booking.getBookingTime())
                    .message("Booking confirmed successfully")
                    .build();
            
            logger.info("Booking created successfully with ID: {}", booking.getBookingId());
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
        } catch (Exception e) {
            logger.error("Error creating booking", e);
            throw e;
        }
    }
}

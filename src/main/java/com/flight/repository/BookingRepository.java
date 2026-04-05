package com.flight.repository;

import com.flight.model.Booking;
import org.springframework.stereotype.Repository;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookingRepository {
    private final Map<String, Booking> bookings = new HashMap<>();
    
    public Optional<Booking> findByBookingId(String bookingId) {
        return Optional.ofNullable(bookings.get(bookingId));
    }
    
    public void save(Booking booking) {
        bookings.put(booking.getBookingId(), booking);
    }
    
    public Map<String, Booking> findAll() {
        return new HashMap<>(bookings);
    }
}

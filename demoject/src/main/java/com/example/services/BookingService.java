package com.example.services;

import com.example.models.Booking;
import com.example.models.Seat;
import com.example.models.ShowTime;
import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.ValidationException;

public class BookingService extends BaseService<Booking> {
    private SeatService seatService;
    private ShowTimeService showTimeService;

    public BookingService(SeatService seatService, ShowTimeService showTimeService) {
        if (seatService == null || showTimeService == null)
            throw new ValidationException("Services cannot be null");
        this.seatService = seatService;
        this.showTimeService = showTimeService;
    }

    public Booking findByBookingId(String bookingId) {
        Booking booking = findById(bookingId);
        if (booking == null) {
            throw new ResourceNotFoundException("Booking not found: " + bookingId);
        }
        return booking;
    }

    public String getBookingDetails(String bookingId) {
        Booking booking = findByBookingId(bookingId);
        return booking.displayInfo();
    }

    public Booking createBooking(String bookingId, String showTimeId, int seatNumber) {
        ShowTime showTime = showTimeService.findById(showTimeId);
        
        if(findById(bookingId) != null){
            throw new ValidationException("Booking already exists: " + bookingId);
        }

        String screenId = showTime.getScreen().getId();
        Seat seat = seatService.findByNumber(screenId, seatNumber);

        if (!seat.isAvailable()) {
            throw new ValidationException("Seat #" + seat.getSeatNumber() + " is already booked.");
        }

        seatService.reserveSeat(screenId, seatNumber);
        Booking book = new Booking(bookingId, screenId + "-" + seatNumber, showTime);
        add(book);
        
        return book;
    }
}

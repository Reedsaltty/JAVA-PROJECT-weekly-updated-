package com.example.services;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Booking;
import com.example.models.Seat;
import com.example.models.ShowTime;

public class BookingService {
    // static void createBooking(): (1) find the showtime, (2) check seat is
    // available, (3) reserve the seat, (4) create and store a Booking object. Must
    // be done in this exact order.
    private ArrayList<Booking> books = new ArrayList<>();
    private SeatService seatService;
    private ShowTimeService showTimeService;

    public Booking findByNumber(String bookingId) {
        for (Booking b : books) {
            if (b.getId().equals(bookingId)) {
                return b;
            }
        }
        return null;

    };

    public BookingService(SeatService seatService, ShowTimeService showTimeService) {
        if (seatService == null || showTimeService == null)
            throw new IllegalArgumentException("Services cannot be null");
        this.seatService = seatService;
        this.showTimeService = showTimeService;
    }

    public Booking createBooking(String bookingId, String showTimeId, int seatNumber) {
        ShowTime showTime = showTimeService.findById(showTimeId);
        if (showTime == null) {
            throw new IllegalAccessError("ShowTime not found: " + showTimeId);
        }

        Seat seat = seatService.findByNumber(seatNumber);
        if (seat == null) {
            throw new IllegalAccessError("Seat not found: " + seatNumber);
        }
        if (!seat.isAvailable()) {
            throw new IllegalAccessError("Seat #" + seat.getSeatNumber() + " is already booked.");
        }
        seatService.reserveSeat(seatNumber);
        Booking booking = new Booking(bookingId, seatNumber, showTimeId);
        books.add(booking);
        System.out.println("Booking confirmed: ");
        getBookingDetails(bookingId);
        return booking;
    }

    public boolean cancelBooking(String bookingId) {
        Booking booking = findByNumber(bookingId);
        if (booking == null) {
            throw new IllegalAccessError("Booking not found: " + bookingId);
        }
        seatService.releaseSeat(booking.getSeatId());
        books.removeIf(b -> b.getId().equals(bookingId));
        System.out.println("Booking " + bookingId + " cancelled.");
        return true;
    }

    public List<Booking> getAllBookings() {
        return books;
    }

    // BookingService.java — add this method
    public String getBookingDetails(String bookingId) {
        Booking booking = findByNumber(bookingId);
        if (booking == null)
            throw new IllegalAccessError("Booking not found: " + bookingId);

        // look up full objects using the IDs stored in Booking
        Seat seat = seatService.findByNumber(booking.getSeatId());
        ShowTime showTime = showTimeService.findById(booking.getShowTime());

        return "Booking ID : " + booking.getId()
                + " | Seat #" + seat.getSeatNumber()
                + " | Movie : " + showTime.getMovie().getTitle()
                + " | Screen : " + showTime.getScreen().getId()
                + " | Time : " + showTime.getDateTime();
    }

}
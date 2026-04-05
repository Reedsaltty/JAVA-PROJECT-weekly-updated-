package com.example.models;

import com.example.interface_abstract.Displayable;
import com.example.interface_abstract.Entity;


public class Booking extends Entity implements Displayable {

    private String seatId;          
    private ShowTime showTime;  

    public Booking(String bookingId, String seatId, ShowTime showTime) throws com.example.exceptions.ValidationException {
        super(bookingId);
        if (seatId == null || seatId.trim().isEmpty()) {
            throw new com.example.exceptions.ValidationException("Booking seatId cannot be null or empty");
        }
        if (showTime == null) {
            throw new com.example.exceptions.ValidationException("Booking must have a ShowTime associated");
        }
        this.seatId   = seatId;
        this.showTime = showTime;
    }
    

    public String getSeatId() {
        return seatId;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    @Override
    public String displayInfo() {
        return "Booking [ID: " + getId() + ", Showtime: " + (showTime != null ? showTime.getDateTime() : "N/A") + ", Seat: " + seatId + "]";
    }
}

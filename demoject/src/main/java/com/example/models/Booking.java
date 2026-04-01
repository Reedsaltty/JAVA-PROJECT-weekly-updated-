package com.example.models;

import com.example.interface_abstract.Displayable;
import com.example.interface_abstract.Entity;


public class Booking extends Entity implements Displayable {

    private String seatId;          
    private String showTimeId;  

    public Booking(String bookingId, String seatId, String showTimeId) {
        super(bookingId);
        this.seatId     = seatId;
        this.showTimeId = showTimeId;
    }
    

    public String getSeatId() {
        return seatId;
    }

    public String getShowTime() {
        return showTimeId;
    }

    @Override
    public String displayInfo() {
        return "Booking [ID: " + getId() + ", Showtime: " + showTimeId + ", Seat: " + seatId + "]";
    }
}

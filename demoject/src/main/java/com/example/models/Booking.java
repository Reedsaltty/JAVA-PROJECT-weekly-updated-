package com.example.models;

import com.example.interface_abstract.Entity;


public class Booking extends Entity  {

    private int seatId;          
    private String showTimeId;  

    public Booking(String bookingId, int seatId, String showTimeId) {
        super(bookingId);
        this.seatId     = seatId;
        this.showTimeId = showTimeId;
    }
    

    public int getSeatId() {
        return seatId;
    }

    public String getShowTime() {
        return showTimeId;
    }

    

   
    
}
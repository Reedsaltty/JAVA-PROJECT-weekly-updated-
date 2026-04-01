package com.example.models;

import com.example.interface_abstract.Displayable;
import com.example.interface_abstract.Entity;

public class Seat extends Entity implements Displayable {


    private String screenId;
    private int seatNumber;
    private boolean isAvailable;

    public Seat(String screenId, int seatNumber) {
        super(screenId + "-" + seatNumber);
        this.screenId = screenId;
        this.seatNumber = seatNumber;
        this.isAvailable = true;
    }
    
    public Seat( String screenId, int seatNumber, boolean isAvailable) {
        super(screenId + "-" + seatNumber);
        this.screenId    = screenId;
        this.seatNumber  = seatNumber;
        this.isAvailable = isAvailable;
    }

    public String getScreenId()              { return screenId; }
    public void setScreenId(String screenId) { this.screenId = screenId; }

    public int getSeatNumber()              { return seatNumber; }
    public void setSeatNumber(int n)        { this.seatNumber = n; }

    public boolean isAvailable()            { return isAvailable; }
    public void setAvailable(boolean avail) { this.isAvailable = avail; }

    @Override
    public String displayInfo() {
        return "Seat [Screen: " + screenId + ", Number: " + seatNumber + ", Status: " + (isAvailable ? "AVAILABLE" : "BOOKED") + "]";
    }

    @Override
    public String toString() {
        return displayInfo();
    }
}

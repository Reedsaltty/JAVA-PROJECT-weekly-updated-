package com.example.models;

import com.example.interface_abstract.Displayable;
import com.example.interface_abstract.Entity;
import com.example.exceptions.ValidationException;  
public class Seat extends Entity implements Displayable {


    private String screenId;
    private int seatNumber;
    private boolean isAvailable;

    public Seat(String screenId, int seatNumber) throws ValidationException {
        super(screenId + "-" + seatNumber);
        setScreenId(screenId);
        setSeatNumber(seatNumber);
        this.isAvailable = true;
    }
    
    public Seat( String screenId, int seatNumber, boolean isAvailable) throws ValidationException {
        super(screenId + "-" + seatNumber);
        setScreenId(screenId);
        setSeatNumber(seatNumber);
        this.isAvailable = isAvailable;
    }

    public String getScreenId()              { return screenId; }
    public void setScreenId(String screenId) throws ValidationException {
        if (screenId == null || screenId.trim().isEmpty()) {
            throw new ValidationException("Seat screenId cannot be null or empty");
        }
        this.screenId = screenId;
    }

    public int getSeatNumber()              { return seatNumber; }
    public void setSeatNumber(int n) throws ValidationException {
        if (n <= 0) {
            throw new ValidationException("Seat number must be greater than 0");
        }
        this.seatNumber = n;
    }

    public boolean isAvailable()            { return isAvailable; }
    public void setAvailable(boolean avail) { this.isAvailable = avail; }

    @Override
    public String displayInfo() {
        return "Seat [Screen: " + screenId + ", Number: " + seatNumber + ", Status: " + (isAvailable ? "AVAILABLE" : "BOOKED") + "]";
    }


    public String toString() {
        return displayInfo();
    }
}

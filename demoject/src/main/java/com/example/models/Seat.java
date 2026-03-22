package com.example.models;

public class Seat {   // no longer implements Bookable


    private String screenId;
    private int seatNumber;
    private boolean isAvailable;

    public Seat( String screenId, int seatNumber, boolean isAvailable) {
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

    public String toString() {
        return "Seat #" + seatNumber + " [" + (isAvailable ? "AVAILABLE" : "BOOKED") + "]";
    }
}
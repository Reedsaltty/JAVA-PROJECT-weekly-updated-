package com.example.models;

import com.example.interface_abstract.Displayable;
import com.example.interface_abstract.Entity;

public class Screen extends Entity implements Displayable {

    private int numberOfSeats;

    public Screen(String screenId, int numberOfSeats) {
        super(screenId);
        this.numberOfSeats = numberOfSeats;
    }


    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String displayInfo() {
        return "Screen [ID: " + getId() + ", Seats: " + numberOfSeats + "]";
    }

}

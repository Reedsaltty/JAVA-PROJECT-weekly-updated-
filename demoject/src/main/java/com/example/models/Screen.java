package com.example.models;

import com.example.interface_abstract.Displayable;
import com.example.interface_abstract.Entity;
import com.example.exceptions.ValidationException;  

public class Screen extends Entity implements Displayable {

    private int numberOfSeats;

    public Screen(String screenId, int numberOfSeats) throws ValidationException {
        super(screenId);
        setNumberOfSeats(numberOfSeats);
    }


    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) throws ValidationException {
        if (numberOfSeats <= 0) {
            throw new ValidationException("Screen must have at least 1 seat");
        }
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public String displayInfo() {
        return "Screen [ID: " + getId() + ", Seats: " + numberOfSeats + "]";
    }
}

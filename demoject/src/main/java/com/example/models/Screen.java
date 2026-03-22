package com.example.models;


import com.example.interface_abstract.Entity;

public class Screen extends Entity  {

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

}
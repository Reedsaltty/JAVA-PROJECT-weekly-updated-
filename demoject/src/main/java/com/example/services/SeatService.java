package com.example.services;

import com.example.models.Seat;
import com.example.models.Screen;
import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.ValidationException;
import java.util.List;
import java.util.ArrayList;

public class SeatService extends BaseService<Seat> {

    public void initializeSeats(Screen screen) {
        for (int i = 1; i <= screen.getNumberOfSeats(); i++) {
            add(new Seat(screen.getId(), i));
        }
        System.out.println("Initialized " + screen.getNumberOfSeats()
            + " seats for screen " + screen.getId());
    }

    public List<Seat> getAvailableSeats(String screenId) {
        List<Seat> result = new ArrayList<>();
        for (Seat s : entities) {
            if (s.getScreenId().equals(screenId) && s.isAvailable()) {
                result.add(s);
            }
        }
        return result;
    }

    public Seat findByNumber(String screenId, int seatNumber) {
        String idSlug = screenId + "-" + seatNumber;
        Seat seat = findById(idSlug);
        if (seat == null) {
             throw new ResourceNotFoundException("Seat #" + seatNumber + " on Screen " + screenId + " not found.");
        }
        return seat;
    }

    public boolean reserveSeat(String screenId, int seatNumber) {
        Seat seat = findByNumber(screenId, seatNumber);
        if (!seat.isAvailable()){
             throw new ValidationException("Seat #" + seat.getSeatNumber() + " is already booked.");
        }
        seat.setAvailable(false);
        return true;
    }

    public boolean releaseSeat(String screenId, int seatNumber) {
        Seat seat = findByNumber(screenId, seatNumber);
        if (seat.isAvailable())
            return false;
        seat.setAvailable(true);
        return true;
    }
}

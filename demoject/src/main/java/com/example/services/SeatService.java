package com.example.services;

import com.example.models.Seat;
import com.example.models.Screen;
import java.util.ArrayList;
import java.util.function.Predicate;


public class SeatService {

    private ArrayList<Seat> allSeats = new ArrayList<>();

    // ── Called by ScreenService when a screen is added ──────────

    public void initializeSeats(Screen screen) {
        for (int i = 1; i <= screen.getNumberOfSeats(); i++) {
            allSeats.add(new Seat(
                screen.getId(),
                i,
                true
            ));
        }
        System.out.println("Initialized " + screen.getNumberOfSeats()
            + " seats for screen " + screen.getId());
    }

    // ── Moved from Seat.book() ───────────────────────────────────

    public boolean reserveSeat(int seatNumber) {
        Seat seat = findByNumber(seatNumber);
        if (seat == null){
            throw new IllegalAccessError("Seat not found: " + seatNumber);}
            
        if (!seat.isAvailable()){
             throw new IllegalAccessError(
                "Seat #" + seat.getSeatNumber() + " is already booked.");
        }
        seat.setAvailable(false);   // the actual state change
        return true;
    }

    // ── Moved from Seat.cancel() ─────────────────────────────────

    public boolean releaseSeat(int seatNumber) {
        Seat seat = findByNumber(seatNumber);
        if (seat == null)
            throw new IllegalAccessError("Seat not found: " + seatNumber);
        if (seat.isAvailable())
            return false;           // wasn't booked — nothing to cancel

        seat.setAvailable(true);    // release it
        return true;
    }

    // ── Predicate-based filter (uses your Filter.java pattern) ───

    public ArrayList<Seat> getSeatsBy(Predicate<Seat> condition) {
        ArrayList<Seat> result = new ArrayList<>();
        for (Seat s : allSeats)
            if (condition.test(s)) result.add(s);
        return result;
    }

    // Convenience methods that use the predicate above
    public ArrayList<Seat> getAvailableSeats(String screenId) {
        return getSeatsBy(s -> s.getScreenId().equals(screenId) && s.isAvailable());
    }
    public void listAllAvailSeats(String screenId){
        ArrayList<Seat> avaiSeat = getAvailableSeats(screenId);
        for(Seat a : avaiSeat){
            System.out.println(a.toString() );
        }
    }

    public ArrayList<Seat> getAllSeatsForScreen(String screenId) {
        return getSeatsBy(s -> s.getScreenId().equals(screenId));
    }

    // ── Finder ───────────────────────────────────────────────────

    public Seat findByNumber(int seatNumber) {
        for (Seat s : allSeats)
            if (s.getSeatNumber() == seatNumber) return s;
        return null;
    }
    
}
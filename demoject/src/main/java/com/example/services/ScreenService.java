package com.example.services;
import java.util.ArrayList;

import com.example.models.Screen;

// service/ScreenService.java
public class ScreenService {

    private ArrayList<Screen> screens = new ArrayList<>();
    private SeatService seatService;

    // SeatService is injected through the constructor
    // This is called "dependency injection" — ScreenService doesn't
    // create SeatService itself, Main.java passes it in
    public ScreenService(SeatService seatService) {
        this.seatService = seatService;
    }

    public void addScreen(Screen screen) {
        screens.add(screen);

        // Automatically bulk-create all seats for this screen
        seatService.initializeSeats(screen);

        System.out.println("Screen " + screen.getId()
            + " added with " + screen.getNumberOfSeats() + " seats.");
    }

    public ArrayList<Screen> getAllScreens() {
        return screens;
    }

    public Screen findById(String screenId) {
        for (Screen s : screens)
            if (s.getId().equals(screenId)) return s;
        return null;
    }
}
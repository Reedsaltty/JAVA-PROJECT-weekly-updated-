package com.example.services;

import com.example.models.Screen;
import com.example.exceptions.ResourceNotFoundException;
import java.util.List;

public class ScreenService extends BaseService<Screen> {

    private SeatService seatService;

    public ScreenService(SeatService seatService) {
        this.seatService = seatService;
    }

    public void addScreen(Screen screen) {
        add(screen);
        seatService.initializeSeats(screen);
        System.out.println("Screen " + screen.getId()
            + " added with " + screen.getNumberOfSeats() + " seats.");
    }

    public List<Screen> getAllScreens() {
        return getAll();
    }

    @Override
    public Screen findById(String screenId) {
        Screen screen = super.findById(screenId);
        if (screen == null) {
            throw new ResourceNotFoundException("Screen not found: " + screenId);
        }
        return screen;
    }
}

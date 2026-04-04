package com.example.services;

import com.example.models.Screen;
import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.ValidationException;
import java.util.List;

public class ScreenService extends BaseService<Screen> {

    private SeatService seatService;

    public ScreenService(SeatService seatService) {
        if (seatService == null)
            throw new ValidationException("SeatService cannot be null");
        this.seatService = seatService;
    }

    public void addScreen(Screen screen) {
        if (super.findById(screen.getId()) != null) {
            throw new ValidationException("Screen already exists: " + screen.getId());
        }
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

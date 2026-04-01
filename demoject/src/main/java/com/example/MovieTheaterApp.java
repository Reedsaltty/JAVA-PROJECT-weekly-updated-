package com.example;

import com.example.models.Booking;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.models.Seat;
import com.example.services.BookingService;
import com.example.services.MovieService;
import com.example.services.ScreenService;
import com.example.services.SeatService;
import com.example.services.ShowTimeService;
import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.ValidationException;

public class MovieTheaterApp 
{
    public static void main( String[] args )
    {
        SeatService     seatService     = new SeatService();
        MovieService    movieService    = new MovieService();
        ScreenService   screenService   = new ScreenService(seatService);
        ShowTimeService showTimeService = new ShowTimeService(movieService, screenService);
        BookingService  bookingService  = new BookingService(seatService, showTimeService);

        movieService.addMovie(new Movie("MOV001", "Avengers: Endgame", 181, "Action"));
        screenService.addScreen(new Screen("SCR001", 5)); 
        showTimeService.addShowTime("ST001", "2026-03-25 19:00", "MOV001", "SCR001");

        System.out.println("\n-- Available Seats --");
        for (Seat s : seatService.getAvailableSeats("SCR001"))
            System.out.println(s.displayInfo());

        System.out.println("\n-- Making Booking ---");
        try {
            Booking b = bookingService.createBooking("BK001", "ST001", 1);
            System.out.println(bookingService.getBookingDetails("BK001"));
        } catch (ValidationException | ResourceNotFoundException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        System.out.println("\n-- Booking Same Seat Again --");
        try {
            bookingService.createBooking("BK002", "ST001", 1);
            System.out.println(bookingService.getBookingDetails("BK002"));
        } catch (ValidationException | ResourceNotFoundException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        System.out.println("\n-- Final Available Seats --");
        for (Seat s : seatService.getAvailableSeats("SCR001"))
            System.out.println(s.displayInfo());
    }
}

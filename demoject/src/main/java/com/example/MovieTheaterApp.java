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

/**
 * Hello world!
 *
 */
public class MovieTheaterApp 
{
    public static void main( String[] args )
    {
    // ── Wire services ──────────────────────────
        SeatService     seatService     = new SeatService();
        MovieService    movieService    = new MovieService();
        ScreenService   screenService   = new ScreenService(seatService);
        ShowTimeService showTimeService = new ShowTimeService(movieService, screenService);
        BookingService  bookingService  = new BookingService(seatService, showTimeService);

        // ── Sample data ────────────────────────────
        movieService.addMovie(new Movie("MOV001", "Avengers: Endgame", 181, "Action"));
        screenService.addScreen(new Screen("SCR001", 50)); // 5 seats for easy testing
        showTimeService.addShowTime("ST001", "2026-03-25 19:00", "MOV001", "SCR001");

        // ── Test 1: list available seats ───────────
        System.out.println("\n── Available Seats ──");
        for (Seat s : seatService.getAvailableSeats("SCR001"))
            System.out.println(s.toString());

        // ── Test 2: make a booking ─────────────────
        System.out.println("\n── Making Booking ───");
        try {
            Booking b = bookingService.createBooking("BK001", "ST001", 1);
            System.out.println(bookingService.getBookingDetails("BK001"));
        } catch (IllegalStateException e) {
            System.out.println("Failed: " + e.getMessage());
        }

        // ── Test 3: book same seat again (should fail) ──
        System.out.println("\n── Booking Same Seat Again ──");
        try {
            bookingService.createBooking("BK002", "ST001", 2);
            System.out.println(bookingService.getBookingDetails("BK002"));
        } catch (IllegalStateException e) {
            System.out.println("Failed: " + e.getMessage());}


        // ── Test 4: cancel and rebook ──────────────
        System.out.println("\n── Cancel then Rebook ───");
        bookingService.cancelBooking("BK001");
        try {
            Booking b = bookingService.createBooking("BK003", "ST001", 1);
            System.out.println(bookingService.getBookingDetails("BK003"));
        } catch (IllegalStateException e) {
            System.out.println("Failed: " + e.getMessage());}
        
        seatService.listAllAvailSeats("SCR001");

       
    }
}

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
    // Helper to print a section header
    private static void section(String title) {
        System.out.println("\n========================================");
        System.out.println("  " + title);
        System.out.println("========================================");
    }

    // Helper to print PASS / FAIL result
    private static void expect(String scenario, Runnable action, boolean shouldFail) {
        System.out.print("[TEST] " + scenario + " --> ");
        try {
            action.run();
            if (shouldFail) {
                System.out.println("FAIL (expected exception but none was thrown)");
            } else {
                System.out.println("PASS");
            }
        } catch (ValidationException | ResourceNotFoundException e) {
            if (shouldFail) {
                System.out.println("PASS (caught: " + e.getClass().getSimpleName() + ": " + e.getMessage() + ")");
            } else {
                System.out.println("FAIL (unexpected: " + e.getClass().getSimpleName() + ": " + e.getMessage() + ")");
            }
        }
    }

    public static void main(String[] args) {

        SeatService     seatService     = new SeatService();
        MovieService    movieService    = new MovieService();
        ScreenService   screenService   = new ScreenService(seatService);
        ShowTimeService showTimeService = new ShowTimeService(movieService, screenService);
        BookingService  bookingService  = new BookingService(seatService, showTimeService);

        // ─────────────────────────────────────────────
        // 1. MOVIE EXCEPTIONS
        // ─────────────────────────────────────────────
        section("1. MOVIE — ValidationException (duplicate ID)");

        expect("Add new movie (should PASS)",
            () -> movieService.addMovie(new Movie("MOV001", "Avengers: Endgame", 181, "Action")),
            false);

        expect("Add duplicate movie ID (should FAIL)",
            () -> movieService.addMovie(new Movie("MOV001", "Avengers: Endgame", 181, "Action")),
            true);

        section("2. MOVIE — ResourceNotFoundException (not found)");

        expect("Find existing movie (should PASS)",
            () -> movieService.findMovieById("MOV001"),
            false);

        expect("Find non-existent movie (should FAIL)",
            () -> movieService.findMovieById("GHOST"),
            true);

        section("3. MOVIE — ResourceNotFoundException (delete non-existent)");

        expect("Delete existing movie (should PASS)",
            () -> {
                movieService.addMovie(new Movie("MOV999", "Temp Movie", 90, "Drama"));
                movieService.deleteMovie("MOV999");
            },
            false);

        expect("Delete non-existent movie (should FAIL)",
            () -> movieService.deleteMovie("NO-SUCH-MOVIE"),
            true);

        // ─────────────────────────────────────────────
        // 2. SCREEN EXCEPTIONS
        // ─────────────────────────────────────────────
        section("4. SCREEN — ValidationException (duplicate ID)");

        expect("Add new screen (should PASS)",
            () -> screenService.addScreen(new Screen("SCR001", 10)),
            false);

        expect("Add duplicate screen (should FAIL)",
            () -> screenService.addScreen(new Screen("SCR001", 10)),
            true);

        section("5. SCREEN — ValidationException (null SeatService)");

        expect("Create ScreenService with null SeatService (should FAIL)",
            () -> new ScreenService(null),
            true);

        // ─────────────────────────────────────────────
        // 3. SHOWTIME EXCEPTIONS
        // ─────────────────────────────────────────────
        section("6. SHOWTIME — ValidationException (null services)");

        expect("Create ShowTimeService with null movieService (should FAIL)",
            () -> new ShowTimeService(null, screenService),
            true);

        expect("Create ShowTimeService with null screenService (should FAIL)",
            () -> new ShowTimeService(movieService, null),
            true);

        section("7. SHOWTIME — ValidationException (duplicate showtime ID)");

        expect("Add new showtime (should PASS)",
            () -> showTimeService.addShowTime("ST001", "2026-04-10 19:00", "MOV001", "SCR001"),
            false);

        expect("Add duplicate showtime ID (should FAIL)",
            () -> showTimeService.addShowTime("ST001", "2026-04-10 21:00", "MOV001", "SCR001"),
            true);

        section("8. SHOWTIME — ResourceNotFoundException (movie/screen not found)");

        expect("Add showtime with non-existent movie (should FAIL)",
            () -> showTimeService.addShowTime("ST002", "2026-04-11 19:00", "GHOST-MOV", "SCR001"),
            true);

        expect("Add showtime with non-existent screen (should FAIL)",
            () -> showTimeService.addShowTime("ST002", "2026-04-11 19:00", "MOV001", "GHOST-SCR"),
            true);

        section("9. SHOWTIME — ValidationException (screen/time conflict)");

        screenService.addScreen(new Screen("SCR002", 5));
        showTimeService.addShowTime("ST002", "2026-04-11 19:00", "MOV001", "SCR002");

        expect("Add conflicting showtime (same screen & time) (should FAIL)",
            () -> showTimeService.addShowTime("ST003", "2026-04-11 19:00", "MOV001", "SCR002"),
            true);

        // ─────────────────────────────────────────────
        // 4. BOOKING EXCEPTIONS
        // ─────────────────────────────────────────────
        section("10. BOOKING — ValidationException (null services)");

        expect("Create BookingService with null seatService (should FAIL)",
            () -> new BookingService(null, showTimeService),
            true);

        expect("Create BookingService with null showTimeService (should FAIL)",
            () -> new BookingService(seatService, null),
            true);

        section("11. BOOKING — Happy path (should PASS)");

        expect("Book seat 1 on ST001 (should PASS)",
            () -> {
                Booking b = bookingService.createBooking("BK001", "ST001", 1);
                System.out.print("  Booking created: " + b.displayInfo() + " ");
            },
            false);

        section("12. BOOKING — ValidationException (duplicate booking ID)");

        expect("Book with same booking ID 'BK001' again (should FAIL)",
            () -> bookingService.createBooking("BK001", "ST001", 2),
            true);

        section("13. BOOKING — ValidationException (seat already taken)");

        expect("Book seat 1 again on ST001 (should FAIL)",
            () -> bookingService.createBooking("BK002", "ST001", 1),
            true);

        section("14. BOOKING — ResourceNotFoundException (showtime not found)");

        expect("Book with non-existent showtime (should FAIL)",
            () -> bookingService.createBooking("BK003", "GHOST-ST", 2),
            true);

        section("15. BOOKING — ResourceNotFoundException (seat not found)");

        expect("Book seat 999 that doesn't exist on screen (should FAIL)",
            () -> bookingService.createBooking("BK004", "ST001", 999),
            true);

        // ─────────────────────────────────────────────
        // SUMMARY
        // ─────────────────────────────────────────────
        section("ALL TESTS COMPLETE");
        System.out.println("  Every PASS line = exception guard is working correctly.");
        System.out.println("  Every FAIL line = something needs to be fixed.\n");
    }
}

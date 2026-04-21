package com.example;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.example.models.*;
import com.example.services.*;
import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.ValidationException;

import java.util.List;

/**
 * Integrated System Tests for the Movie Theater Application.
 * These tests verify the end-to-end functionality of movie management,
 * screen initialization, showtime scheduling, and the booking process.
 */
public class AppTest {
    private MovieService movieService;
    private ScreenService screenService;
    private ShowTimeService showTimeService;
    private BookingService bookingService;
    private SeatService seatService;

    /**
     * Re-initializes all services before each test to ensure a clean state
     * and independent test execution.
     */
    @Before
    public void setUp() {
        seatService = new SeatService();
        movieService = new MovieService();
        screenService = new ScreenService(seatService);
        showTimeService = new ShowTimeService(movieService, screenService);
        bookingService = new BookingService(seatService, showTimeService);
    }

    // --- Movie Management Tests ---

    /**
     * Verifies that a movie can be added with all required fields (ID, Title,
     * Duration, Genre).
     */
    @Test
    public void testAddMovieFullDetails() {
        movieService.addMovie(new Movie("MOV-001", "Inception", 148, "Sci-Fi"));
        Movie movie = movieService.findMovieById("MOV-001");
        assertNotNull("Movie should be found in the system", movie);
        assertEquals("Title should match the input", "Inception", movie.getTitle());
    }

    /**
     * Verifies the overloaded constructor: adding a movie without a genre
     * should automatically default it to "Unknown".
     */
    @Test
    public void testAddMovieOverloaded() {
        movieService.addMovie(new Movie("MOV-002", "Joker", 122));
        Movie movie = movieService.findMovieById("MOV-002");
        assertEquals("Genre should default to 'Unknown'", "Unknown", movie.getGenre());
    }

    /**
     * Verifies that searching for a non-existent movie ID correctly
     * throws a ResourceNotFoundException.
     */
    @Test(expected = ResourceNotFoundException.class)
    public void testFindNonExistentMovie() {
        movieService.findMovieById("NON-EXISTENT");
    }

    // --- Screen Management Tests ---

    /**
     * Verifies that when a screen is added, the SeatService correctly
     * initializes all associated seats for that screen.
     */
    @Test
    public void testAddScreenInitializesSeats() {
        screenService.addScreen(new Screen("SCR-101", 100));
        List<Seat> avaiSeats = seatService.getAvailableSeats("SCR-101");
        assertEquals("All 100 seats should be initialized and available", 100, avaiSeats.size());
    }

    // --- ShowTime Management Tests ---

    /**
     * Verifies the happy path for scheduling a new ShowTime.
     */
    @Test
    public void testAddShowTimeSuccess() {
        movieService.addMovie(new Movie("MOV-001", "Inception", 148));
        screenService.addScreen(new Screen("SCR-101", 100));
        showTimeService.addShowTime("ST-101", "2026-04-01 20:00", "MOV-001", "SCR-101");
        assertNotNull("ShowTime should be retrievable by ID", showTimeService.findById("ST-101"));
    }

    /**
     * Verifies that the system prevents 'Double Booking' a screen:
     * scheduling two shows at the same time on the same screen must fail.
     */
    @Test(expected = ValidationException.class)
    public void testAddShowTimeConflict() {
        movieService.addMovie(new Movie("MOV-001", "Inception", 148));
        screenService.addScreen(new Screen("SCR-101", 100));
        showTimeService.addShowTime("ST-101", "2026-04-01 20:00", "MOV-001", "SCR-101");

        // This should throw a ValidationException due to overlapping time/screen
        showTimeService.addShowTime("ST-CONFLICT", "2026-04-01 20:00", "MOV-001", "SCR-101");
    }

    // --- Booking Management Tests ---

    /**
     * Verifies that a seat can be successfully booked for a valid ShowTime.
     */
    @Test
    public void testCreateBookingSuccess() {
        movieService.addMovie(new Movie("MOV-001", "Inception", 148));
        screenService.addScreen(new Screen("SCR-101", 100));
        showTimeService.addShowTime("ST-101", "2026-04-01 20:00", "MOV-001", "SCR-101");

        Booking b = bookingService.createBooking("BK-101", "ST-101", 1);
        assertNotNull("Booking confirmation should not be null", b);
        assertEquals("Seat ID should be formatted correctly (Screen-SeatNumber)", "SCR-101-1", b.getSeatId());
    }

    /**
     * Verifies that the system enforces unique Booking IDs.
     */
    @Test(expected = ValidationException.class)
    public void testCreateBookingDuplicateId() {
        movieService.addMovie(new Movie("MOV-001", "Inception", 148));
        screenService.addScreen(new Screen("SCR-101", 100));
        showTimeService.addShowTime("ST-101", "2026-04-01 20:00", "MOV-001", "SCR-101");

        bookingService.createBooking("BK-101", "ST-101", 1);
        // This should fail because 'BK-101' is already used
        bookingService.createBooking("BK-101", "ST-101", 2);
    }

    /**
     * Verifies that the system prevents booking a seat that has already
     * been reserved for that specific show.
     */
    @Test(expected = ValidationException.class)
    public void testBookAlreadyTakenSeat() {
        movieService.addMovie(new Movie("MOV-001", "Inception", 148));
        screenService.addScreen(new Screen("SCR-101", 100));
        showTimeService.addShowTime("ST-101", "2026-04-01 20:00", "MOV-001", "SCR-101");

        bookingService.createBooking("BK-1", "ST-101", 1);
        // This should fail because Seat 1 is already taken
        bookingService.createBooking("BK-2", "ST-101", 1);
    }

    // --- Advanced Logic: Multi-Screen Isolation ---

    /**
     * CRITICAL TEST: Verifies that seat availability is scoped to the screen.
     * Booking Seat 1 on Screen A should NOT affect the availability of
     * Seat 1 on Screen B.
     */
    @Test
    public void testMultiScreenIsolation() {
        movieService.addMovie(new Movie("MOV-001", "Inception", 148));
        screenService.addScreen(new Screen("SCR-101", 50));
        screenService.addScreen(new Screen("SCR-102", 50));
        showTimeService.addShowTime("ST-101", "2026-04-01 20:00", "MOV-001", "SCR-101");
        showTimeService.addShowTime("ST-102", "2026-04-01 20:00", "MOV-001", "SCR-102");

        // Book Seat 1 on Screen 101
        bookingService.createBooking("BK-1", "ST-101", 1);

        // Seat 1 on Screen 102 should still be AVAILABLE and safe to book
        Booking b2 = bookingService.createBooking("BK-2", "ST-102", 1);
        assertNotNull("Booking for Screen 102 should succeed", b2);
    }
}

package com.example;

import com.example.models.Booking;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.services.BookingService;
import com.example.services.MovieService;
import com.example.services.ScreenService;
import com.example.services.SeatService;
import com.example.services.ShowTimeService;
import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.ValidationException;
import java.util.Scanner;

public class MovieTheaterApp {

    // ANSI Color Constants (Makes the demo pop!)
    public static final String RESET = "\u001B[0m";
    public static final String BOLD = "\u001B[1m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String CYAN = "\u001B[36m";
    public static final String YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            runProfessionalDemo(scanner);
            System.out.println("\n" + GREEN + "✓ Demo completed successfully!" + RESET);
        } catch (Exception e) {
            System.err.println("Demo Error: " + e.getMessage());
        }
    }

    private static void runProfessionalDemo(Scanner scanner) {
        // Initialize services
        SeatService seatService = new SeatService();
        MovieService movieService = new MovieService();
        ScreenService screenService = new ScreenService(seatService);
        ShowTimeService showTimeService = new ShowTimeService(movieService, screenService);
        BookingService bookingService = new BookingService(seatService, showTimeService);

        printHeader("🎬 CINEBOOK DEMO: PROFESSIONAL THEATER BOOKING");
        pause(scanner, "Start by explaining that this is a robust backend built in Java with full data validation.");




        // ---------------------------------------------------------
        // PART 1: DATA INTEGRITY (THE INPUT GUARD)
        // ---------------------------------------------------------
        printSection("1. DATA INTEGRITY (The Input Guard)");
        System.out.println(CYAN + "[SPEAKER] 'Our system ensures that no bad data can even enter memory...'" + RESET);

        try {
            System.out.println("-> Let's try to add a movie with -10 minutes duration...");
            new Movie("M1", "Broken Movie", -10, "Comedy");
        } catch (ValidationException e) {
            System.out.println(RED + "BLOCK REJECTED: " + e.getMessage() + RESET);
        }
        pause(scanner, "Notice how the system rejects 'bad data' before it even enters memory. This is Constructor-Level Validation.");

        // ---------------------------------------------------------
        // PART 2: SERVICE MANAGEMENT (DUPLICATE IDs & NOT FOUND)
        // ---------------------------------------------------------
        printSection("2. SERVICE MANAGEMENT (Data Integrity)");
        System.out.println(CYAN + "[SPEAKER] 'Our services protect against duplicate records and handle missing resources...'" + RESET);
        
        try {
            movieService.addMovie(new Movie("MOV01", "Inception", 148, "Sci-Fi"));
            System.out.println(GREEN + "✓ Movie 'Inception' Added (MOV01)" + RESET);
            
            System.out.println("-> Attempting to add ANOTHER movie with ID 'MOV01'...");
            movieService.addMovie(new Movie("MOV01", "The Matrix", 136, "Action"));
        } catch (ValidationException e) {
            System.out.println(RED + "BLOCK DETECTED: " + e.getMessage() + RESET);
        }

        try {
            System.out.println("\n-> Looking for a non-existent Movie with ID 'GHOST'...");
            movieService.findMovieById("GHOST");
        } catch (ResourceNotFoundException e) {
            System.out.println(YELLOW + "HANDLED: " + e.getMessage() + RESET);
        }
        pause(scanner, "Explain how custom exceptions like 'ResourceNotFound' make the system more predictable and professional.");





        // ---------------------------------------------------------
        // PART 3: SCHEDULING (Conflict Detection)
        // ---------------------------------------------------------
        printSection("3. SCHEDULING (Conflict Detection)");
        try {
            // Setup additional movie
            movieService.addMovie(new Movie("MOV02", "Interstellar", 169, "Sci-Fi"));
            screenService.addScreen(new Screen("SCR01", 10));
            
            System.out.println(GREEN + "✓ Screen 'SCR01' (10 seats) Registered" + RESET);
            
            System.out.println("\n-> Scheduling 'Inception' (MOV01) in SCR01 at 19:00...");
            showTimeService.addShowTime("ST01", "2026-10-10 19:00", "MOV01", "SCR01");
            System.out.println(BOLD + "SHOWTIME ST01 CREATED!" + RESET);
            
            pause(scanner, "Now, attempt to schedule 'Interstellar' (MOV02) in the SAME screen at the SAME time...");
            
            System.out.println("-> Attempting to double-book Screen SCR01 at 19:00...");
            showTimeService.addShowTime("ST02", "2026-10-10 19:00", "MOV02", "SCR01");
        } catch (Exception e) {
            System.out.println(RED + "BLOCK DETECTED: " + e.getMessage() + RESET);
        }
        pause(scanner, "Explain that the 'ShowTimeService' manages complex resource lookups to prevent business errors.");




        
        // ---------------------------------------------------------
        // PART 4: THE BOOKING JOURNEY (REAL-TIME AVAILABILITY)
        // ---------------------------------------------------------
        printSection("4. BOOKING JOURNEY (Real-time Availability)");
        try {
            System.out.println("-> Customer A is booking ST01 (Interstellar), Seat 5...");
            Booking b = bookingService.createBooking("B001", "ST01", 5);
            System.out.println(GREEN + "✓ BOOKING SUCCESS: " + b.displayInfo() + RESET);

            pause(scanner, "Now, show what happens when Customer B tries to book the SAME seat...");

            System.out.println("-> Customer B attempting to book Seat 5 again...");
            bookingService.createBooking("B002", "ST01", 5);
        } catch (Exception e) {
            System.out.println(RED + "BLOCK DETECTED: " + e.getMessage() + RESET);
        }
        pause(scanner, "Final point on booking: Concurrent users are protected from double-booking.");




        // ---------------------------------------------------------
        // PART 5: THE CORE ENGINE (SEARCH & RETRIEVAL)
        // ---------------------------------------------------------
        printSection("5. SEARCH & RETRIEVAL (The Engine)");
        System.out.println(CYAN + "[SPEAKER] 'Our system allows fast, validated access to all resources...'" + RESET);

        try {
            System.out.print("-> Searching for movie with ID 'MOV01'... ");
            Movie found = movieService.findMovieById("MOV01");
            System.out.println(GREEN + "FOUND: " + found.getTitle() + RESET);

            System.out.print("-> Retrieving screen SCR01 seat capacity... ");
            Screen s = screenService.findById("SCR01");
            System.out.println(GREEN + s.getNumberOfSeats() + " Seats" + RESET);
        } catch (Exception e) {
            System.out.println(RED + "ERROR during search: " + e.getMessage() + RESET);
        }
        pause(scanner, "This modular design allows us to easily find any movie, screen, or booking in milliseconds.");




        // ---------------------------------------------------------
        // PART 6: THE FINAL BROADCAST (THEATER STATUS)
        // ---------------------------------------------------------
        printSection("6. SYSTEM SUMMARY (The Dashboard)");
        System.out.println(BOLD + "Movies Currently Showing:" + RESET);
        System.out.println(movieService.getAllMoviesFormatted());
        
        System.out.println("\n" + BOLD + "Active Showtimes:" + RESET);
        // Assuming we can list showtimes - let's print the one we made
        try {
            System.out.println(showTimeService.findById("ST01").displayInfo());
        } catch (Exception e) {}
        
        pause(scanner, "End by showing how all these modular services come together to provide a full dashboard view.");



        // ---------------------------------------------------------
        // CONCLUSION
        // ---------------------------------------------------------
        printHeader("🎉 DEMO COMPLETE: SCALABLE, SECURE, PROFESSIONAL.");
        System.out.println(CYAN + "THANK YOU FOR YOUR ATTENTION!" + RESET);
    }

    // HELPER METHODS FOR FORMATTING
    private static void printHeader(String text) {
        System.out.println("\n" + YELLOW + "********************************************************");
        System.out.println("  " + BOLD + text + RESET);
        System.out.println(YELLOW + "********************************************************" + RESET);
    }

    private static void printSection(String text) {
        System.out.println("\n" + BOLD + "--------------------------------------------------------");
        System.out.println(" " + text);
        System.out.println("--------------------------------------------------------" + RESET);
    }

    private static void pause(Scanner scanner, String talkPrompt) {
        System.out.println(BOLD + "\n[SPEAKER TIP]: " + YELLOW + talkPrompt + RESET);
        System.out.print(CYAN + ">>> Press [ENTER] to continue..." + RESET);
        scanner.nextLine();
    }
}

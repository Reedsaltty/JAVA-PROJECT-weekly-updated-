# Project Technical Details: CineBook

## Class and Method Reference

### 1. Models (Core Data Objects)
- **Movie.java**
  - `getMovieId()`: Returns the movie's unique identifier.
  - `getTitle()` / `setTitle(String)`: Manages the movie title (with validation).
  - `getDuration()` / `setDuration(int)`: Manages duration in minutes (with validation).
  - `getGenre()` / `setGenre(String)`: Manages the movie genre.
  - `displayInfo()`: Returns a formatted string of movie details.

- **Screen.java**
  - `getNumberOfSeats()` / `setNumberOfSeats(int)`: Manages the total seat capacity.
  - `displayInfo()`: Returns screen ID and capacity details.

- **Seat.java**
  - `getSeatNumber()`: Returns the seat number within its screen.
  - `getScreenId()`: Returns the ID of the screen this seat belongs to.
  - `isAvailable()` / `setAvailable(boolean)`: Manages the reservation status.
  - `displayInfo()`: Returns seat location and availability status.

- **ShowTime.java**
  - `getDateTime()`: Returns the scheduled time string.
  - `getMovie()`: Returns the Movie object for this session.
  - `getScreen()`: Returns the Screen object for this session.
  - `displayInfo()`: Returns a summary of the movie, time, and theater location.

- **Booking.java**
  - `getSeatId()`: Returns the specific seat identifier.
  - `getShowTime()`: Returns the associated ShowTime object.
  - `displayInfo()`: Returns a summary of the booking transaction.

### 2. Services (Business Logic Layer)
- **BaseService.java (Abstract)**
  - `findById(String)`: Generic search for any entity by its ID.
  - `add(T)`: Adds an entity to the internal storage list.
  - `getAll()`: Returns a list of all stored entities.
  - `remove(String)`: Deletes an entity by ID.

- **MovieService.java**
  - `addMovie(...)`: Registers a new movie with duplicate ID protection.
  - `getAllMovies()`: Returns list of all movies.
  - `getAllMoviesFormatted()`: Returns a single string of all movies for display.
  - `findMovieById(String)`: Returns a movie or throws ResourceNotFoundException.
  - `updateMovie(...)`: Modifies existing movie details.
  - `deleteMovie(String)`: Removes a movie from the system.
  - `displayAllMovies()`: Prints all movie information to console.

- **ScreenService.java**
  - `addScreen(Screen)`: Registers a screen and auto-initializes its seats.
  - `getAllScreens()`: Returns list of all screens.
  - `findById(String)`: Specialized screen lookup with error handling.

- **SeatService.java**
  - `initializeSeats(Screen)`: Populates a screen with individual Seat objects.
  - `getAvailableSeats(String)`: Returns all unbooked seats for a specific screen.
  - `findByNumber(String, int)`: Finds a specific seat on a specific screen.
  - `reserveSeat(String, int)`: Marks a seat as unavailable.
  - `releaseSeat(String, int)`: Marks a seat as available again.

- **ShowTimeService.java**
  - `addShowTime(...)`: Schedules a movie with conflict detection (no double-booking).
  - `getShowTimesByMovie(String)`: Filters sessions by movie.
  - `getShowTimesByScreen(String)`: Filters sessions by screen.
  - `findById(String)`: Specialized showtime lookup.
  - `getAllShowTimes()`: Returns all scheduled sessions.

- **BookingService.java**
  - `createBooking(...)`: The core engine—validates seat availability, reserves the seat, and saves the transaction record.
  - `findByBookingId(String)`: Retrieves a specific ticket record.
  - `displayAllBookings()`: Prints a list of all successful transactions.

### 3. Logic & Architecture
- **MovieTheaterApp.java (Entry Point)**
  - `main(String[])`: The starting point of the application.
  - `runProfessionalDemo(Scanner)`: Orchestrates the story-driven demonstration.
  - `pause(Scanner, String)`: Implements the manual "Press Enter" presentation flow.
  - `printHeader(String)` / `printSection(String)`: Utility methods for high-end terminal formatting.

---
*Reference generated for presentation preparation.*

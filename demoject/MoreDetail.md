Refactor Services: Use an abstract BaseService to handle the findByBookingId and findById logic for all your services. This will significantly reduce code duplication.
Precise Seat Lookup: Update SeatService with an overloaded findByNumber(String screenId, int seatNumber) method to ensure you're always booking the correct seat on the correct screen.
Displayable Interface: Implement a standard interface for models to display their information.
Exception Handling: I recommend switching the IllegalAccessError calls back to IllegalArgumentException (or a custom exception) to follow Java best practices for application-level validation.(use custom exeption);





// Logic without Overriding (A Maintenance Nightmare)
for (Object obj : items) {
    if (obj instanceof Movie) {
        Movie m = (Movie) obj;
        System.out.println("Movie: " + m.getTitle());
    } else if (obj instanceof Booking) {
        Booking b = (Booking) obj;
        System.out.println("Booking for: " + b.getShowTimeId());
    } else if (obj instanceof Screen) {
        Screen s = (Screen) obj;
        System.out.println("Screen size: " + s.getCapacity());
    }
    // Every time you add a new class, you have to add another 'else if'!
}



// Reference Type: Displayable (General)
// Created Object: Movie (Specific)
Displayable item = new Movie("MOV001", "The Matrix", 136);
Movie movie = (Movie) item;
MovieService movieService = new MovieService();
movieService.addMovie(movie);

// Even though 'item' is a Movie, you are treating it as a 'Displayable'
System.out.println(item.displayInfo()); 






// New Behavior Interface
public interface Priceable {
    double getPrice(double basePrice);
}

// Specialized Object Type
public class VipSeat extends Seat implements Priceable {
    public VipSeat(String screenId, int seatNumber) {
        super(screenId, seatNumber);
    }

    @Override
    public double getPrice(double basePrice) {
        return basePrice * 1.5; // VIP behavior: 50% more expensive
    }

    @Override
    public String displayInfo() {
        return "✨ VIP " + super.displayInfo();
    }
}

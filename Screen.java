
import java.util.ArrayList;

import interface_abstract.Displayable;
import interface_abstract.Entity;
public class Screen extends Entity implements Displayable{
    private ArrayList<Seat> seats;

    public Screen(String screenId , int numberOfSeat){
        super(screenId);
        setSeats(numberOfSeat);
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(int numberOfSeat) {
        this.seats = new ArrayList<>();
        for (int i = 1; i <= 250 ; i++) {
        Seat newSeat = new Seat(i, true);
        this.seats.add(newSeat);
        System.out.println( "Seat : " + i + " Available: " + newSeat.isAvailable());     // Create seats numbered 1 to numberOfSeats, all available
    }
    }

    @Override
    public String displayInfo() {
        // TODO Auto-generated method stub
        return null;
    }

    
}

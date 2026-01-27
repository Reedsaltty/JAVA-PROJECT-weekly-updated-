
import java.util.ArrayList;
public class Screen {
    public String screenId ;
    public ArrayList<Seat> seats;

    public Screen(String screenId , ArrayList<Seat> seats){
        this.screenId = screenId;
        this.seats = seats;
    }
}

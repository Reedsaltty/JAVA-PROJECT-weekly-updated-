
import java.util.ArrayList;
public class Screen {
    private String screenId ;
    private ArrayList<Seat> seats;

    public Screen(String screenId , ArrayList<Seat> seats){
        this.screenId = screenId;
        this.seats = seats;
    }

    public String getScreenId() {
        return screenId;
    }

    public void setScreenId(String screenId) {
        this.screenId = screenId;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }
}

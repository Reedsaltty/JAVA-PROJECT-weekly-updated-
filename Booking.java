import interface_abstract.Entity;

public class Booking extends Entity {
    private Seat yourSeat;
    private ShowTime showTime;
    
    public Booking(String bookingId, Seat yourSeat, ShowTime showTime){
        super(bookingId);
        setYourSeatId(yourSeat);
        setShowTime(showTime);
    }

    public Seat getYourSeat() {
        return yourSeat;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setYourSeatId(Seat yourSeat){
        this.yourSeat = yourSeat;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

}

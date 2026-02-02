public class Booking {
    private String bookingId ;
    private Seat yourSeat;
    private ShowTime showTime;
    
    public Booking(String bookingId, Seat yourSeat, ShowTime showTime){
        setBookingId(bookingId);
        setYourSeatId(yourSeat);
        setShowTime(showTime);
    }

    public String getBookingId() {
        return bookingId;
    }

    public Seat getYourSeat() {
        return yourSeat;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setBookingId(String bookingId){
        this.bookingId = bookingId;
    }

    public void setYourSeatId(Seat yourSeat){
        this.yourSeat = yourSeat;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

}

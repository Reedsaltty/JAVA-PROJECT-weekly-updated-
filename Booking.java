public class Booking {
    private String bookingId ;
    private Seat yourSeat;
    private ShowTime showTime;
    
    public Booking(String bookingId, Seat yourSeat, ShowTime showTime){
        this.showTime = showTime;
        getYourId(bookingId);
        getYourSeatId(yourSeat);
    }



    public String getYourId(String bookingId){
        this.bookingId = bookingId;
        System.out.println("Booking Id : " + bookingId);
        return bookingId;
    }

    public Seat getYourSeatId(Seat showTime){
        this.yourSeat = yourSeat;
        System.out.println("Seat : " + Seat.seatNumber);
        return yourSeat ;
    }

    public ShowTime getShowTime() {
        return showTime;
    }

    public void setShowTime(ShowTime showTime) {
        this.showTime = showTime;
    }

    public String getBookingId() {
        return bookingId;
    }

    public Seat getYourSeat() {
        return yourSeat;
    }

}

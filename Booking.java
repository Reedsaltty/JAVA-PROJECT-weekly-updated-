public class Booking {
    private String bookingId ;
    private Seat yourSeat;
    public ShowTime showTime;
    
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

    

}

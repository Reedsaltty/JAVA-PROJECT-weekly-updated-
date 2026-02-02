
public class Seat {
    private static int seatNumber;
    private boolean isAvailable;

    public Seat(int seatNumber, boolean isAvailable){
       setSeatNumber(seatNumber);
       setAvailable(isAvailable);
    }

    public static int getSeatNumber() {
        return seatNumber;
    }

    public static void setSeatNumber(int seatNumber) {
        Seat.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
}

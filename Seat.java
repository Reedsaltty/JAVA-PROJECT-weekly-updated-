import interface_abstract.Bookable;

public class Seat implements Bookable{
    private  int seatNumber;
    private boolean isAvailable;

    public Seat(int seatNumber, boolean isAvailable){
       setSeatNumber(seatNumber);
       setAvailable(isAvailable);
    }

    public  int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public boolean book() {
        // TODO Auto-generated method stub
        if (isAvailable){
            isAvailable = false;
            return true;
        }
        return false;
    }

    @Override
    public boolean cancel() {
        // TODO Auto-generated method stub
        if (book()){
            return true;
        }
        return false ;
    }
    
}

import interface_abstract.Displayable;
import interface_abstract.Entity;

public class ShowTime extends Entity implements Displayable {
    private String dateTime ;
    private Movie movie;

    public ShowTime(String showTimeId, String dateTime, Movie movie ){
        super(showTimeId);
        setDateTime(dateTime);
        setMovie(movie);
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String displayInfo() {
        // TODO Auto-generated method stub
        return null;
    } 
    
}

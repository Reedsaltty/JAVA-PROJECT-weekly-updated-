
public class ShowTime {
    private String showTimeId ;
    private String dateTime ;
    private Movie movie;

    public ShowTime(String showTimeId, String dateTime, Movie movie ){
        setDateTime(dateTime);
        setShowTimeId(showTimeId);
        setMovie(movie);
    }

    public String getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(String showTimeId) {
        this.showTimeId = showTimeId;
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
}

package com.example.models;
import com.example.interface_abstract.Displayable;
import com.example.interface_abstract.Entity;

public class ShowTime extends Entity implements Displayable {
    private String dateTime ;
    private Movie movie;
    private Screen screen; 
 
    public ShowTime(String showTimeId, String dateTime, Movie movie, Screen screen ){
        super(showTimeId);
        setDateTime(dateTime);
        setMovie(movie);
        setScreen(screen);

    }
    public void setScreen(Screen screen ){
        this.screen = screen;
    }

    public String getDateTime() {
        return dateTime;
    }
    public Screen getScreen(){
        return screen;
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
        return "ShowTime [ID: " + getId() + ", Time: " + dateTime + ", Movie: " + (movie != null ? movie.getTitle() : "N/A") + ", Screen: " + (screen != null ? screen.getId() : "N/A") + "]";
    }
}

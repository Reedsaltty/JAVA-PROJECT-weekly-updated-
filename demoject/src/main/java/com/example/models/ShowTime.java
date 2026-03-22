package com.example.models;
import com.example.interface_abstract.Entity;

public class ShowTime extends Entity  {
    private String dateTime ;
    private Movie movie;
    private Screen screen; 

    public ShowTime(String showTimeId, String dateTime, Movie movie, Screen screen ){
        super(showTimeId);
        setDateTime(dateTime);
        setMovie(movie);
        this.screen = screen ;

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



    public String displayInfo() {
        // TODO Auto-generated method stub
        return null;
    } 
    
}

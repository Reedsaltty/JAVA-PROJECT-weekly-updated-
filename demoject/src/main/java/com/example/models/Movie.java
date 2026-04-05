package com.example.models;
import com.example.interface_abstract.Displayable;
import com.example.interface_abstract.Entity;

public class Movie extends Entity implements Displayable {
    private String title ;
    private int duration ;
    private String genre ;
        
    public Movie(String id, String title, int duration) {
        super(id);
        setTitle(title);
        setDuration(duration);
        setGenre("Unknown");
    }
    
    public Movie(String movieId, String title , int duration, String genre){
        super(movieId);
        setTitle(title);
        setDuration(duration);
        setGenre(genre);

    }
    public String getMovieId(){
        return getId();
    }
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public int getDuration(){
        return duration;
    }
    public void setDuration(int duration){
        this.duration = duration;
    }
    public String getGenre() {
    return genre;
    }

    public void setGenre(String genre) {
    this.genre = genre;
    }
    @Override
    public String displayInfo() {
        return "Movie [Title: " + getTitle() + ", Duration: " + getDuration() + " mins, Genre: " + getGenre() + ", ID: " + getId() + "]";
    }
}

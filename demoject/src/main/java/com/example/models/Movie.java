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
    
    public void setTitle(String title) throws com.example.exceptions.ValidationException {
        if (title == null || title.trim().isEmpty()) {
            throw new com.example.exceptions.ValidationException("Movie title cannot be null or empty");
        }
        this.title = title;
    }
    
    public int getDuration(){
        return duration;
    }
    public void setDuration(int duration) throws com.example.exceptions.ValidationException {
        if (duration <= 0) {
            throw new com.example.exceptions.ValidationException("Movie duration must be greater than 0");
        }
        this.duration = duration;
    }
    public String getGenre() {
    return genre;
    }

    public void setGenre(String genre) throws com.example.exceptions.ValidationException {
        if (genre == null || genre.trim().isEmpty()) {
            throw new com.example.exceptions.ValidationException("Movie genre cannot be null or empty");
        }
        this.genre = genre;
    }
    @Override
    public String displayInfo() {
        return "Movie [Title: " + getTitle() + ", Duration: " + getDuration() + " mins, Genre: " + getGenre() + ", ID: " + getId() + "]";
    }
}

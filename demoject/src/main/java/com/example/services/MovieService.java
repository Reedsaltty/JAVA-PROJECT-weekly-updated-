package com.example.services;

import com.example.models.Movie;
import com.example.exceptions.ResourceNotFoundException;
import java.util.List;
import com.example.exceptions.ValidationException;

public class MovieService extends BaseService<Movie> {
    
    public void addMovie(Movie movie){
        Movie addedMovie = findById(movie.getId());
        if (addedMovie != null) {
            throw new ValidationException("Movie already exists: " + movie.getId());
        }   
        add(movie);
    }

    public void addMovie(String movieId, String title, int duration, String genre){
        Movie movie = new Movie(movieId, title, duration, genre);
        addMovie(movie);
    }

    public List<Movie> getAllMovies() {
        return getAll();
    }
    
    public Movie findMovieById(String movieId){
        Movie movie = findById(movieId);
        if (movie == null) {
            throw new ResourceNotFoundException("Movie not found: " + movieId);
        }
        return movie;
    }


    public void updateMovie(String movieid, String title, int duration, String genre) {
        Movie movieToUpdate = findMovieById(movieid);
        movieToUpdate.setTitle(title);
        movieToUpdate.setDuration(duration);
        movieToUpdate.setGenre(genre);
    }

    public void deleteMovie(String movieId) {
        findMovieById(movieId); // throws ResourceNotFoundException if not found
        remove(movieId);
    }

    public void displayAllMovies(){
        List<Movie> movies = getAll();
        if (movies.isEmpty()){
            System.out.println("No movie available");
        }
        for(Movie movie : movies ){
            System.out.println(movie.displayInfo());
        }
    }
}

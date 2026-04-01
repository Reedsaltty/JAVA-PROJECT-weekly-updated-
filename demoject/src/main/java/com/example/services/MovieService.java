package com.example.services;

import com.example.models.Movie;
import com.example.exceptions.ResourceNotFoundException;
import java.util.List;

public class MovieService extends BaseService<Movie> {
    
    public void addMovie(Movie movie){
        add(movie);
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

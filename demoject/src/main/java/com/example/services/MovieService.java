package com.example.services;

import com.example.models.Movie;
import java.util.ArrayList;





public class MovieService {
    // getAllMovies(), addMovie(), updateMovie(), deleteMovie(). Annotated @Service. Calls MovieRepository.
    private ArrayList<Movie> movies = new ArrayList<>(); 

    public void addMovie(Movie movie){
        movies.add(movie);
    }
    public ArrayList<Movie> getAllMovies() {
        return movies;
    }
    public Movie findMovieById(String movieId){
        for(Movie m : movies){
            if (m.getId().equals(movieId)) {
                return m;
            }
        }
        return null;
        
    };
    public void updateMovie(String movieid, String title ,int duration , String genre ){
        Movie movieToUpdate =findMovieById(movieid);
        if (movieToUpdate != null){
            movieToUpdate.setTitle(title);
            movieToUpdate.setDuration(duration);
            movieToUpdate.setGenre(genre);
        }
    
    }
     public void deleteMovie(String movieId) {
        movies.removeIf(m -> m.getMovieId().equals(movieId));
    }


}



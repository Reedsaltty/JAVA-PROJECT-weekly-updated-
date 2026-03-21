package com.example;

import com.example.models.Movie;

import com.example.services.MovieService;

/**
 * Hello world!
 *
 */
public class MovieTheaterApp 
{
    public static void main( String[] args )
    {
       MovieService movieList  = new MovieService();
       Movie spiderman = new Movie("MOv11", "Stupid man", 60, "Drama");
       movieList.addMovie(spiderman);
       movieList.displayAllMovies();
       movieList.updateMovie("MOv11", "Stupid Man", 70, "Horror");
       movieList.displayAllMovies();
       movieList.deleteMovie("MOv11");
       movieList.displayAllMovies();
    }
}

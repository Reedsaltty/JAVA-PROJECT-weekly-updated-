package com.example.services;

import com.example.models.ShowTime;
import com.example.models.Movie;
import com.example.models.Screen;
import com.example.exceptions.ResourceNotFoundException;
import com.example.exceptions.ValidationException;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ShowTimeService extends BaseService<ShowTime> {

    private MovieService movieService;
    private ScreenService screenService;

    public ShowTimeService(MovieService movieService, ScreenService screenService) {
        this.movieService  = movieService;
        this.screenService = screenService;
    }

    public void addShowTime(String showTimeId, String dateTime, String movieId, String screenId) {
        Movie movie   = movieService.findMovieById(movieId);
        Screen screen = screenService.findById(screenId);

        if (hasConflict(screenId, dateTime)) {
            throw new ValidationException("Screen " + screenId + " already has a showtime at " + dateTime);
        }

        ShowTime showTime = new ShowTime(showTimeId, dateTime, movie, screen);
        add(showTime);

        System.out.println("ShowTime added: " + showTime.displayInfo());
    }

    private boolean hasConflict(String screenId, String dateTime) {
        for (ShowTime st : entities)
            if (st.getScreen().getId().equals(screenId)
                    && st.getDateTime().equals(dateTime))
                return true;
        return false;
    }

    public List<ShowTime> getShowTimesBy(Predicate<ShowTime> condition) {
        List<ShowTime> result = new ArrayList<>();
        for (ShowTime st : entities)
            if (condition.test(st)) result.add(st);
        return result;
    }

    public List<ShowTime> getShowTimesByMovie(String movieId) {
        return getShowTimesBy(st -> st.getMovie().getMovieId().equals(movieId));
    }

    public List<ShowTime> getShowTimesByScreen(String screenId) {
        return getShowTimesBy(st -> st.getScreen().getId().equals(screenId));
    }

    @Override
    public ShowTime findById(String showTimeId) {
        ShowTime st = super.findById(showTimeId);
        if (st == null) {
            throw new ResourceNotFoundException("ShowTime not found: " + showTimeId);
        }
        return st;
    }

    public List<ShowTime> getAllShowTimes() {
        return getAll();
    }
}

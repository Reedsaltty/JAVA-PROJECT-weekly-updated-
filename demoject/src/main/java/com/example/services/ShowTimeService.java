package com.example.services;

import com.example.models.ShowTime;
import com.example.models.Movie;
import com.example.models.Screen;
import java.util.ArrayList;
import java.util.function.Predicate;

public class ShowTimeService {

    private ArrayList<ShowTime> showTimes = new ArrayList<>();

    // Dependencies
    private MovieService movieService;
    private ScreenService screenService;

    public ShowTimeService(MovieService movieService, ScreenService screenService) {
        this.movieService  = movieService;
        this.screenService = screenService;
    }

    // ── Add a showtime ───────────────────────────────────────────

    public void addShowTime(String showTimeId, String dateTime,
                            String movieId, String screenId) {

        // Step 1 — find movie and screen by ID
        Movie movie   = movieService.findMovieById(movieId);
        Screen screen = screenService.findById(screenId);

        if (movie == null)
            throw new IllegalAccessError("Movie not found: " + movieId);
        if (screen == null)
            throw new IllegalAccessError("Screen not found: " + screenId);

        // Step 2 — check no overlap on same screen at same time
        if (hasConflict(screenId, dateTime))
            throw new IllegalArgumentException(
                "Screen " + screenId + " already has a showtime at " + dateTime);

        // Step 3 — create and store
        ShowTime showTime = new ShowTime(showTimeId, dateTime, movie, screen);
        showTimes.add(showTime);

        System.out.println("ShowTime added: " + showTime.displayInfo());
    }

    // ── Conflict check ───────────────────────────────────────────

    private boolean hasConflict(String screenId, String dateTime) {
        for (ShowTime st : showTimes)
            if (st.getScreen().getId().equals(screenId)
                    && st.getDateTime().equals(dateTime))
                return true;
        return false;
    }

    // ── Predicate filter ─────────────────────────────────────────

    public ArrayList<ShowTime> getShowTimesBy(Predicate<ShowTime> condition) {
        ArrayList<ShowTime> result = new ArrayList<>();
        for (ShowTime st : showTimes)
            if (condition.test(st)) result.add(st);
        return result;
    }

    public ArrayList<ShowTime> getShowTimesByMovie(String movieId) {
        return getShowTimesBy(st -> st.getMovie().getMovieId().equals(movieId));
    }

    public ArrayList<ShowTime> getShowTimesByScreen(String screenId) {
        return getShowTimesBy(st -> st.getScreen().getId().equals(screenId));
    }

    // ── Finder ───────────────────────────────────────────────────

    public ShowTime findById(String showTimeId) {
        for (ShowTime st : showTimes)
            if (st.getId().equals(showTimeId)) return st;
        return null;
    }

    public ArrayList<ShowTime> getAllShowTimes() {
        return showTimes;
    }
}
package com.samir.popularmovies.service.integration;


import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;

public class MoviedbPopularCommand implements Command {

    private final String action;

    public MoviedbPopularCommand() {
        action = AplicationPopularMovies.getContext().getString(R.string.popular);
    }

    @Override
    public String getCommand() {
        return action;
    }
}

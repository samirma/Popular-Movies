package com.samir.popularmovies.service.integration;


import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.service.SettiringManager;

public class MoviedbTopRatedCommand implements Command {

    private final String action;

    public MoviedbTopRatedCommand() {
        action = AplicationPopularMovies.getContext().getString(R.string.top_rated);
    }

    @Override
    public String getCommand() {
        return action;
    }
}

package com.samir.popularmovies.service.integration.commands;


import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.PersistenceService;
import com.samir.popularmovies.service.integration.MovieCommand;

import java.io.IOException;
import java.util.List;

public class MoviedbFavoritedCommand implements MovieCommand {

    private final String action;

    public MoviedbFavoritedCommand() {
        action = AplicationPopularMovies.getContext().getString(R.string.top_rated);
    }

    @Override
    public String getCommand() {
        return action;
    }

    @Override
    public List<Movie> getMovies() throws IOException {
        return new PersistenceService().getMovies();
    }
}

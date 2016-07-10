package com.samir.popularmovies.service.integration.commands;


import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Page;
import com.samir.popularmovies.service.integration.HttpCommand;
import com.samir.popularmovies.service.integration.MovieCommand;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MoviedbPopularCommand extends HttpCommand implements MovieCommand {

    private final String action;

    public MoviedbPopularCommand() {
        action = AplicationPopularMovies.getContext().getString(R.string.popular);
    }

    @Override
    public List<Movie> getMovies() throws IOException {

        final String result = getHttpString();

        final Page page = GSON.fromJson(result, Page.class);

        return Arrays.asList(page.getResults());
    }


    @Override
    public String getCommand() {
        return action;
    }
}

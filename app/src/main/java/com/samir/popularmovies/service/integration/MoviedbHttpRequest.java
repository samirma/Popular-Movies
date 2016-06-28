package com.samir.popularmovies.service.integration;


import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.BuildConfig;
import com.samir.popularmovies.R;

public class MoviedbHttpRequest {

    private static final String API_KEY = BuildConfig.THE_MOVIE_DATABASE_API_KEY;

    private static final String API_KEY_APPEND = "?api_key=" + API_KEY;
    public static final String SERVER = AplicationPopularMovies.getContext().getString(R.string.server);

    private Command action;

    public MoviedbHttpRequest(Command action) {
        this.action = action;
    }

    public String getUrl() {
        return String.format("%s%s%s", SERVER, action.getCommand(), API_KEY_APPEND);
    }

}

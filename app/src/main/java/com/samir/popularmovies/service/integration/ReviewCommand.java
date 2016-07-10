package com.samir.popularmovies.service.integration;

import android.content.Context;

import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;

/**
 * Created by samirmoreira on 7/6/16.
 */

public class ReviewCommand implements Command {

    private Movie movie;

    public ReviewCommand(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String getCommand() {


        final Context context = AplicationPopularMovies.getContext();
        String command = String.format(context.getString(R.string.review), String.valueOf(movie.id));
        
        return command;
    }
}

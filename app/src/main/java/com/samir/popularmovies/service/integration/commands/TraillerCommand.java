package com.samir.popularmovies.service.integration.commands;

import android.content.Context;

import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.integration.Command;


public class TraillerCommand implements Command {

    private Movie movie;

    public TraillerCommand(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String getCommand() {


        final Context context = AplicationPopularMovies.getContext();
        String command = String.format(context.getString(R.string.trailers), String.valueOf(movie.getId()));
        
        return command;
    }
}

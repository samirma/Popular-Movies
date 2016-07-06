package com.samir.popularmovies.service;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Page;
import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThemoviedbService {


    public ThemoviedbService() {
    }


    public void requestMovies(final ThemoviedbDelegate delegate) {

        final MovieListAsyncTask movieListAsyncTask = new MovieListAsyncTask(delegate);
        movieListAsyncTask.execute();

    }


    public String getThumbnail(Movie movie) {
        final Context context = AplicationPopularMovies.getContext();
        final String format = String.format("%s%s%s", context.getString(R.string.server_img), context.getString(R.string.w185), movie.poster_path);
        return format;
    }

    public String getBackdrop(Movie movie) {
        final Context context = AplicationPopularMovies.getContext();
        final String format = String.format("%s%s%s", context.getString(R.string.server_img), context.getString(R.string.w500), movie.poster_path);
        return format;
    }

    public void loadTrailers(final Movie movie, final ThemoviedbDelegate delegate) {

        final TrailerListAsyncTask trailerListAsyncTask = new TrailerListAsyncTask(movie, delegate);
        trailerListAsyncTask.execute();
    }
}

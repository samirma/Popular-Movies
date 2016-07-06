package com.samir.popularmovies.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.trailer.Trailer;
import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;
import com.samir.popularmovies.service.integration.TraillerCommand;


public class TrailerListAsyncTask extends AsyncTask<Command, Void, Trailer>  {

    private static final String TAG = TrailerListAsyncTask.class.getSimpleName();
    private Movie movie;
    private ThemoviedbDelegate delegate;

    public TrailerListAsyncTask(Movie movie, ThemoviedbDelegate delegate) {
        this.movie = movie;
        this.delegate = delegate;
    }

    @Override
    protected Trailer doInBackground(Command... params) {

        Command command = new TraillerCommand(movie);

        Trailer trailer = null;

        try {
            final HttpClient httpClient = new HttpClient();
            final MoviedbHttpRequest moviedbHttpRequest = new MoviedbHttpRequest(command);
            final String result = httpClient.execute(moviedbHttpRequest);

            trailer  = new Gson().fromJson(result, Trailer.class);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }


        return trailer;
    }
}

package com.samir.popularmovies.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.trailer.Trailer;
import com.samir.popularmovies.model.trailer.TrailerDetail;
import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;
import com.samir.popularmovies.service.integration.TraillerCommand;

import java.util.List;


public class TrailerListAsyncTask extends AsyncTask<Command, Void, Trailer>  {

    private static final String TAG = TrailerListAsyncTask.class.getSimpleName();
    private Movie movie;
    private ThemoviedbTrailerDelegate delegate;

    public TrailerListAsyncTask(Movie movie, ThemoviedbTrailerDelegate delegate) {
        this.movie = movie;
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        delegate.onPreExecute();
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


    @Override
    protected void onPostExecute(Trailer trailer) {
        super.onPostExecute(trailer);
        final TrailerDetail[] results = trailer.getResults();

        for (TrailerDetail trailerDetail:results) {
            delegate.add(trailerDetail);
        }

        delegate.posExecute();

    }
}

package com.samir.popularmovies.service;

import android.os.AsyncTask;
import android.util.Log;

import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.MovieDB;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ThemoviedbService extends AsyncTask<Void, Void, List<MovieDB>> {

    public static final String TAG = ThemoviedbService.class.getSimpleName();
    private ThemoviedbDelegate delegate;

    public ThemoviedbService() {
    }

    @Override
    protected List<MovieDB> doInBackground(Void... params) {

        final ArrayList<MovieDB> movieDBs = new ArrayList<>();

        try {
            final HttpClient httpClient = new HttpClient();
            final MoviedbHttpRequest moviedbHttpRequest = new MoviedbHttpRequest(AplicationPopularMovies.getContext().getString(R.string.popular));
            final String result = httpClient.execute(moviedbHttpRequest);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }


        return movieDBs;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        delegate.onPreExecute();
    }

    public void requestMovies(ThemoviedbDelegate delegate) {
        this.delegate = delegate;

        execute();

    }

    @Override
    protected void onPostExecute(List<MovieDB> movieDBs) {

        for (MovieDB movieDB:movieDBs) {
            delegate.add(movieDB);
        }
    }
}

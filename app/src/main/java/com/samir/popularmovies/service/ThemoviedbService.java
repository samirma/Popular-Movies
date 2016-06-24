package com.samir.popularmovies.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Page;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThemoviedbService extends AsyncTask<Void, Void, List<Movie>> {

    public static final String TAG = ThemoviedbService.class.getSimpleName();
    private ThemoviedbDelegate delegate;

    public ThemoviedbService() {
    }

    @Override
    protected List<Movie> doInBackground(Void... params) {

        final ArrayList<Movie> movieDBs = new ArrayList<>();

        String result = null;

        try {
            final HttpClient httpClient = new HttpClient();
            final MoviedbHttpRequest moviedbHttpRequest = new MoviedbHttpRequest(AplicationPopularMovies.getContext().getString(R.string.popular));
            result = httpClient.execute(moviedbHttpRequest);

            final Page page = new Gson().fromJson(result, Page.class);

            movieDBs.addAll(Arrays.asList(page.getResults()));

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
    protected void onPostExecute(List<Movie> movieDBs) {

        for (Movie movieDB:movieDBs) {
            delegate.add(movieDB);
        }
    }
}

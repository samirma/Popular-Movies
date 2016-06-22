package com.samir.popularmovies.service;

import android.os.AsyncTask;

import com.samir.popularmovies.model.MovieDB;

import java.util.ArrayList;
import java.util.List;

public class ThemoviedbService extends AsyncTask<Void, Void, List<MovieDB>> {

    private ThemoviedbDelegate delegate;

    public ThemoviedbService() {
    }

    @Override
    protected List<MovieDB> doInBackground(Void... params) {

        final ArrayList<MovieDB> movieDBs = new ArrayList<>();

        movieDBs.add(new MovieDB());
        movieDBs.add(new MovieDB());
        movieDBs.add(new MovieDB());
        movieDBs.add(new MovieDB());
        movieDBs.add(new MovieDB());

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

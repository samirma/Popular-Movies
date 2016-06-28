package com.samir.popularmovies.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Page;
import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThemoviedbService extends AsyncTask<Command, Void, List<Movie>> {

    public static final String TAG = ThemoviedbService.class.getSimpleName();
    private ThemoviedbDelegate delegate;

    public ThemoviedbService() {
    }

    @Override
    protected List<Movie> doInBackground(Command... params) {

        Command command;
        if (params != null && params.length > 0){
            command = params[0];
        } else {
            command = SettiringManager.getSettiringManager().getSelectedCommad();
        }

        final ArrayList<Movie> movieDBs = new ArrayList<>();

        try {
            final HttpClient httpClient = new HttpClient();
            final MoviedbHttpRequest moviedbHttpRequest = new MoviedbHttpRequest(command);
            final String result = httpClient.execute(moviedbHttpRequest);

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

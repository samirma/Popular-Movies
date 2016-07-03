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

    public static final String TAG = ThemoviedbService.class.getSimpleName();
    private ThemoviedbDelegate delegate;

    public ThemoviedbService() {
    }

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

    protected void onPreExecute() {
        delegate.onPreExecute();
    }

    public void requestMovies(ThemoviedbDelegate delegate) {
        this.delegate = delegate;

        new AsyncTask<Command, Void, List<Movie>>() {

            @Override
            protected List<Movie> doInBackground(Command... params) {
                return ThemoviedbService.this.doInBackground(params);
            }


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                ThemoviedbService.this.onPreExecute();
            }

            @Override
            protected void onPostExecute(List<Movie> movieDBs) {
                ThemoviedbService.this.onPostExecute(movieDBs);
            }

        }.execute();

    }

    protected void onPostExecute(List<Movie> movieDBs) {

        for (Movie movieDB:movieDBs) {
            delegate.add(movieDB);
        }

        delegate.posExecute();

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

}

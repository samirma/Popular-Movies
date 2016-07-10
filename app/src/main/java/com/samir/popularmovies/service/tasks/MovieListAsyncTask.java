package com.samir.popularmovies.service.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.SettiringManager;
import com.samir.popularmovies.service.ThemoviedbMoviesDelegate;
import com.samir.popularmovies.service.integration.MovieCommand;

import java.util.ArrayList;
import java.util.List;


public class MovieListAsyncTask extends AsyncTask<MovieCommand, Void, List<Movie>> {

    public static final String TAG = MovieListAsyncTask.class.getSimpleName();

    ThemoviedbMoviesDelegate delegate;


    public MovieListAsyncTask(ThemoviedbMoviesDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        delegate.onPreExecute();
    }

    protected List<Movie> doInBackground(MovieCommand... params) {

        MovieCommand command;
        if (params != null && params.length > 0){
            command = params[0];
        } else {
            command = SettiringManager.getSettiringManager().getSelectedCommad();
        }

        final ArrayList<Movie> movieDBs = new ArrayList<>();

        try {

            final List<Movie> movies = command.getMovies();

            movieDBs.addAll(movies);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }


        return movieDBs;
    }


    protected void onPostExecute(List<Movie> movieDBs) {

        for (Movie movieDB:movieDBs) {
            delegate.add(movieDB);
        }

        delegate.posExecute();

    }

}

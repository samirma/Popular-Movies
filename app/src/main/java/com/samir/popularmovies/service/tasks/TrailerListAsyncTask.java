package com.samir.popularmovies.service.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Trailer;
import com.samir.popularmovies.model.TrailerDetail;
import com.samir.popularmovies.service.PersistenceService;
import com.samir.popularmovies.service.ThemoviedbTrailerDelegate;
import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;
import com.samir.popularmovies.service.integration.commands.TraillerCommand;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TrailerListAsyncTask extends AsyncTask<Command, Void, List<TrailerDetail>>  {

    private static final String TAG = TrailerListAsyncTask.class.getSimpleName();
    private Movie movie;
    private ThemoviedbTrailerDelegate delegate;

    public static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .serializeNulls()
            .create();


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
    protected List<TrailerDetail> doInBackground(Command... params) {

        Command command = new TraillerCommand(movie);

        final ArrayList<TrailerDetail> trailerList = new ArrayList<>();


        try {

            if (movie.isFavorited) {

                final List<TrailerDetail> trailers = new PersistenceService().getTrailers(movie);
                trailerList.addAll(trailers);

            } else {

                final HttpClient httpClient = new HttpClient();
                final MoviedbHttpRequest moviedbHttpRequest = new MoviedbHttpRequest(command);
                final String result = httpClient.execute(moviedbHttpRequest);

                final String fixed = result.replaceAll("\"id\"", "review_id");

                Trailer trailer = GSON.fromJson(fixed, Trailer.class);

                trailerList.addAll(Arrays.asList(trailer.getResults()));

            }

        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }


        return trailerList;
    }


    @Override
    protected void onPostExecute(List<TrailerDetail> trailers) {
        super.onPostExecute(trailers);

            for (TrailerDetail trailerDetail : trailers) {
                delegate.add(trailerDetail);
            }

        delegate.posExecute();

    }
}

package com.samir.popularmovies.service;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Review;
import com.samir.popularmovies.model.ReviewDetail;
import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;
import com.samir.popularmovies.service.integration.ReviewCommand;

import java.lang.reflect.Modifier;


public class ReviewListAsyncTask extends AsyncTask<Command, Void, Review>  {

    private static final String TAG = ReviewListAsyncTask.class.getSimpleName();
    private Movie movie;
    private ThemoviedbReviewDelegate delegate;

    public static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .serializeNulls()
            .create();

    public ReviewListAsyncTask(Movie movie, ThemoviedbReviewDelegate delegate) {
        this.movie = movie;
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        delegate.onPreExecute();
    }

    @Override
    protected Review doInBackground(Command... params) {

        Command command = new ReviewCommand(movie);

        Review review = null;

        try {
            final HttpClient httpClient = new HttpClient();
            final MoviedbHttpRequest moviedbHttpRequest = new MoviedbHttpRequest(command);
            final String result = httpClient.execute(moviedbHttpRequest);

            review  = GSON.fromJson(result, Review.class);

        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }


        return review;
    }


    @Override
    protected void onPostExecute(Review review) {
        super.onPostExecute(review);
        final ReviewDetail[] results = review.results;

        for (ReviewDetail detail:results) {
            delegate.add(detail);
        }

        delegate.posExecute();

    }
}

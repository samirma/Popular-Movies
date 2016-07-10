package com.samir.popularmovies.service.tasks;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Review;
import com.samir.popularmovies.model.ReviewDetail;
import com.samir.popularmovies.model.TrailerDetail;
import com.samir.popularmovies.service.PersistenceService;
import com.samir.popularmovies.service.ThemoviedbReviewDelegate;
import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.HttpClient;
import com.samir.popularmovies.service.integration.MoviedbHttpRequest;
import com.samir.popularmovies.service.integration.commands.ReviewCommand;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ReviewListAsyncTask extends AsyncTask<Command, Void, List<ReviewDetail>>  {

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
    protected List<ReviewDetail> doInBackground(Command... params) {

        Command command = new ReviewCommand(movie);

        final List<ReviewDetail> list = new ArrayList<>();


        try {

            if (movie.isFavorited) {

                final List<ReviewDetail> reviews = new PersistenceService().getReviews(movie);
                list.addAll(reviews);

            } else {

                final HttpClient httpClient = new HttpClient();
                final MoviedbHttpRequest moviedbHttpRequest = new MoviedbHttpRequest(command);
                final String result = httpClient.execute(moviedbHttpRequest);

                final Review review = GSON.fromJson(result, Review.class);

                list.addAll(Arrays.asList(review.results));

            }


        } catch (Exception e) {
            Log.e(TAG, e.getMessage(), e);
        }


        return list;
    }


    @Override
    protected void onPostExecute(List<ReviewDetail> reviews) {
        super.onPostExecute(reviews);

        for (ReviewDetail detail:reviews) {
            delegate.add(detail);
        }

        delegate.posExecute();

    }
}

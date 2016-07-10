package com.samir.popularmovies.service;

import com.activeandroid.Model;
import com.activeandroid.query.Select;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.ReviewDetail;
import com.samir.popularmovies.model.TrailerDetail;

import java.util.List;

/**
 * Created by samir on 7/10/16.
 */
public class PersistenceService {
    public void save(Movie movie, final List<TrailerDetail> trailers, final List<ReviewDetail> reviews) {

        movie.save();


        for (TrailerDetail detail: trailers) {
            detail.movieId = movie.id;
            detail.save();
        }

        for (ReviewDetail detail: reviews) {
            detail.movieId = movie.id;
            detail.save();
        }
    }

    public List<Movie> getMovies() {
        final List<Movie> execute = new Select()
                .from(Movie.class).execute();
        return execute;
    }

    public List<TrailerDetail> getTrailers(final Movie movie) {
        final List<TrailerDetail> execute = new Select()
                .from(TrailerDetail.class)
                .where("movieId = ?", movie.id)
                .execute();
        return execute;
    }

    public List<ReviewDetail> getReviews(final Movie movie) {
        final List<ReviewDetail> execute = new Select()
                .from(ReviewDetail.class)
                .where("movieId = ?", movie.id)
                .execute();
        return execute;
    }

    public boolean isMovieAdded(Movie movie) {
        final Model executeSingle = new Select().from(Movie.class)
                .where("idJson = ?", movie.id)
                .executeSingle();
        return  executeSingle != null;
    }
}

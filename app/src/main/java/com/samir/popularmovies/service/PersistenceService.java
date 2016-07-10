package com.samir.popularmovies.service;

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
}

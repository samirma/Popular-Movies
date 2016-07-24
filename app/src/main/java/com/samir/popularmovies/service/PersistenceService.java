package com.samir.popularmovies.service;


import com.orm.query.Condition;
import com.orm.query.Select;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Review;
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
            detail.movieId = movie.getId();
            detail.save();
        }

        for (ReviewDetail detail: reviews) {
            detail.movieId = movie.getId();
            detail.save();
        }
    }

    public List<Movie> getMovies() {
        final List<Movie> movies = Movie.listAll(Movie.class);
        return movies;
    }

    public List<TrailerDetail> getTrailers(final Movie movie) {

        final List<TrailerDetail> list = Select.from(TrailerDetail.class)
                .where(Condition.prop("MOVIE_ID").eq(movie.getId()))
                .list();

        return list;
    }

    public List<ReviewDetail> getReviews(final Movie movie) {
        final List<ReviewDetail> list = Select.from(ReviewDetail.class)
                .where(Condition.prop("MOVIE_ID").eq(movie.getId()))
                .list();

        return list;
    }

    public boolean isMovieAdded(Movie movie) {
        final Movie movie1 = Movie.findById(Movie.class, movie.getId());
        return  movie1 != null;
    }

    public void remove(Movie movie) {
        final Long id = movie.getId();
        movie.delete();
        ReviewDetail.deleteAll(ReviewDetail.class, "MOVIE_ID = ?", String.valueOf(id));
        TrailerDetail.deleteAll(TrailerDetail.class, "MOVIE_ID = ?", String.valueOf(id));
    }
}

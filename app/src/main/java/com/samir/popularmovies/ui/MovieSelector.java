package com.samir.popularmovies.ui;

import com.samir.popularmovies.model.Movie;

/**
 * Created by samir on 7/24/16.
 */
public interface MovieSelector {
    Movie getSelectedMovie();

    void setSelectedMovie(Movie selectedMovie);

    boolean ismTwoPane();
}

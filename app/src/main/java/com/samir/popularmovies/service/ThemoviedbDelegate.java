package com.samir.popularmovies.service;

import com.samir.popularmovies.model.Movie;

public interface ThemoviedbDelegate {
    void add(Movie movieDB);

    void onPreExecute();
}

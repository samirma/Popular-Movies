package com.samir.popularmovies.service;

import com.samir.popularmovies.model.MovieDB;

public interface ThemoviedbDelegate {
    void add(MovieDB movieDB);

    void onPreExecute();
}

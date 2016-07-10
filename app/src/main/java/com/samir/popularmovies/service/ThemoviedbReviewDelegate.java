package com.samir.popularmovies.service;

import com.samir.popularmovies.model.ReviewDetail;

public interface ThemoviedbReviewDelegate extends ThemoviedbDelegate {

    void add(ReviewDetail detail);
}

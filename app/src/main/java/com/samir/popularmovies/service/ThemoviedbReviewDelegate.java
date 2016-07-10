package com.samir.popularmovies.service;

import com.samir.popularmovies.model.review.ReviewDetail;
import com.samir.popularmovies.model.trailer.TrailerDetail;

public interface ThemoviedbReviewDelegate extends ThemoviedbDelegate {

    void add(ReviewDetail detail);
}

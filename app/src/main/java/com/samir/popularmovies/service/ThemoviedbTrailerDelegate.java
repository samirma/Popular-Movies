package com.samir.popularmovies.service;

import com.samir.popularmovies.model.trailer.Trailer;
import com.samir.popularmovies.model.trailer.TrailerDetail;

public interface ThemoviedbTrailerDelegate extends ThemoviedbDelegate {

    void add(TrailerDetail trailer);

}

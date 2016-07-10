package com.samir.popularmovies.service.integration;

import com.samir.popularmovies.model.Movie;

import java.io.IOException;
import java.util.List;

/**
 * Created by samir on 7/10/16.
 */

public interface MovieCommand extends Command {

    List<Movie> getMovies() throws IOException;


}

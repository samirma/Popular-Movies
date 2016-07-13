package com.samir.popularmovies.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.ThemoviedbService;

import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity  {

    public static final String MOVIE = "movie";
    private Movie movie;

    private ThemoviedbService themoviedbService;

    MovieDetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        themoviedbService = new ThemoviedbService();

        movie = getIntent().getExtras().getParcelable(MOVIE);

        final String title = movie.title;

        detailFragment = (MovieDetailFragment) getSupportFragmentManager().findFragmentById(R.id.movie_detail_fragment);

        detailFragment.setMovie(movie);

    }


}

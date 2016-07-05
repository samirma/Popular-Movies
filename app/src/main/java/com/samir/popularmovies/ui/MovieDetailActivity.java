package com.samir.popularmovies.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.ThemoviedbService;
import com.samir.popularmovies.util.DateUtil;
import com.squareup.picasso.Picasso;

public class MovieDetailActivity extends AppCompatActivity {

    public static final String MOVIE = "movie";
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        movie = (Movie) getIntent().getExtras().getParcelable(MOVIE);


        final String title = movie.title;
        toolbar.setTitle(title);


        String imageUrl = new ThemoviedbService().getBackdrop(movie);

        final ImageView backdrop = (ImageView)findViewById(R.id.backdrop);

        Picasso.with(this)
                .load(imageUrl)
                .into(backdrop);


        setTextIntoTexview(movie.original_title, R.id.title);
        setTextIntoTexview(movie.overview, R.id.synopsis);
        setTextIntoTexview(movie.vote_average, R.id.rating);
        setTextIntoTexview(DateUtil.userFriendlyDate(movie.release_date), R.id.release_date);

    }

    public void setTextIntoTexview(final String text, Integer id) {
        final TextView textView = (TextView)findViewById(id);
        textView.setText(text);
    }

}

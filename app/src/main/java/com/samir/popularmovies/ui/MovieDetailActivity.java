package com.samir.popularmovies.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.ReviewDetail;
import com.samir.popularmovies.model.TrailerDetail;
import com.samir.popularmovies.service.PersistenceService;
import com.samir.popularmovies.service.ThemoviedbReviewDelegate;
import com.samir.popularmovies.service.ThemoviedbService;
import com.samir.popularmovies.service.ThemoviedbTrailerDelegate;
import com.samir.popularmovies.ui.adapter.ReviewAdapter;
import com.samir.popularmovies.ui.adapter.TrailerAdapter;
import com.samir.popularmovies.util.DateUtil;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity  {

    public static final String MOVIE = "movie";
    private Movie movie;

    @BindView(R.id.backdrop)
    ImageView backdrop;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ThemoviedbService themoviedbService;

    private ProgressDialog progress;



    private final PersistenceService service = new PersistenceService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        themoviedbService = new ThemoviedbService();

        movie = getIntent().getExtras().getParcelable(MOVIE);


        final String title = movie.title;
        toolbar.setTitle(title);


        String imageUrl = themoviedbService.getBackdrop(movie);

        Picasso.with(this)
                .load(imageUrl)
                .into(backdrop);



    }


}

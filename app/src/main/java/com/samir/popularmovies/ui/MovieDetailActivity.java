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
import com.orm.SugarRecord;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.review.ReviewDetail;
import com.samir.popularmovies.model.trailer.TrailerDetail;
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

public class MovieDetailActivity extends AppCompatActivity implements ThemoviedbTrailerDelegate, ThemoviedbReviewDelegate {

    public static final String MOVIE = "movie";
    private Movie movie;

    @BindView(R.id.backdrop)
    ImageView backdrop;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ThemoviedbService themoviedbService;

    private ProgressDialog progress;

    @BindView(R.id.recycler_trailers)
    RecyclerView recyclerViewTrailers;

    @BindView(R.id.recycler_reviews)
    RecyclerView recyclerViewReview;


    @BindView(R.id.favorite)
    FloatingActionButton favorite;

    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        final GridLayoutManager mLayoutManager = new GridLayoutManager(this, 1);

        trailerAdapter = new TrailerAdapter();
        recyclerViewTrailers.setAdapter(trailerAdapter);

        recyclerViewTrailers.setLayoutManager(mLayoutManager);


        final GridLayoutManager mLayoutManagerR = new GridLayoutManager(this, 1);

        reviewAdapter = new ReviewAdapter();
        recyclerViewReview.setAdapter(reviewAdapter);

        recyclerViewReview.setLayoutManager(mLayoutManagerR);


        setTextIntoTexview(movie.original_title, R.id.title);
        setTextIntoTexview(movie.overview, R.id.synopsis);
        setTextIntoTexview(movie.vote_average, R.id.rating);
        setTextIntoTexview(DateUtil.userFriendlyDate(movie.release_date), R.id.release_date);

        loadTrailers(movie);

        loadReviews(movie);

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favoriteMovie(movie);
            }
        });

        favorite.setVisibility(Boolean.FALSE.equals(movie.isFavorited)?View.VISIBLE:View.GONE);

    }

    private void favoriteMovie(final Movie movie) {
        movie.isFavorited = true;
        SugarRecord.save(movie);
        final List<TrailerDetail> trailers = trailerAdapter.getTrailers();
        for (TrailerDetail detail: trailers) {
            detail.movieId = movie.id;
            SugarRecord.save(detail);
        }

        final List<ReviewDetail> reviews = reviewAdapter.getReviews();
        for (ReviewDetail detail: reviews) {
            detail.movieId = movie.id;
            SugarRecord.save(detail);
        }

        CharSequence text = getString(R.string.added);
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(this, text, duration);
        toast.show();

        //favorite.setVisibility(Boolean.FALSE.equals(movie.isFavorited)?View.VISIBLE:View.GONE);

    }


    private void loadTrailers(Movie movie) {

        themoviedbService.loadTrailers(movie, this);

    }

    private void loadReviews(Movie movie) {

        themoviedbService.loadReviews(movie, this);

    }

    public void setTextIntoTexview(final String text, Integer id) {
        final TextView textView = (TextView) findViewById(id);
        textView.setText(text);
    }


    @Override
    public void onPreExecute() {
        // progress = ProgressDialog.show(this, getString(R.string.load_title), getString(R.string.load_trailer), true);
    }

    @Override
    public void posExecute() {
        //progress.dismiss();
    }

    @Override
    public void add(TrailerDetail trailer) {

        final boolean trailer1 = trailer.isTrailer();
        if (trailer1) {
            trailerAdapter.addTrailer(trailer);
        }

    }

    @Override
    public void add(ReviewDetail detail) {

        reviewAdapter.addReview(detail);

    }
}

package com.samir.popularmovies.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.trailer.TrailerDetail;
import com.samir.popularmovies.service.ThemoviedbService;
import com.samir.popularmovies.service.ThemoviedbTrailerDelegate;
import com.samir.popularmovies.ui.adapter.TrailerAdapter;
import com.samir.popularmovies.util.DateUtil;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity implements ThemoviedbTrailerDelegate {

    public static final String MOVIE = "movie";
    private Movie movie;

    @BindView(R.id.backdrop)
    ImageView backdrop;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private ThemoviedbService themoviedbService;

    private ProgressDialog progress;

    @BindView(R.id.recycler_trailers)
    RecyclerView recyclerView;

    private TrailerAdapter trailerAdapter;

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
        recyclerView.setAdapter(trailerAdapter);

        recyclerView.setLayoutManager(mLayoutManager);

        setTextIntoTexview(movie.original_title, R.id.title);
        setTextIntoTexview(movie.overview, R.id.synopsis);
        setTextIntoTexview(movie.vote_average, R.id.rating);
        setTextIntoTexview(DateUtil.userFriendlyDate(movie.release_date), R.id.release_date);

        loadTrailers(movie);

    }

    private void loadTrailers(Movie movie) {

        themoviedbService.loadTrailers(movie, this);

    }

    public void setTextIntoTexview(final String text, Integer id) {
        final TextView textView = (TextView)findViewById(id);
        textView.setText(text);
    }


    @Override
    public void onPreExecute() {
        progress = ProgressDialog.show(this, getString(R.string.load_title), getString(R.string.load_trailer), true);
    }

    @Override
    public void posExecute() {
        progress.dismiss();
    }

    @Override
    public void add(TrailerDetail trailer) {

        final boolean trailer1 = trailer.isTrailer();
        if (trailer1){
            trailerAdapter.addTrailer(trailer);
        }

    }
}

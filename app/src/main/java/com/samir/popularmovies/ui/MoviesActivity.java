package com.samir.popularmovies.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.SettiringManager;
import com.samir.popularmovies.service.ThemoviedbMoviesDelegate;
import com.samir.popularmovies.service.ThemoviedbService;
import com.samir.popularmovies.ui.adapter.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.samir.popularmovies.service.SettiringManager.FAVORITE;

public class MoviesActivity extends AppCompatActivity implements ThemoviedbMoviesDelegate, MovieSelector {

    public static final String MOVIE_LIST = "MOVIE_LIST";
    public static final String COMMAND_STRING = "COMMAND_STRING";
    public static final int TABLET_SIZE = 600;
    public static final String SELECTED_MOVIE = "SELECTED_MOVIE";
    @BindView(R.id.id_thumbnail_layout)
    RecyclerView recyclerView;

    private MovieAdapter movieAdapter;
    private ThemoviedbService themoviedbService;
    private String commandString;

    private Movie selectedMovie;

    private boolean mTwoPane = false;

    public MoviesActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ButterKnife.bind(this);

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        final MovieSelector movieSelector = (MovieSelector) this;
        movieAdapter = new MovieAdapter(movieSelector);
        recyclerView.setAdapter(movieAdapter);

        final int spanCount = getSpanCount();

        final GridLayoutManager mLayoutManager = new GridLayoutManager(this, spanCount);

        recyclerView.setLayoutManager(mLayoutManager);

        themoviedbService = new ThemoviedbService();

        if (savedInstanceState == null) {
            loadMovies();
        }

    }

    private int getSpanCount() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int widthPixels = metrics.widthPixels;
        int heightPixels = metrics.heightPixels;

        float scaleFactor = metrics.density;


        float widthDp = widthPixels / scaleFactor;
        float heightDp = heightPixels / scaleFactor;

        final int spanCount = isTablet(widthDp, heightDp) ? 3 : 2;
        return spanCount;
    }

    private boolean isTablet(float widthDp, float heightDp) {
        return Math.min(widthDp, heightDp) >= TABLET_SIZE;
    }

    @Override
    protected void onResume() {
        super.onResume();

        String commandString = new SettiringManager().getCommandString();
        final boolean equals = commandString.equals(this.commandString);
        if (!equals || commandString.equals(FAVORITE)) {
            loadMovies();
        }
    }

    private void loadMovies() {
        this.commandString = new SettiringManager().getCommandString();
        themoviedbService.requestMovies(this);
    }

    @Override
    public void add(Movie movieDB) {
        movieAdapter.addMovie(movieDB);
    }

    @Override
    public void onPreExecute() {

//        progress = ProgressDialog.show(this, getString(R.string.load_title), getString(R.string.load_movie), true);

        movieAdapter.removeAll();
        recyclerView.removeAllViewsInLayout();
    }

    @Override
    public void posExecute() {
        movieAdapter.notifyDataSetChanged();
        //     progress.dismiss();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        final List<Movie> movies = movieAdapter.getMovies();

        savedInstanceState.putParcelableArrayList(MOVIE_LIST, (ArrayList<? extends Parcelable>) movies);
        savedInstanceState.putString(COMMAND_STRING, commandString);
        savedInstanceState.putParcelable(SELECTED_MOVIE, getSelectedMovie());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        commandString = savedInstanceState.getString(COMMAND_STRING);
        setSelectedMovie((Movie) savedInstanceState.getParcelable(SELECTED_MOVIE));
        final ArrayList<Parcelable> parcelableArrayList = savedInstanceState.getParcelableArrayList(MOVIE_LIST);
        for(Parcelable parc : parcelableArrayList) {
            movieAdapter.addMovie((Movie) parc);
        }
    }

    @Override
    public Movie getSelectedMovie() {
        return selectedMovie;
    }

    @Override
    public void setSelectedMovie(Movie selectedMovie) {
        this.selectedMovie = selectedMovie;
    }

    @Override
    public boolean ismTwoPane() {
        return mTwoPane;
    }
}

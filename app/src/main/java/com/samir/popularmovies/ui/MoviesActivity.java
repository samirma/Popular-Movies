package com.samir.popularmovies.ui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.SettiringManager;
import com.samir.popularmovies.service.ThemoviedbMoviesDelegate;
import com.samir.popularmovies.service.ThemoviedbService;
import com.samir.popularmovies.ui.adapter.MovieAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesActivity extends AppCompatActivity implements ThemoviedbMoviesDelegate {

    @BindView(R.id.id_thumbnail_layout)
    RecyclerView recyclerView;

    private MovieAdapter movieAdapter;
    private ThemoviedbService themoviedbService;
    private String commandString;

    private ProgressDialog progress;
    private boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        ButterKnife.bind(this);

        if (findViewById(R.id.item_detail_container) != null) {
            mTwoPane = true;
        }

        movieAdapter = new MovieAdapter(mTwoPane);
        recyclerView.setAdapter(movieAdapter);

        final GridLayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        recyclerView.setLayoutManager(mLayoutManager);

        themoviedbService = new ThemoviedbService();

        loadMovies();

    }

    @Override
    protected void onResume() {
        super.onResume();

        String commandString = new SettiringManager().getCommandString();
        if (!commandString.equals(this.commandString)) {
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

        progress = ProgressDialog.show(this, getString(R.string.load_title), getString(R.string.load_movie), true);

        movieAdapter.removeAll();
        recyclerView.removeAllViewsInLayout();
    }

    @Override
    public void posExecute() {
        movieAdapter.notifyDataSetChanged();
        progress.dismiss();
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

}

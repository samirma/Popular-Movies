package com.samir.popularmovies.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridLayout;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.ThemoviedbDelegate;
import com.samir.popularmovies.service.ThemoviedbService;

public class MoviesActivity extends AppCompatActivity implements ThemoviedbDelegate {

    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);

        final View viewById = findViewById(R.id.id_thumbnail_layout);
        gridLayout = (GridLayout) viewById;


        final ThemoviedbService themoviedbService = new ThemoviedbService();

        themoviedbService.requestMovies(this);

    }

    @Override
    public void add(Movie movieDB) {
        final View movieDBThumbnail = getMovieDBThumbnail(movieDB);
        gridLayout.addView(movieDBThumbnail);
    }

    @Override
    public void onPreExecute() {
        gridLayout.removeAllViewsInLayout();
    }

    private View getMovieDBThumbnail(final Movie movieDB) {

        final View inflate = getLayoutInflater().inflate(R.layout.movie, gridLayout, false);

        return inflate;

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

package com.samir.popularmovies.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    public Context context;

    public void addMovie(final Movie movieDB) {
        movies.add(movieDB);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);
            thumbnail = (ImageView)v.findViewById(R.id.thumbnail);
        }
    }

    public MovieAdapter() {
        this.movies = new ArrayList<>();
    }

    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                      int viewType) {
        // create a new view
        context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.movie, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Movie movie = movies.get(position);

        final String postUrl = getPostUrl(movie);

        Picasso.with(context)
                .load(postUrl)
                .config(Bitmap.Config.RGB_565)
                .into(holder.thumbnail);

    }

    private String getPostUrl(Movie movie) {
        final String format = String.format("%s%s%s", context.getString(R.string.server_img), context.getString(R.string.w185), movie.getPoster_path());
        return format;
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movies.size();
    }

}


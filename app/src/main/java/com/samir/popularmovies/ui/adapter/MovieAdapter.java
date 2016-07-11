package com.samir.popularmovies.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.ThemoviedbService;
import com.samir.popularmovies.ui.ItemDetailFragment;
import com.samir.popularmovies.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;
    public Context context;

    private boolean twopane;

    public void addMovie(final Movie movieDB) {
        movies.add(movieDB);
    }

    public void removeAll() {
        movies.clear();
    }

    public MovieAdapter(boolean twopane) {
        this.twopane = twopane;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView thumbnail;
        private final View view;
        private final Context context;
        public Movie movie;

        public ViewHolder(View v, Context context) {
            super(v);
            thumbnail = (ImageView)v.findViewById(R.id.thumbnail);
            this.view = v;
            this.context = context;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetail();
                }
            });

        }


        private void goToDetail() {

            if (true) {
                Bundle arguments = new Bundle();
                arguments.putString(ItemDetailFragment.ARG_ITEM_ID, holder.mItem.id);
                ItemDetailFragment fragment = new ItemDetailFragment();
                fragment.setArguments(arguments);
                context.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else  {

                final Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.MOVIE, movie);
                context.startActivity(intent);
            }
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

        ViewHolder vh = new ViewHolder(v, context);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final Movie movie = movies.get(position);

        final String postUrl = new ThemoviedbService().getThumbnail(movie);

        Picasso.with(context)
                .load(postUrl)
                .config(Bitmap.Config.RGB_565)
                .into(holder.thumbnail);

        holder.movie = movie;

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movies.size();
    }

}


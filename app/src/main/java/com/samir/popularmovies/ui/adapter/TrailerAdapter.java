package com.samir.popularmovies.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.trailer.TrailerDetail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private List<TrailerDetail> trailers;
    public Context context;

    public void addTrailer(final TrailerDetail movieDB) {
        trailers.add(movieDB);
    }

    public void removeAll() {
        trailers.clear();
    }

    public List<TrailerDetail> getTrailers() {
        return trailers;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final ImageView thumbnail;
        public final TextView title;
        private final Context context;
        public TrailerDetail trailerDetail;

        public ViewHolder(View v, Context context) {
            super(v);
            thumbnail = (ImageView)v.findViewById(R.id.thumbnail_trailer);

            title = (TextView)v.findViewById(R.id.thumbnail_trailer_name);

            this.context = context;

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    goToDetail();
                }
            });

        }


        private void goToDetail() {
            final Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(String.format(context.getString(R.string.youtube_url), trailerDetail.key)));
            context.startActivity(intent);
        }
    }

    public TrailerAdapter() {
        this.trailers = new ArrayList<>();
    }

    @Override
    public TrailerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.trailer, parent, false);

        ViewHolder vh = new ViewHolder(v, context);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final TrailerDetail trailerDetail = trailers.get(position);

        final String postUrl = String.format(context.getString(R.string.youtube_trailer_thumbnail_url), trailerDetail.key);

        Picasso.with(context)
                .load(postUrl)
                .into(holder.thumbnail);

        holder.trailerDetail = trailerDetail;

        holder.title.setText(trailerDetail.name);

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trailers.size();
    }

}


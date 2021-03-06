package com.samir.popularmovies.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.ReviewDetail;

import java.util.ArrayList;
import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    private List<ReviewDetail> reviews;
    public Context context;

    public void addReview(final ReviewDetail movieDB) {
        reviews.add(movieDB);
    }

    public void removeAll() {
        reviews.clear();
    }

    public List<ReviewDetail> getReviews() {
        return reviews;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView title;
        public final TextView author;
        public ReviewDetail detail;

        public ViewHolder(View v, Context context) {
            super(v);

            title = (TextView)v.findViewById(R.id.review);

            author = (TextView)v.findViewById(R.id.author);

        }


    }

    public ReviewAdapter() {
        this.reviews = new ArrayList<>();
    }

    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                       int viewType) {
        // create a new view
        context = parent.getContext();
        View v = LayoutInflater.from(context)
                .inflate(R.layout.review, parent, false);

        ViewHolder vh = new ViewHolder(v, context);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final ReviewDetail trailerDetail = reviews.get(position);

        holder.detail = trailerDetail;

        holder.title.setText(trailerDetail.content);

        holder.author.setText(trailerDetail.author);

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return reviews.size();
    }

}


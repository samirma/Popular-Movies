package com.samir.popularmovies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.Movie;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {


    public MovieAdapter(Context context, int resource, List<Movie> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Movie item = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie, parent, false);
        }

        return convertView;
    }
}

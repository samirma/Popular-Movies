package com.samir.popularmovies.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.samir.popularmovies.R;
import com.samir.popularmovies.model.MovieDB;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<MovieDB> {


    public MovieAdapter(Context context, int resource, List<MovieDB> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        MovieDB item = getItem(position);


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.movie, parent, false);
        }

        return convertView;
    }
}

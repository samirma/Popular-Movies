package com.samir.popularmovies;

import android.app.Application;
import android.content.Context;


public class AplicationPopularMovies extends Application {

    private static Context context = null;



    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();
    }

    public static Context getContext() {
        return context;
    }

}

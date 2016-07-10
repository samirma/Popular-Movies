package com.samir.popularmovies;

import android.content.Context;

import com.facebook.stetho.Stetho;


public class AplicationPopularMovies extends com.activeandroid.app.Application {

    private static Context context = null;



    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();

        Stetho.initializeWithDefaults(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public static Context getContext() {
        return context;
    }

}

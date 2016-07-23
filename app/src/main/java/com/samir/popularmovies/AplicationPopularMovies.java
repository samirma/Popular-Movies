package com.samir.popularmovies;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.facebook.stetho.Stetho;
import com.orm.SugarApp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class AplicationPopularMovies extends SugarApp {

    private static Context context = null;

    public final String Name = "popularMovie.db";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getBaseContext();

        try {
            copydatabase();
        } catch (IOException e) {
            Log.e(AplicationPopularMovies.class.getSimpleName(), e.getMessage(), e);
        }

        Stetho.initializeWithDefaults(this);

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    public void copydatabase() throws IOException {

        final File databasePath = context.getDatabasePath(Name);

        databasePath.getParentFile().mkdir();

        final boolean exists = databasePath.exists();
        if (!exists) {
            OutputStream myOutput = new FileOutputStream(databasePath);
            byte[] buffer = new byte[1024];
            int length;
            InputStream myInput = context.getAssets().open("popularMovie.db");
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myInput.close();
            myOutput.flush();
            myOutput.close();
        }
    }

    public static Context getContext() {
        return context;
    }

}

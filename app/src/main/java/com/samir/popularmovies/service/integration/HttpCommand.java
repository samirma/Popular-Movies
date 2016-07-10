package com.samir.popularmovies.service.integration;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.model.Page;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

/**
 * Created by samir on 7/10/16.
 */

public abstract class HttpCommand implements Command {

    public static final Gson GSON = new GsonBuilder()
            .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
            .serializeNulls()
            .create();


    protected String getHttpString() throws IOException {
        final HttpClient httpClient = new HttpClient();
        final MoviedbHttpRequest moviedbHttpRequest = new MoviedbHttpRequest(this);
        return httpClient.execute(moviedbHttpRequest);
    }
}

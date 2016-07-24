package com.samir.popularmovies.service;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.service.integration.MovieCommand;
import com.samir.popularmovies.service.integration.commands.MoviedbFavoritedCommand;
import com.samir.popularmovies.service.integration.commands.MoviedbPopularCommand;
import com.samir.popularmovies.service.integration.commands.MoviedbTopRatedCommand;

import java.util.HashMap;
import java.util.Map;

public class SettiringManager {

    final static SettiringManager settiringManager = new SettiringManager();

    final static Map<String, MovieCommand> commands = new HashMap<>();

    public static final String FAVORITE = "3";

    static {
        registerCommand("1", new MoviedbPopularCommand());
        registerCommand("2", new MoviedbTopRatedCommand());
        registerCommand(FAVORITE, new MoviedbFavoritedCommand());

    }


    public static SettiringManager getSettiringManager() {
        return settiringManager;
    }

    public MovieCommand getSelectedCommad() {

        final String string = getCommandString();

        final MovieCommand command = commands.get(string);
        return command;
    }

    @NonNull
    public String getCommandString() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AplicationPopularMovies.getContext());
        return prefs.getString(AplicationPopularMovies.getContext().getString(R.string.category), "1");
    }

    public static void registerCommand(String key, MovieCommand command) {
        commands.put(key, command);
    }
}

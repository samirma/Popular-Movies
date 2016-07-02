package com.samir.popularmovies.service;


import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.samir.popularmovies.AplicationPopularMovies;
import com.samir.popularmovies.R;
import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.MoviedbPopularCommand;
import com.samir.popularmovies.service.integration.MoviedbTopRatedCommand;

import java.util.HashMap;
import java.util.Map;

public class SettiringManager {

    final static SettiringManager settiringManager = new SettiringManager();

    final static Map<String, Command> commands = new HashMap<>();

    static {
        registerCommand("1", new MoviedbPopularCommand());
        registerCommand("2", new MoviedbTopRatedCommand());
    }


    public static SettiringManager getSettiringManager() {
        return settiringManager;
    }

    public Command getSelectedCommad() {

        final String string = getCommandString();

        final Command command = commands.get(string);
        return command;
    }

    @NonNull
    public String getCommandString() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(AplicationPopularMovies.getContext());
        return prefs.getString(AplicationPopularMovies.getContext().getString(R.string.category), "1");
    }

    public static void registerCommand(String key, Command command) {
        commands.put(key, command);
    }
}

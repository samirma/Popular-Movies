package com.samir.popularmovies.service;


import com.samir.popularmovies.service.integration.Command;
import com.samir.popularmovies.service.integration.MoviedbTopRatedCommand;

public class SettiringManager {

    final static SettiringManager settiringManager = new SettiringManager();


    public static SettiringManager getSettiringManager() {
        return settiringManager;
    }

    public Command getSelectedCommad() {
        return new MoviedbTopRatedCommand();
    }

}

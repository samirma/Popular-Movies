package com.samir.popularmovies;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.samir.popularmovies.model.MovieDB;
import com.samir.popularmovies.service.ThemoviedbDelegate;
import com.samir.popularmovies.service.ThemoviedbService;

import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@MediumTest
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentationTest {
    @Test
    public void useAppContext() throws Exception {

        final ThemoviedbService themoviedbService = new ThemoviedbService();

        themoviedbService.requestMovies(new ThemoviedbDelegate() {
            @Override
            public void add(MovieDB movieDB) {
                synchronized (themoviedbService) {
                    themoviedbService.notify();
                }
            }

            @Override
            public void onPreExecute() {
                synchronized (themoviedbService) {
                    themoviedbService.notify();
                }
            }
        });

        synchronized (themoviedbService) {
            themoviedbService.wait();
        }

    }
}
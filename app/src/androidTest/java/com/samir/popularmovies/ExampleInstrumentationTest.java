package com.samir.popularmovies;

import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.samir.popularmovies.model.Movie;
import com.samir.popularmovies.service.ThemoviedbMoviesDelegate;
import com.samir.popularmovies.service.ThemoviedbService;

import org.junit.Test;
import org.junit.runner.RunWith;

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

        themoviedbService.requestMovies(new ThemoviedbMoviesDelegate() {
            @Override
            public void add(Movie movieDB) {
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
package com.samir.popularmovies.ui;


import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.samir.popularmovies.R;

public class SettingsActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();


    }


}

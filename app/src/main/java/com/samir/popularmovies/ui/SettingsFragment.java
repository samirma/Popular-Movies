package com.samir.popularmovies.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.samir.popularmovies.R;

/**
 * Created by samir on 6/28/16.
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.pref_general);
    }


}


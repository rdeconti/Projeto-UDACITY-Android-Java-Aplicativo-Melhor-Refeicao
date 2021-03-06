package com.rosemeire.deconti.bestmeal.ApplicationSettings;

 /* ****************************************************************************
 /* Copyright (C) 2016 The Android Open Source Project
 /*
 /* Licensed under the Apache License, Version 2.0 (the "License");
 /* you may not use this file except in compliance with the License.
 /* You may obtain a copy of the License at
 /*
 /*     http://www.apache.org/licenses/LICENSE-2.0
 /*
 /* Unless required by applicable law or agreed to in writing, software
 /* distributed under the License is distributed on an "AS IS" BASIS,
 /* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 /* See the License for the specific language governing permissions and
 /* limitations under the License.
 /* ****************************************************************************
 /* UDACITY Android Developer NanoDegree Program
 /* Created by Rosemeire Deconti on 02/01/2019
 /* ****************************************************************************/

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import com.rosemeire.deconti.bestmeal.R;

import java.util.Objects;

public class ApplicationSettingsFragment extends PreferenceFragmentCompat implements
        OnSharedPreferenceChangeListener, Preference.OnPreferenceChangeListener {

    /* ****************************************************************************************** **
    /* **** Format screen with definitions done in XML file and shared preferences
    /* ****************************************************************************************** */
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {

        addPreferencesFromResource(R.xml.application_settings);

        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();

        PreferenceScreen prefScreen = getPreferenceScreen();

        int count = prefScreen.getPreferenceCount();

        for (int i = 0; i < count; i++) {

            Preference p = prefScreen.getPreference(i);

            if (!(p instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(p.getKey(), "");
                setPreferenceSummary(p, value);
            }
        }
    }

    /* ****************************************************************************************** **
    /* **** Figure out which preference was changed
    /* ****************************************************************************************** */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

        Preference preference = findPreference(key);

        if (null != preference) {

            if (!(preference instanceof CheckBoxPreference)) {
                String value = sharedPreferences.getString(preference.getKey(), "");
                setPreferenceSummary(preference, value);
                new ApplicationSettingUpdating(Objects.requireNonNull(getContext()), value);

            }
        }
    }

    /* ****************************************************************************************** **
    /* **** Updates the summary for the preference
    /* ****************************************************************************************** */
    private void setPreferenceSummary(Preference preference, String value) {

        new ApplicationSettingUpdating(Objects.requireNonNull(getContext()), value);

        if (preference instanceof ListPreference) {

            ListPreference listPreference = (ListPreference) preference;

            int prefIndex = listPreference.findIndexOfValue(value);

            if (prefIndex >= 0) {

                listPreference.setSummary(listPreference.getEntries()[prefIndex]);

            }
        } else if (preference instanceof EditTextPreference) {

            preference.setSummary(value);

            new ApplicationSettingUpdating(getContext(), value);

        }
    }

    /* ****************************************************************************************** **
    /* **** In this context, we're using the onPreferenceChange listener for checking whether the
    /* **** size setting was set to a valid value.
    /* ****************************************************************************************** */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        return true;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
}
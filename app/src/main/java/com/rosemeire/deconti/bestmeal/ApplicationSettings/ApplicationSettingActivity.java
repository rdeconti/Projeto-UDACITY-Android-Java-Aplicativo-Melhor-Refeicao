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
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSettingLocalization;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSharedPreferencesGet;
import com.rosemeire.deconti.bestmeal.R;

import java.util.Locale;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentLanguageIsoCode;

/* ********************************************************************************************** **
/* **** TREAT APPLICATION SETTINGS
/* ********************************************************************************************** */
public class ApplicationSettingActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    /* ****************************************************************************************** **
    /* **** Create screen
    /* ****************************************************************************************** */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.application_settings_activity);

        ActionBar actionBar = this.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // .................................................................. Get Shared Preferences
        new SupportSharedPreferencesGet(getApplicationContext());
        new SupportSettingLocalization();

        // ............................................................................ Set Language
        Locale locale = new Locale(sCurrentLanguageIsoCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

    }

    /* ****************************************************************************************** **
    /* **** Treat options
    /* ****************************************************************************************** */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == android.R.id.home) {

            NavUtils.navigateUpFromSameTask(this);

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
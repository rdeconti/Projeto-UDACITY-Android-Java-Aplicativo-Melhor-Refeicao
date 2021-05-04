package com.rosemeire.deconti.bestmeal.ApplicationSettings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSettingLocalization;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSharedPreferencesGet;
import com.rosemeire.deconti.bestmeal.R;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsLanguage;

/* *************************************************************************************************
/* UDACITY Android Developer NanoDegree Program
/* Created by Rosemeire Deconti on 01/10/2018
/* *************************************************************************************************
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
/* ************************************************************************************************/

@SuppressLint("Registered")
public class ApplicationSettingUpdating extends AppCompatActivity {

    /* ****************************************************************************************** **
    /* **** Update application settings
    /* ****************************************************************************************** */
    public ApplicationSettingUpdating(Context context, String value) {

        if (value.equals(context.getResources().getString(R.string.settings_language_value_english))) {
            sPreferredSettingsLanguage = value;
        }

        if (value.equals(context.getResources().getString(R.string.settings_language_value_spanish))) {
            sPreferredSettingsLanguage = value;
        }

        if (value.equals(context.getResources().getString(R.string.settings_language_value_portuguese))) {
            sPreferredSettingsLanguage = value;
        }

        // .................................................................. Get Shared Preferences
        new SupportSharedPreferencesGet(context);
        new SupportSettingLocalization();

    }
}

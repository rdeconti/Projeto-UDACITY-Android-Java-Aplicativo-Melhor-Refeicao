package com.rosemeire.deconti.bestmeal.ApplicationSupport;

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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.rosemeire.deconti.bestmeal.R;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsClassification;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsLanguage;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsSelectionDifficult;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsSelectionPrice;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsSelectionServings;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsSelectionTime;

/* ********************************************************************************************** **
/* **** Get preferred settings
/* ********************************************************************************************** */
@SuppressLint("Registered")
public class SupportSharedPreferencesGet extends AppCompatActivity {

    public SupportSharedPreferencesGet(Context context) {

        SharedPreferences sharedPreferences =
                android.support.v7.preference.PreferenceManager.getDefaultSharedPreferences(context);

        sPreferredSettingsClassification = sharedPreferences.getString(context.getString(R.string.settings_classification_key), context.getString(R.string.settings_classification_label));
        if (TextUtils.isEmpty(sPreferredSettingsClassification)) {
            sPreferredSettingsClassification = getString(R.string.settings_classification_label_newest);
        }

        sPreferredSettingsLanguage = sharedPreferences.getString(context.getString(R.string.settings_language_key), context.getString(R.string.settings_language_label));
        if (TextUtils.isEmpty(sPreferredSettingsLanguage)) {
            sPreferredSettingsLanguage = getString(R.string.settings_language_value_english);
        }

        sPreferredSettingsSelectionServings = sharedPreferences.getString(context.getString(R.string.settings_servings_key), context.getString(R.string.settings_servings_label));
        if (TextUtils.isEmpty(sPreferredSettingsSelectionServings)) {
            sPreferredSettingsSelectionServings = getString(R.string.settings_servings_label_low);
        }

        sPreferredSettingsSelectionPrice = sharedPreferences.getString(context.getString(R.string.settings_price_key), context.getString(R.string.settings_price_label));
        if (TextUtils.isEmpty(sPreferredSettingsSelectionPrice)) {
            sPreferredSettingsSelectionPrice = getString(R.string.settings_price_label_low);
        }

        sPreferredSettingsSelectionTime = sharedPreferences.getString(context.getString(R.string.settings_time_key), context.getString(R.string.settings_time_label));
        if (TextUtils.isEmpty(sPreferredSettingsSelectionTime)) {
            sPreferredSettingsSelectionTime = getString(R.string.settings_time_label_low);
        }

        sPreferredSettingsSelectionDifficult = sharedPreferences.getString(context.getString(R.string.settings_difficult_key), context.getString(R.string.settings_difficult_label));
        if (TextUtils.isEmpty(sPreferredSettingsSelectionDifficult)) {
            sPreferredSettingsSelectionDifficult = getString(R.string.settings_time_label_low);
        }
    }
}

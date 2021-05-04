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
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.LANGUAGE_ENGLISH;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.LANGUAGE_ENGLISH_ISO_CODE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.LANGUAGE_PORTUGUESE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.LANGUAGE_PORTUGUESE_ISO_CODE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.LANGUAGE_SPANISH;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.LANGUAGE_SPANISH_ISO_CODE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentLanguageIsoCode;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsLanguage;

/* ********************************************************************************************** **
/* **** Setting localization
/* ********************************************************************************************** */
@SuppressLint("Registered")
public class SupportSettingLocalization extends AppCompatActivity {

    public SupportSettingLocalization() {

        if (TextUtils.isEmpty(sPreferredSettingsLanguage)) {

            sPreferredSettingsLanguage = LANGUAGE_ENGLISH;

        }

        switch (sPreferredSettingsLanguage) {

            case LANGUAGE_ENGLISH:
                sCurrentLanguageIsoCode = LANGUAGE_ENGLISH_ISO_CODE;
                break;

            case LANGUAGE_SPANISH:
                sCurrentLanguageIsoCode = LANGUAGE_SPANISH_ISO_CODE;
                break;

            case LANGUAGE_PORTUGUESE:
                sCurrentLanguageIsoCode = LANGUAGE_PORTUGUESE_ISO_CODE;
                break;

            default:
                sCurrentLanguageIsoCode = LANGUAGE_ENGLISH_ISO_CODE;
                break;

        }
    }
}

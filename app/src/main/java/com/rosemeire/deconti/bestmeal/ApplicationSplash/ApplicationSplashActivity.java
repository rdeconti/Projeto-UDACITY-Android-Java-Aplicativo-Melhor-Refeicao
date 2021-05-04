package com.rosemeire.deconti.bestmeal.ApplicationSplash;

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

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.rosemeire.deconti.bestmeal.ApplicationAuthorization.AuthorizationLogInActivity;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSettingLocalization;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSharedPreferencesGet;
import com.rosemeire.deconti.bestmeal.R;

import java.util.Locale;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentLanguageIsoCode;

/* ********************************************************************************************** **
/* **** Treat Application Presentation Screen
/* ********************************************************************************************** */
public class ApplicationSplashActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // .................................................................. Get Shared Preferences
        new SupportSharedPreferencesGet(getApplicationContext());
        new SupportSettingLocalization();

        // ............................................................................ Set Language
        Locale locale = new Locale(sCurrentLanguageIsoCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // ............................................................... Initialize Layout Objects
        setContentView(R.layout.application_splash_activity);

        CountDownTimer mCountDownTimer = new CountDownTimer(2500, 200) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onFinish() {

                fadeOut();
                finish();

            }
        };

        mCountDownTimer.start();

    }

    private void fadeOut() {

        Intent mainIntent = new Intent(this, AuthorizationLogInActivity.class);
        startActivity(mainIntent);
        overridePendingTransition(R.anim.splash_in, R.anim.splash_out);

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void attachBaseContext(Context newBase) {

        super.attachBaseContext((newBase));

    }
}
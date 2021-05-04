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
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.rosemeire.deconti.bestmeal.R;

import java.util.Calendar;

/* ************************************************************************************************/
/* *** FORMAT LOG ERROR MESSAGE TO DATABASE ERROR
/* ************************************************************************************************/
@SuppressLint("Registered")
public class SupportHandlingDatabaseError extends AppCompatActivity {

    final Context context = getApplicationContext();

    public SupportHandlingDatabaseError
            (String stringActivity, DatabaseError databaseError) {

        Log.e(context.getString(R.string.label_best_meal_error),
                context.getString(R.string.message_error_date_time) + Calendar.getInstance());

        Log.e(context.getString(R.string.label_best_meal_error),
                context.getString(R.string.message_error_activity) + stringActivity);

        Log.e(context.getString(R.string.label_best_meal_error),
                context.getString(R.string.message_error_code) + databaseError.getCode());

        Log.e(context.getString(R.string.label_best_meal_error),
                context.getString(R.string.message_error_details) + databaseError.getDetails());

        Log.e(context.getString(R.string.label_best_meal_error),
                context.getString(R.string.message_error_message) + databaseError.getMessage());
    }
}

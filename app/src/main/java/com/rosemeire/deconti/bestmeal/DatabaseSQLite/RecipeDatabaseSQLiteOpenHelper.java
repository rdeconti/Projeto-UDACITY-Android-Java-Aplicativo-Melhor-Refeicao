package com.rosemeire.deconti.bestmeal.DatabaseSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CREATE_TABLE_PURCHASE_LIST;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CREATE_TABLE_PURCHASE_WIDGET;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CREATE_TABLE_RECIPE_COMMENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CREATE_TABLE_RECIPE_HEADER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CREATE_TABLE_RECIPE_INGREDIENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CREATE_TABLE_RECIPE_INSTRUCTIONS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CREATE_TABLE_RECIPE_NUTRITIONAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CREATE_TABLE_RECIPE_TIPS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_BASE_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_BASE_VERSION;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DROP_TABLE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_COMMENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_HEADER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_INGREDIENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_INSTRUCTIONS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_NUTRITIONAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_PURCHASE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_PURCHASE_WIDGET;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_TIPS;

/*****************************************************************************
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
 /*****************************************************************************
 /* UDACITY Android Developer NanoDegree Program
 /* Created by Rosemeire Deconti on 19/02/2019
 /*****************************************************************************/
public class RecipeDatabaseSQLiteOpenHelper extends SQLiteOpenHelper {

    /* ********************************************************************************************/
    /* *** Start SQLite Structure
    /* ********************************************************************************************/
    public RecipeDatabaseSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version) {
        super(context, DATA_BASE_NAME, cursorFactory, DATA_BASE_VERSION);

    }

    /* ********************************************************************************************/
    /* *** Create SQLite Structure
    /* ********************************************************************************************/
    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_TABLE_RECIPE_HEADER);
        database.execSQL(CREATE_TABLE_RECIPE_INGREDIENTS);
        database.execSQL(CREATE_TABLE_RECIPE_INSTRUCTIONS);
        database.execSQL(CREATE_TABLE_RECIPE_NUTRITIONAL);
        database.execSQL(CREATE_TABLE_RECIPE_TIPS);
        database.execSQL(CREATE_TABLE_RECIPE_COMMENTS);
        database.execSQL(CREATE_TABLE_PURCHASE_LIST);
        database.execSQL(CREATE_TABLE_PURCHASE_WIDGET);

    }

    /* ********************************************************************************************/
    /* *** Upgrade SQLite Structure
    /* ********************************************************************************************/
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL(DROP_TABLE + TABLE_RECIPE_HEADER);
        database.execSQL(DROP_TABLE + TABLE_RECIPE_INGREDIENTS);
        database.execSQL(DROP_TABLE + TABLE_RECIPE_INSTRUCTIONS);
        database.execSQL(DROP_TABLE + TABLE_RECIPE_NUTRITIONAL);
        database.execSQL(DROP_TABLE + TABLE_RECIPE_TIPS);
        database.execSQL(DROP_TABLE + TABLE_RECIPE_COMMENTS);
        database.execSQL(DROP_TABLE + TABLE_RECIPE_PURCHASE);
        database.execSQL(DROP_TABLE + TABLE_RECIPE_PURCHASE_WIDGET);

        onCreate(database);
    }
}

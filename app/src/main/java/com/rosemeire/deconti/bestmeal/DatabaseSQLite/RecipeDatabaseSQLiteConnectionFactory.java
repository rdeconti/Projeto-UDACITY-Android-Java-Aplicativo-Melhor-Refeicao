package com.rosemeire.deconti.bestmeal.DatabaseSQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_BASE_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_BASE_VERSION;

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
public final class RecipeDatabaseSQLiteConnectionFactory {

    private final RecipeDatabaseSQLiteOpenHelper dataHelper;

    public RecipeDatabaseSQLiteConnectionFactory(Context context) {
        this.dataHelper = new RecipeDatabaseSQLiteOpenHelper(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);
    }

    public SQLiteDatabase databaseConnectionWritable() throws SQLiteException {
        return dataHelper.getWritableDatabase();
    }

    public SQLiteDatabase databaseConnectionReadable() throws SQLiteException {
        return dataHelper.getReadableDatabase();
    }
}
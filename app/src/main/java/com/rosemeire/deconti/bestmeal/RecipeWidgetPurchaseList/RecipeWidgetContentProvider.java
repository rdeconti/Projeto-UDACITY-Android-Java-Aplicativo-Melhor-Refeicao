package com.rosemeire.deconti.bestmeal.RecipeWidgetPurchaseList;

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

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteOpenHelper;

import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_BASE_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_BASE_VERSION;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_PURCHASE_WIDGET;

/* ************************************************************************************************/
/* *** TREAT CONTENT PROVIDER
/* ************************************************************************************************/
public class RecipeWidgetContentProvider extends ContentProvider {

    private RecipeDatabaseSQLiteOpenHelper recipeDatabaseSQLiteOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final int WIDGET_PURCHASE_CODE = 100;
    public static final UriMatcher URI_MATCHER = buildUriMatcher();

    public Cursor cursor;
    public Uri returnUri;

    /* ********************************************************************************************/
    /* *** TREAT BUILD URI MATCHER
    /* ********************************************************************************************/
    public static UriMatcher buildUriMatcher() {

        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(RecipeWidgetContract.WIDGET_AUTHORITY, RecipeWidgetContract.WIDGET_PATH_PURCHASE, WIDGET_PURCHASE_CODE);

        return uriMatcher;
    }

    /* ********************************************************************************************/
    /* *** TREAT ON CREATE
    /* ********************************************************************************************/
    @Override
    public boolean onCreate() {

        Context context = getContext();

        recipeDatabaseSQLiteOpenHelper = new RecipeDatabaseSQLiteOpenHelper(context, DATA_BASE_NAME, null, DATA_BASE_VERSION);

        return true;
    }

    /* ********************************************************************************************/
    /* *** TREAT CURSOR QUERY
    /* ********************************************************************************************/
    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        sqLiteDatabase = recipeDatabaseSQLiteOpenHelper.getReadableDatabase();

        int match = URI_MATCHER.match(uri);

        cursor = null;

        switch (match) {

            case WIDGET_PURCHASE_CODE:

                cursor = sqLiteDatabase.query(TABLE_RECIPE_PURCHASE_WIDGET,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;

            default:

                break;
        }

        Objects.requireNonNull(cursor).setNotificationUri(Objects.requireNonNull(getContext()).getContentResolver(), uri);

        return cursor;
    }

    /* ********************************************************************************************/
    /* *** TREAT GET TYPE
    /* ********************************************************************************************/
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {

        return null;

    }

    /* ********************************************************************************************/
    /* *** TREAT INSERT
    /* ********************************************************************************************/
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values) {

        sqLiteDatabase = recipeDatabaseSQLiteOpenHelper.getWritableDatabase();

        int match = URI_MATCHER.match(uri);

        returnUri = null;

        switch (match) {

            case WIDGET_PURCHASE_CODE:

                long id = sqLiteDatabase.insert(TABLE_RECIPE_PURCHASE_WIDGET, null, values);

                if(id > 0) {

                    returnUri = ContentUris.withAppendedId(RecipeWidgetContract.WIDGET_PATH_PURCHASE_URI, id);

                }

                break;

            default:

                break;
        }

        Objects.requireNonNull(getContext()).getContentResolver().notifyChange(uri, null);

        return returnUri;
    }

    /* ********************************************************************************************/
    /* *** TREAT DELETE
    /* ********************************************************************************************/
    @Override
    public int delete(@NonNull Uri uri, String selection, String[] selectionArgs) {

        return 0;

    }

    /* ********************************************************************************************/
    /* *** TREAT UPDATE
    /* ********************************************************************************************/
    @Override
    public int update(@NonNull Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        return 0;

    }
}

package com.rosemeire.deconti.bestmeal.DatabaseSQLite;

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

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingExceptionError;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeHeaderModel;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeStatisticsModel;
import com.rosemeire.deconti.bestmeal.R;

import java.util.ArrayList;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_ASTERISK;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_EQUAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_FROM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_QUOTES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_SELECT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_WHERE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_AUTHOR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_CALORIES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_CUISENES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_DIET;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_DIFFICULT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_HEALTH;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_ID;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_LANGUAGE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_APPROVALS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_COMMENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_DISLIKES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_LIKES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_PRINTINGS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_REPROVALS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_SHARINGS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_OCCASION;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_PHOTO;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_PRICE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_RATING_DISPLAY;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_RATING_VALUE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_SERVINGS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS_APPROVED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS_COMMENTED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS_DISLIKED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS_LIKED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS_PRINTED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS_REPROVED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS_SHARED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS_VISUALIZED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_TIME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_UPDATE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_HEADER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_STATUS_YES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusApproved;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusCommented;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusDisliked;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusLiked;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusPrinted;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusReproved;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusShared;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusVisualized;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromSQLite;

/* ************************************************************************************************/
/* *** Treat SQLite Maintenance: RECIPE HEADER
/* ************************************************************************************************/
public class RecipeDatabaseSQLiteTableHeader {

    private static SQLiteDatabase database;

    /* ********************************************************************************************/
    /* *** Start SQLite connection
    /* ********************************************************************************************/
    public RecipeDatabaseSQLiteTableHeader(RecipeDatabaseSQLiteConnectionFactory connectionFactory) {
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeDatabaseSQLiteConnectionFactory connectionFactory1 = connectionFactory;
    }

    /* ********************************************************************************************/
    /* *** Insert register SQLite table
    /* ********************************************************************************************/
    public static void RecipeHeaderSQLiteInsert(
            Context context,
            String recipe_header_number,
            String recipe_header_name,
            String recipe_header_author,
            String recipe_header_update,
            String recipe_header_photo,
            String recipe_header_status_approved,
            String recipe_header_status_reproved,
            String recipe_header_status_liked,
            String recipe_header_status_disliked,
            String recipe_header_status_commented,
            String recipe_header_status_printed,
            String recipe_header_status_shared,
            String recipe_header_status_visualized,
            String recipe_header_language,
            String recipe_header_likes,
            String recipe_header_dislikes,
            String recipe_header_approvals,
            String recipe_header_reprovals,
            String recipe_header_visualizations,
            String recipe_header_printings,
            String recipe_header_comments,
            String recipe_header_sharings,
            String recipe_header_rating_value,
            String recipe_header_rating_display,
            String recipe_header_diet,
            String recipe_header_calories,
            String recipe_header_cuisenes,
            String recipe_header_health,
            String recipe_header_occasion,
            String recipe_header_servings,
            String recipe_header_price,
            String recipe_header_time,
            String recipe_header_difficult) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_HEADER_NUMBER, recipe_header_number);
            values.put(FIELD_RECIPE_HEADER_NAME, recipe_header_name);
            values.put(FIELD_RECIPE_HEADER_AUTHOR, recipe_header_author);
            values.put(FIELD_RECIPE_HEADER_UPDATE, recipe_header_update);
            values.put(FIELD_RECIPE_HEADER_PHOTO, recipe_header_photo);
            values.put(FIELD_RECIPE_HEADER_STATUS_APPROVED, recipe_header_status_approved);
            values.put(FIELD_RECIPE_HEADER_STATUS_REPROVED, recipe_header_status_reproved);
            values.put(FIELD_RECIPE_HEADER_STATUS_LIKED, recipe_header_status_liked);
            values.put(FIELD_RECIPE_HEADER_STATUS_DISLIKED, recipe_header_status_disliked);
            values.put(FIELD_RECIPE_HEADER_STATUS_COMMENTED, recipe_header_status_commented);
            values.put(FIELD_RECIPE_HEADER_STATUS_PRINTED, recipe_header_status_printed);
            values.put(FIELD_RECIPE_HEADER_STATUS_SHARED, recipe_header_status_shared);
            values.put(FIELD_RECIPE_HEADER_STATUS_VISUALIZED, recipe_header_status_visualized);
            values.put(FIELD_RECIPE_HEADER_LANGUAGE, recipe_header_language);
            values.put(FIELD_RECIPE_HEADER_NUMBER_LIKES, recipe_header_likes);
            values.put(FIELD_RECIPE_HEADER_NUMBER_DISLIKES, recipe_header_dislikes);
            values.put(FIELD_RECIPE_HEADER_NUMBER_APPROVALS, recipe_header_approvals);
            values.put(FIELD_RECIPE_HEADER_NUMBER_REPROVALS, recipe_header_reprovals);
            values.put(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS, recipe_header_visualizations);
            values.put(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS, recipe_header_printings);
            values.put(FIELD_RECIPE_HEADER_NUMBER_COMMENTS, recipe_header_comments);
            values.put(FIELD_RECIPE_HEADER_NUMBER_SHARINGS, recipe_header_sharings);
            values.put(FIELD_RECIPE_HEADER_RATING_VALUE, recipe_header_rating_value);
            values.put(FIELD_RECIPE_HEADER_RATING_DISPLAY, recipe_header_rating_display);
            values.put(FIELD_RECIPE_HEADER_DIET, recipe_header_diet);
            values.put(FIELD_RECIPE_HEADER_CALORIES, recipe_header_calories);
            values.put(FIELD_RECIPE_HEADER_CUISENES, recipe_header_cuisenes);
            values.put(FIELD_RECIPE_HEADER_HEALTH, recipe_header_health);
            values.put(FIELD_RECIPE_HEADER_OCCASION, recipe_header_occasion);
            values.put(FIELD_RECIPE_HEADER_SERVINGS, recipe_header_servings);
            values.put(FIELD_RECIPE_HEADER_PRICE, recipe_header_price);
            values.put(FIELD_RECIPE_HEADER_TIME, recipe_header_time);
            values.put(FIELD_RECIPE_HEADER_DIFFICULT, recipe_header_difficult);

            database.insert(TABLE_RECIPE_HEADER, null, values);
            database.setTransactionSuccessful();

        } catch (SQLException sqlerror) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, sqlerror, context);

        } finally {

            database.endTransaction();
        }

        database.close();
    }

    /* ********************************************************************************************/
    /* *** Update register SQLite table
    /* ********************************************************************************************/
    public static void RecipeHeaderSQLiteUpdate(
            Context context,
            String recipe_header_name,
            String recipe_header_update,
            String recipe_header_photo,
            String recipe_header_diet,
            String recipe_header_calories,
            String recipe_header_cuisenes,
            String recipe_header_health,
            String recipe_header_occasion,
            String recipe_header_servings,
            String recipe_header_price,
            String recipe_header_time,
            String recipe_header_difficult) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_HEADER_NAME, recipe_header_name);
            values.put(FIELD_RECIPE_HEADER_UPDATE, recipe_header_update);
            values.put(FIELD_RECIPE_HEADER_PHOTO, recipe_header_photo);
            values.put(FIELD_RECIPE_HEADER_DIET, recipe_header_diet);
            values.put(FIELD_RECIPE_HEADER_CALORIES, recipe_header_calories);
            values.put(FIELD_RECIPE_HEADER_CUISENES, recipe_header_cuisenes);
            values.put(FIELD_RECIPE_HEADER_HEALTH, recipe_header_health);
            values.put(FIELD_RECIPE_HEADER_OCCASION, recipe_header_occasion);
            values.put(FIELD_RECIPE_HEADER_SERVINGS, recipe_header_servings);
            values.put(FIELD_RECIPE_HEADER_PRICE, recipe_header_price);
            values.put(FIELD_RECIPE_HEADER_TIME, recipe_header_time);
            values.put(FIELD_RECIPE_HEADER_DIFFICULT, recipe_header_difficult);

            database.update(TABLE_RECIPE_HEADER, values, FIELD_RECIPE_HEADER_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});
            database.setTransactionSuccessful();

        } catch (SQLException sqlerror) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, sqlerror, context);

        } finally {

            database.endTransaction();
        }

        database.close();
    }

    /* ********************************************************************************************/
    /* *** Update register SQLite table
    /* ********************************************************************************************/
    public static void RecipeHeaderSQLiteUpdateStatus(Context context) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_HEADER_STATUS_APPROVED, sCurrentRecipeStatusApproved);
            values.put(FIELD_RECIPE_HEADER_STATUS_REPROVED, sCurrentRecipeStatusReproved);
            values.put(FIELD_RECIPE_HEADER_STATUS_LIKED, sCurrentRecipeStatusLiked);
            values.put(FIELD_RECIPE_HEADER_STATUS_DISLIKED, sCurrentRecipeStatusDisliked);
            values.put(FIELD_RECIPE_HEADER_STATUS_PRINTED, sCurrentRecipeStatusPrinted);
            values.put(FIELD_RECIPE_HEADER_STATUS_SHARED, sCurrentRecipeStatusShared);
            values.put(FIELD_RECIPE_HEADER_STATUS_COMMENTED, sCurrentRecipeStatusCommented);
            values.put(FIELD_RECIPE_HEADER_STATUS_VISUALIZED, sCurrentRecipeStatusVisualized);

            database.update(TABLE_RECIPE_HEADER, values, FIELD_RECIPE_HEADER_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});
            database.setTransactionSuccessful();

        } catch (SQLException sqlerror) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, sqlerror, context);

        } finally {

            database.endTransaction();
        }

        database.close();
    }

    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipeHeaderSQLiteDeleteAll(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_NUMBER + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_HEADER_ID));
                database.delete(TABLE_RECIPE_HEADER, FIELD_RECIPE_HEADER_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetHeader(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_NUMBER + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }


    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeStatisticsModel> RecipeHeaderGetStatistics(Context context) {

        Cursor cursor;
        List<RecipeStatisticsModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_NUMBER + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeStatisticsModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetListApproved(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_STATUS_APPROVED + COMMAND_EQUAL + COMMAND_QUOTES + RECIPE_TREAT_STATUS_YES + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetListBook(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_AUTHOR + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentUserFirebaseUid + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetListDisliked(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_STATUS_DISLIKED + COMMAND_EQUAL + COMMAND_QUOTES + RECIPE_TREAT_STATUS_YES + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetListLiked(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_STATUS_LIKED + COMMAND_EQUAL + COMMAND_QUOTES + RECIPE_TREAT_STATUS_YES + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetListReproved(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_STATUS_REPROVED + COMMAND_EQUAL + COMMAND_QUOTES + RECIPE_TREAT_STATUS_YES + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetListPrinted(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_STATUS_PRINTED + COMMAND_EQUAL + COMMAND_QUOTES + RECIPE_TREAT_STATUS_YES + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetListShared(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_STATUS_SHARED + COMMAND_EQUAL + COMMAND_QUOTES + RECIPE_TREAT_STATUS_YES + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderGetListVisualized(Context context) {

        Cursor cursor;
        List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_STATUS_VISUALIZED + COMMAND_EQUAL + COMMAND_QUOTES + RECIPE_TREAT_STATUS_YES + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeHeaderModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_AUTHOR)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_UPDATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PHOTO)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_LANGUAGE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_LIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_DISLIKES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_APPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_REPROVALS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_COMMENTS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_NUMBER_SHARINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_VALUE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_RATING_DISPLAY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIET)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CALORIES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_CUISENES)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_HEALTH)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_OCCASION)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_SERVINGS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_PRICE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_TIME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_DIFFICULT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }

    /* ********************************************************************************************/
    /* *** Check if recipe was saved on local storage non dependency of status
    /* ********************************************************************************************/
    public static void RecipeHeaderCheckLocalStorageByNumber(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_NUMBER + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        sRecipeKeyFromSQLite = 0;

        if (cursor.moveToFirst()) {

            do {

                sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_HEADER_ID));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Check if recipe was saved on local storage non dependency of status
    /* ********************************************************************************************/
    public static ArrayList<String> RecipeHeaderGetRecipeStatus(Context context) {

        final ArrayList recipeStatuses = new ArrayList ();

        recipeStatuses.clear();

        String mCurrentRecipeStatusApproved = context.getString(R.string.label_recipe_status_no);
        String mCurrentRecipeStatusReproved = context.getString(R.string.label_recipe_status_no);
        String mCurrentRecipeStatusLiked = context.getString(R.string.label_recipe_status_no);
        String mCurrentRecipeStatusDisliked = context.getString(R.string.label_recipe_status_no);
        String mCurrentRecipeStatusPrinted = context.getString(R.string.label_recipe_status_no);
        String mCurrentRecipeStatusShared = context.getString(R.string.label_recipe_status_no);
        String mCurrentRecipeStatusCommented = context.getString(R.string.label_recipe_status_no);
        String mCurrentRecipeStatusVisualized = context.getString(R.string.label_recipe_status_no);

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_HEADER + COMMAND_WHERE
                + FIELD_RECIPE_HEADER_NUMBER + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                mCurrentRecipeStatusApproved = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_APPROVED));
                mCurrentRecipeStatusReproved = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_REPROVED));
                mCurrentRecipeStatusLiked = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_LIKED));
                mCurrentRecipeStatusDisliked = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_DISLIKED));
                mCurrentRecipeStatusPrinted = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_PRINTED));
                mCurrentRecipeStatusShared = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_SHARED));
                mCurrentRecipeStatusCommented = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_COMMENTED));
                mCurrentRecipeStatusVisualized = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_HEADER_STATUS_VISUALIZED));

            } while (cursor.moveToNext());
        }

        recipeStatuses.add(0, mCurrentRecipeStatusApproved);
        recipeStatuses.add(1, mCurrentRecipeStatusReproved);
        recipeStatuses.add(2, mCurrentRecipeStatusLiked);
        recipeStatuses.add(3, mCurrentRecipeStatusDisliked);
        recipeStatuses.add(4, mCurrentRecipeStatusPrinted);
        recipeStatuses.add(5, mCurrentRecipeStatusShared);
        recipeStatuses.add(6, mCurrentRecipeStatusCommented);
        recipeStatuses.add(7, mCurrentRecipeStatusVisualized);

        cursor.close();
        database.close();

        return recipeStatuses;

    }
}
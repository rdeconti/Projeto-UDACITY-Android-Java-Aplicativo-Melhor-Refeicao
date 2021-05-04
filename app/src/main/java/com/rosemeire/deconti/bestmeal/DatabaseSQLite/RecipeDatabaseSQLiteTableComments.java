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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeCommentsModel;

import java.util.ArrayList;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_ASTERISK;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_EQUAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_FROM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_QUOTES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_SELECT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_WHERE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_ID;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_TEXT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_USER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_COMMENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCommentNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromSQLite;

/* ************************************************************************************************/
/* *** Treat SQLite Maintenance: RECIPE COMMENTS
/* ************************************************************************************************/
public class RecipeDatabaseSQLiteTableComments {

    private static SQLiteDatabase database;

    /* ********************************************************************************************/
    /* *** Start SQLite connection
    /* ********************************************************************************************/
    public RecipeDatabaseSQLiteTableComments(RecipeDatabaseSQLiteConnectionFactory connectionFactory) {
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeDatabaseSQLiteConnectionFactory connectionFactory1 = connectionFactory;
    }

    /* ********************************************************************************************/
    /* *** Insert register SQLite table
    /* ********************************************************************************************/
    public static void RecipeCommentsSQLiteInsert(
            Context context,
            String recipe_comments_recipe,
            String recipe_comments_number,
            String recipe_comments_user,
            String recipe_comments_text) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_COMMENTS_RECIPE, recipe_comments_recipe);
            values.put(FIELD_RECIPE_COMMENTS_NUMBER, recipe_comments_number);
            values.put(FIELD_RECIPE_COMMENTS_USER, recipe_comments_user);
            values.put(FIELD_RECIPE_COMMENTS_TEXT, recipe_comments_text);

            database.insert(TABLE_RECIPE_COMMENTS, null, values);
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
    public static void RecipeCommentsSQLiteUpdate(
            Context context,
            int key,
            String recipe_comments_recipe,
            String recipe_comments_number,
            String recipe_comments_user,
            String recipe_comments_text) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_COMMENTS_RECIPE, recipe_comments_recipe);
            values.put(FIELD_RECIPE_COMMENTS_NUMBER, recipe_comments_number);
            values.put(FIELD_RECIPE_COMMENTS_USER, recipe_comments_user);
            values.put(FIELD_RECIPE_COMMENTS_TEXT, recipe_comments_text);

            database.update(TABLE_RECIPE_COMMENTS, values, key + " = ?", new String[]{String.valueOf(key)});
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
    public static void RecipeCommentsSQLiteUpdateSingle(
            String recipe_comments_text,
            Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_COMMENTS + COMMAND_WHERE
                + FIELD_RECIPE_COMMENTS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                String number = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_NUMBER));

                if (sCurrentRecipeCommentNumber.equals(number)) {

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(FIELD_RECIPE_COMMENTS_TEXT, recipe_comments_text);

                    sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_ID));
                    database.update(TABLE_RECIPE_COMMENTS, contentValues, FIELD_RECIPE_COMMENTS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipeCommentsSQLiteDeleteSingle(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_COMMENTS + COMMAND_WHERE
                + FIELD_RECIPE_COMMENTS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                String number = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_NUMBER));

                if (sCurrentRecipeCommentNumber.equals(number)) {

                    sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_ID));
                    database.delete(TABLE_RECIPE_COMMENTS, FIELD_RECIPE_COMMENTS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipeCommentsSQLiteDeleteAll(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_COMMENTS + COMMAND_WHERE
                + FIELD_RECIPE_COMMENTS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_ID));
                database.delete(TABLE_RECIPE_COMMENTS, FIELD_RECIPE_COMMENTS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeCommentsModel> RecipeCommentsGetList(Context context) {

        Cursor cursor;
        List<RecipeCommentsModel> list = new ArrayList<>();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_COMMENTS + COMMAND_WHERE
                + FIELD_RECIPE_COMMENTS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeCommentsModel(

                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_RECIPE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_USER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_COMMENTS_TEXT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }
}
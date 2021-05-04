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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeInstructionsModel;

import java.util.ArrayList;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_ASTERISK;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_EQUAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_FROM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_QUOTES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_SELECT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_WHERE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_ID;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_PHOTO;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_TEXT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_INSTRUCTIONS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromSQLite;

/* ************************************************************************************************/
/* *** Treat SQLite Maintenance: RECIPE INSTRUCTIONS
/* ************************************************************************************************/
public class RecipeDatabaseSQLiteTableInstructions {

    private static SQLiteDatabase database;

    /* ********************************************************************************************/
    /* *** Start SQLite connection
    /* ********************************************************************************************/
    public RecipeDatabaseSQLiteTableInstructions(RecipeDatabaseSQLiteConnectionFactory connectionFactory) {
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeDatabaseSQLiteConnectionFactory connectionFactory1 = connectionFactory;
    }

    /* ********************************************************************************************/
    /* *** Insert register SQLite table
    /* ********************************************************************************************/
    public static void RecipeInstructionsSQLiteInsert(
            Context context,
            String recipe_instructions_recipe,
            String recipe_instructions_number,
            String recipe_instructions_text,
            String recipe_instructions_photo) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_INSTRUCTIONS_RECIPE, recipe_instructions_recipe);
            values.put(FIELD_RECIPE_INSTRUCTIONS_NUMBER, recipe_instructions_number);
            values.put(FIELD_RECIPE_INSTRUCTIONS_TEXT, recipe_instructions_text);
            values.put(FIELD_RECIPE_INSTRUCTIONS_PHOTO, recipe_instructions_photo);

            database.insert(TABLE_RECIPE_INSTRUCTIONS, null, values);
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
    public static void RecipeInstructionsSQLiteUpdate(
            Context context,
            int key,
            String recipe_instructions_recipe,
            String recipe_instructions_number,
            String recipe_instructions_text,
            String recipe_instructions_photo) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_INSTRUCTIONS_RECIPE, recipe_instructions_recipe);
            values.put(FIELD_RECIPE_INSTRUCTIONS_NUMBER, recipe_instructions_number);
            values.put(FIELD_RECIPE_INSTRUCTIONS_TEXT, recipe_instructions_text);
            values.put(FIELD_RECIPE_INSTRUCTIONS_PHOTO, recipe_instructions_photo);

            database.update(TABLE_RECIPE_INSTRUCTIONS, values, key + " = ?", new String[]{String.valueOf(key)});
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
    public static void RecipeInstructionsSQLiteUpdateSingle(
            String recipe_instructions_text,
            Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_INSTRUCTIONS + COMMAND_WHERE
                + FIELD_RECIPE_INSTRUCTIONS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                String number = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_NUMBER));

                if (sCurrentRecipeInstructionNumber.equals(number)) {

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(FIELD_RECIPE_INSTRUCTIONS_TEXT, recipe_instructions_text);

                    sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_ID));
                    database.update(TABLE_RECIPE_INSTRUCTIONS, contentValues, FIELD_RECIPE_INSTRUCTIONS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }
    
    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipeInstructionsSQLiteDeleteSingle(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_INSTRUCTIONS + COMMAND_WHERE
                + FIELD_RECIPE_INSTRUCTIONS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                String number = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_NUMBER));

                if (sCurrentRecipeInstructionNumber.equals(number)) {

                    sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_ID));
                    database.delete(TABLE_RECIPE_INSTRUCTIONS, FIELD_RECIPE_INSTRUCTIONS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipeInstructionsSQLiteDeleteAll(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_INSTRUCTIONS + COMMAND_WHERE
                + FIELD_RECIPE_INSTRUCTIONS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_ID));
                database.delete(TABLE_RECIPE_INSTRUCTIONS, FIELD_RECIPE_INSTRUCTIONS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeInstructionsModel> RecipeInstructionsGetList(Context context) {

        Cursor cursor;
        List<RecipeInstructionsModel> list = new ArrayList<>();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_INSTRUCTIONS + COMMAND_WHERE
                + FIELD_RECIPE_INSTRUCTIONS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeInstructionsModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_RECIPE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_TEXT)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INSTRUCTIONS_PHOTO))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }
}
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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeIngredientsModel;

import java.util.ArrayList;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_ASCENDING;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_ASTERISK;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_EQUAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_FROM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_ORDER_BY;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_QUOTES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_SELECT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_WHERE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_AMOUNT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_ID;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_PHOTO;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_UNIT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_INGREDIENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCommentNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIngredientNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromSQLite;

/* ************************************************************************************************/
/* *** Treat SQLite Maintenance: RECIPE INGREDIENTS
/* ************************************************************************************************/
public class RecipeDatabaseSQLiteTableIngredients {

    private static SQLiteDatabase database;

    /* ********************************************************************************************/
    /* *** Start SQLite connection
    /* ********************************************************************************************/
    public RecipeDatabaseSQLiteTableIngredients(RecipeDatabaseSQLiteConnectionFactory connectionFactory) {
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeDatabaseSQLiteConnectionFactory connectionFactory1 = connectionFactory;
    }

    /* ********************************************************************************************/
    /* *** Insert register SQLite table
    /* ********************************************************************************************/
    public static void RecipeIngredientsSQLiteInsert(
            String recipe_ingredients_recipe,
            String recipe_ingredients_number,
            String recipe_ingredients_amount,
            String recipe_ingredients_unit,
            String recipe_ingredients_name,
            String recipe_ingredients_photo,
            Context context) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_INGREDIENTS_RECIPE, recipe_ingredients_recipe);
            values.put(FIELD_RECIPE_INGREDIENTS_NUMBER, recipe_ingredients_number);
            values.put(FIELD_RECIPE_INGREDIENTS_AMOUNT, recipe_ingredients_amount);
            values.put(FIELD_RECIPE_INGREDIENTS_UNIT, recipe_ingredients_unit);
            values.put(FIELD_RECIPE_INGREDIENTS_NAME, recipe_ingredients_name);
            values.put(FIELD_RECIPE_INGREDIENTS_PHOTO, recipe_ingredients_photo);

            database.insert(TABLE_RECIPE_INGREDIENTS, null, values);
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
    public static void RecipeIngredientsSQLiteUpdate(
            Context context,
            int key,
            String recipe_ingredients_recipe,
            String recipe_ingredients_number,
            String recipe_ingredients_amount,
            String recipe_ingredients_unit,
            String recipe_ingredients_name,
            String recipe_ingredients_photo) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_INGREDIENTS_RECIPE, recipe_ingredients_recipe);
            values.put(FIELD_RECIPE_INGREDIENTS_NUMBER, recipe_ingredients_number);
            values.put(FIELD_RECIPE_INGREDIENTS_AMOUNT, recipe_ingredients_amount);
            values.put(FIELD_RECIPE_INGREDIENTS_UNIT, recipe_ingredients_unit);
            values.put(FIELD_RECIPE_INGREDIENTS_NAME, recipe_ingredients_name);
            values.put(FIELD_RECIPE_INGREDIENTS_PHOTO, recipe_ingredients_photo);

            database.update(TABLE_RECIPE_INGREDIENTS, values, key + " = ?", new String[]{String.valueOf(key)});
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
    public static void RecipeIngredientsSQLiteUpdateSingle(
            String recipe_ingredients_name,
            String recipe_ingredients_amount,
            String recipe_ingredients_unit,
            Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_INGREDIENTS + COMMAND_WHERE
                + FIELD_RECIPE_INGREDIENTS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                String number = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_NUMBER));

                if (sCurrentRecipeCommentNumber.equals(number)){

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(FIELD_RECIPE_INGREDIENTS_NAME, recipe_ingredients_name);
                    contentValues.put(FIELD_RECIPE_INGREDIENTS_AMOUNT, recipe_ingredients_amount);
                    contentValues.put(FIELD_RECIPE_INGREDIENTS_UNIT, recipe_ingredients_unit);

                    sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_ID));
                    database.update(TABLE_RECIPE_INGREDIENTS, contentValues, FIELD_RECIPE_INGREDIENTS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }
    
    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipeIngredientsSQLiteDeleteSingle(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_INGREDIENTS + COMMAND_WHERE
                + FIELD_RECIPE_INGREDIENTS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                String number = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_NUMBER));

                if (sCurrentRecipeIngredientNumber.equals(number)) {

                    sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_ID));
                    database.delete(TABLE_RECIPE_INGREDIENTS, FIELD_RECIPE_INGREDIENTS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipeIngredientsSQLiteDeleteAll(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_INGREDIENTS + COMMAND_WHERE
                + FIELD_RECIPE_INGREDIENTS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_ID));
                database.delete(TABLE_RECIPE_INGREDIENTS, FIELD_RECIPE_INGREDIENTS_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeIngredientsModel> RecipeIngredientsGetList(Context context) {

        Cursor cursor;
        List<RecipeIngredientsModel> list = new ArrayList<>();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_INGREDIENTS + COMMAND_WHERE
                + FIELD_RECIPE_INGREDIENTS_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES
                + COMMAND_ORDER_BY + FIELD_RECIPE_INGREDIENTS_RECIPE + ", " + FIELD_RECIPE_INGREDIENTS_NUMBER + COMMAND_ASCENDING;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeIngredientsModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_RECIPE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_AMOUNT)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_UNIT)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_INGREDIENTS_PHOTO))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }
}
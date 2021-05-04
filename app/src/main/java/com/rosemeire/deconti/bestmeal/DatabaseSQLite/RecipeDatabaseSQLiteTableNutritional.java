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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeNutritionalModel;

import java.util.ArrayList;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_ASTERISK;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_EQUAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_FROM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_QUOTES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_SELECT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_WHERE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_CALCIUM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_CARBS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_ENERGY;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_FAT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_FIBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_FOLATE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_ID;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_IRON;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_MAGNESIUM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_NIACIN;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_POTASSIUM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_PROTEIN;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_SATURATED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_SODIUM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_SUGARS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_THIAMIN;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_TRANS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_VITAMIN_A;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_VITAMIN_C;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_VITAMIN_D;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_VITAMIN_E;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_VITAMIN_K;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_NUTRITIONAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromSQLite;

/* ************************************************************************************************/
/* *** Treat SQLite Maintenance: RECIPE NUTRITIONAL
/* ************************************************************************************************/
public class RecipeDatabaseSQLiteTableNutritional {

    private static SQLiteDatabase database;

    /* ********************************************************************************************/
    /* *** Start SQLite connection
    /* ********************************************************************************************/
    public RecipeDatabaseSQLiteTableNutritional(RecipeDatabaseSQLiteConnectionFactory connectionFactory) {
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeDatabaseSQLiteConnectionFactory connectionFactory1 = connectionFactory;
    }

    /* ********************************************************************************************/
    /* *** Insert register SQLite table
    /* ********************************************************************************************/
    public static void RecipeNutritionalSQLiteInsert(
            String recipe_nutritional_recipe,
            String recipe_nutritional_calcium,
            String recipe_nutritional_carbs,
            String recipe_nutritional_cholesterol,
            String recipe_nutritional_monounsaturated,
            String recipe_nutritional_polyunsaturated,
            String recipe_nutritional_saturated,
            String recipe_nutritional_fat,
            String recipe_nutritional_trans,
            String recipe_nutritional_iron,
            String recipe_nutritional_fiber,
            String recipe_nutritional_folate,
            String recipe_nutritional_potassium,
            String recipe_nutritional_magnesium,
            String recipe_nutritional_sodium,
            String recipe_nutritional_energy,
            String recipe_nutritional_niacin,
            String recipe_nutritional_phosphorus,
            String recipe_nutritional_protein,
            String recipe_nutritional_riboflavin,
            String recipe_nutritional_sugars,
            String recipe_nutritional_thiamin,
            String recipe_nutritional_vitamin_e,
            String recipe_nutritional_vitamin_a,
            String recipe_nutritional_vitamin_b12,
            String recipe_nutritional_vitamin_b6,
            String recipe_nutritional_vitamin_c,
            String recipe_nutritional_vitamin_d,
            String recipe_nutritional_vitamin_k,
            Context context) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_NUTRITIONAL_RECIPE, recipe_nutritional_recipe);
            values.put(FIELD_RECIPE_NUTRITIONAL_CALCIUM, recipe_nutritional_calcium);
            values.put(FIELD_RECIPE_NUTRITIONAL_CARBS, recipe_nutritional_carbs);
            values.put(FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL, recipe_nutritional_cholesterol);
            values.put(FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED, recipe_nutritional_monounsaturated);
            values.put(FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED, recipe_nutritional_polyunsaturated);
            values.put(FIELD_RECIPE_NUTRITIONAL_SATURATED, recipe_nutritional_saturated);
            values.put(FIELD_RECIPE_NUTRITIONAL_FAT, recipe_nutritional_fat);
            values.put(FIELD_RECIPE_NUTRITIONAL_TRANS, recipe_nutritional_trans);
            values.put(FIELD_RECIPE_NUTRITIONAL_IRON, recipe_nutritional_iron);
            values.put(FIELD_RECIPE_NUTRITIONAL_FIBER, recipe_nutritional_fiber);
            values.put(FIELD_RECIPE_NUTRITIONAL_FOLATE, recipe_nutritional_folate);
            values.put(FIELD_RECIPE_NUTRITIONAL_POTASSIUM, recipe_nutritional_potassium);
            values.put(FIELD_RECIPE_NUTRITIONAL_MAGNESIUM, recipe_nutritional_magnesium);
            values.put(FIELD_RECIPE_NUTRITIONAL_SODIUM, recipe_nutritional_sodium);
            values.put(FIELD_RECIPE_NUTRITIONAL_ENERGY, recipe_nutritional_energy);
            values.put(FIELD_RECIPE_NUTRITIONAL_NIACIN, recipe_nutritional_niacin);
            values.put(FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS, recipe_nutritional_phosphorus);
            values.put(FIELD_RECIPE_NUTRITIONAL_PROTEIN, recipe_nutritional_protein);
            values.put(FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN, recipe_nutritional_riboflavin);
            values.put(FIELD_RECIPE_NUTRITIONAL_SUGARS, recipe_nutritional_sugars);
            values.put(FIELD_RECIPE_NUTRITIONAL_THIAMIN, recipe_nutritional_thiamin);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_E, recipe_nutritional_vitamin_e);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_A, recipe_nutritional_vitamin_a);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12, recipe_nutritional_vitamin_b12);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6, recipe_nutritional_vitamin_b6);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_C, recipe_nutritional_vitamin_c);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_D, recipe_nutritional_vitamin_d);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_K, recipe_nutritional_vitamin_k);

            database.insert(TABLE_RECIPE_NUTRITIONAL, null, values);
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
    public static void RecipeNutritionalSQLiteUpdate(
            Context context,
            int key,
            String recipe_nutritional_recipe,
            String recipe_nutritional_calcium,
            String recipe_nutritional_carbs,
            String recipe_nutritional_cholesterol,
            String recipe_nutritional_monounsaturated,
            String recipe_nutritional_polyunsaturated,
            String recipe_nutritional_saturated,
            String recipe_nutritional_fat,
            String recipe_nutritional_trans,
            String recipe_nutritional_iron,
            String recipe_nutritional_fiber,
            String recipe_nutritional_folate,
            String recipe_nutritional_potassium,
            String recipe_nutritional_magnesium,
            String recipe_nutritional_sodium,
            String recipe_nutritional_energy,
            String recipe_nutritional_niacin,
            String recipe_nutritional_phosphorus,
            String recipe_nutritional_protein,
            String recipe_nutritional_riboflavin,
            String recipe_nutritional_sugars,
            String recipe_nutritional_thiamin,
            String recipe_nutritional_vitamin_e,
            String recipe_nutritional_vitamin_a,
            String recipe_nutritional_vitamin_b12,
            String recipe_nutritional_vitamin_b6,
            String recipe_nutritional_vitamin_c,
            String recipe_nutritional_vitamin_d,
            String recipe_nutritional_vitamin_k) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_NUTRITIONAL_RECIPE, recipe_nutritional_recipe);
            values.put(FIELD_RECIPE_NUTRITIONAL_CALCIUM, recipe_nutritional_calcium);
            values.put(FIELD_RECIPE_NUTRITIONAL_CARBS, recipe_nutritional_carbs);
            values.put(FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL, recipe_nutritional_cholesterol);
            values.put(FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED, recipe_nutritional_monounsaturated);
            values.put(FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED, recipe_nutritional_polyunsaturated);
            values.put(FIELD_RECIPE_NUTRITIONAL_SATURATED, recipe_nutritional_saturated);
            values.put(FIELD_RECIPE_NUTRITIONAL_FAT, recipe_nutritional_fat);
            values.put(FIELD_RECIPE_NUTRITIONAL_TRANS, recipe_nutritional_trans);
            values.put(FIELD_RECIPE_NUTRITIONAL_IRON, recipe_nutritional_iron);
            values.put(FIELD_RECIPE_NUTRITIONAL_FIBER, recipe_nutritional_fiber);
            values.put(FIELD_RECIPE_NUTRITIONAL_FOLATE, recipe_nutritional_folate);
            values.put(FIELD_RECIPE_NUTRITIONAL_POTASSIUM, recipe_nutritional_potassium);
            values.put(FIELD_RECIPE_NUTRITIONAL_MAGNESIUM, recipe_nutritional_magnesium);
            values.put(FIELD_RECIPE_NUTRITIONAL_SODIUM, recipe_nutritional_sodium);
            values.put(FIELD_RECIPE_NUTRITIONAL_ENERGY, recipe_nutritional_energy);
            values.put(FIELD_RECIPE_NUTRITIONAL_NIACIN, recipe_nutritional_niacin);
            values.put(FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS, recipe_nutritional_phosphorus);
            values.put(FIELD_RECIPE_NUTRITIONAL_PROTEIN, recipe_nutritional_protein);
            values.put(FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN, recipe_nutritional_riboflavin);
            values.put(FIELD_RECIPE_NUTRITIONAL_SUGARS, recipe_nutritional_sugars);
            values.put(FIELD_RECIPE_NUTRITIONAL_THIAMIN, recipe_nutritional_thiamin);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_E, recipe_nutritional_vitamin_e);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_A, recipe_nutritional_vitamin_a);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12, recipe_nutritional_vitamin_b12);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6, recipe_nutritional_vitamin_b6);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_C, recipe_nutritional_vitamin_c);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_D, recipe_nutritional_vitamin_d);
            values.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_K, recipe_nutritional_vitamin_k);

            database.update(TABLE_RECIPE_NUTRITIONAL, values, key + " = ?", new String[]{String.valueOf(key)});
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
    public static void RecipeNutritionalSQLiteUpdateSingle(
            String recipe_nutritional_calcium,
            String recipe_nutritional_carbs,
            String recipe_nutritional_cholesterol,
            String recipe_nutritional_monounsaturated,
            String recipe_nutritional_polyunsaturated,
            String recipe_nutritional_saturated,
            String recipe_nutritional_fat,
            String recipe_nutritional_trans,
            String recipe_nutritional_iron,
            String recipe_nutritional_fiber,
            String recipe_nutritional_folate,
            String recipe_nutritional_potassium,
            String recipe_nutritional_magnesium,
            String recipe_nutritional_sodium,
            String recipe_nutritional_energy,
            String recipe_nutritional_niacin,
            String recipe_nutritional_phosphorus,
            String recipe_nutritional_protein,
            String recipe_nutritional_riboflavin,
            String recipe_nutritional_sugars,
            String recipe_nutritional_thiamin,
            String recipe_nutritional_vitamin_e,
            String recipe_nutritional_vitamin_a,
            String recipe_nutritional_vitamin_b12,
            String recipe_nutritional_vitamin_b6,
            String recipe_nutritional_vitamin_c,
            String recipe_nutritional_vitamin_d,
            String recipe_nutritional_vitamin_k,
            Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_NUTRITIONAL + COMMAND_WHERE
                + FIELD_RECIPE_NUTRITIONAL_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                String number = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_RECIPE));

                if (sCurrentRecipeNumber.equals(number)) {

                    ContentValues contentValues = new ContentValues();

                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_CALCIUM, recipe_nutritional_calcium);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_CARBS, recipe_nutritional_carbs);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL, recipe_nutritional_cholesterol);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED, recipe_nutritional_monounsaturated);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED, recipe_nutritional_polyunsaturated);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_SATURATED, recipe_nutritional_saturated);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_FAT, recipe_nutritional_fat);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_TRANS, recipe_nutritional_trans);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_IRON, recipe_nutritional_iron);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_FIBER, recipe_nutritional_fiber);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_FOLATE, recipe_nutritional_folate);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_POTASSIUM, recipe_nutritional_potassium);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_MAGNESIUM, recipe_nutritional_magnesium);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_SODIUM, recipe_nutritional_sodium);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_ENERGY, recipe_nutritional_energy);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_NIACIN, recipe_nutritional_niacin);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS, recipe_nutritional_phosphorus);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_PROTEIN, recipe_nutritional_protein);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN, recipe_nutritional_riboflavin);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_SUGARS, recipe_nutritional_sugars);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_THIAMIN, recipe_nutritional_thiamin);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_E, recipe_nutritional_vitamin_e);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_A, recipe_nutritional_vitamin_a);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12, recipe_nutritional_vitamin_b12);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6, recipe_nutritional_vitamin_b6);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_C, recipe_nutritional_vitamin_c);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_D, recipe_nutritional_vitamin_d);
                    contentValues.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_K, recipe_nutritional_vitamin_k);

                    sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_ID));
                    database.update(TABLE_RECIPE_NUTRITIONAL, contentValues, FIELD_RECIPE_NUTRITIONAL_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }
    
    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipeNutritionalSQLiteDeleteAll(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_NUTRITIONAL + COMMAND_WHERE
                + FIELD_RECIPE_NUTRITIONAL_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_ID));
                database.delete(TABLE_RECIPE_NUTRITIONAL, FIELD_RECIPE_NUTRITIONAL_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipeNutritionalModel> RecipeNutritionalGetList(Context context) {

        Cursor cursor;
        List<RecipeNutritionalModel> list = new ArrayList<>();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_NUTRITIONAL + COMMAND_WHERE
                + FIELD_RECIPE_NUTRITIONAL_RECIPE + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipeNutritionalModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_RECIPE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_CALCIUM)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_CARBS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_SATURATED)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_FAT)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_TRANS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_IRON)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_FIBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_FOLATE)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_POTASSIUM)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_MAGNESIUM)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_SODIUM)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_ENERGY)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_NIACIN)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_PROTEIN)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_SUGARS)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_THIAMIN)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_VITAMIN_E)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_VITAMIN_A)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_VITAMIN_C)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_VITAMIN_D)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_NUTRITIONAL_VITAMIN_K))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }
}
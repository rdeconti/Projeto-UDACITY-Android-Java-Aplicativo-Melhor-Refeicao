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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipePurchaseModel;

import java.util.ArrayList;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_ASTERISK;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_EQUAL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_FROM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_QUOTES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_SELECT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.COMMAND_WHERE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_PURCHASE_ID;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_PURCHASE_INGREDIENT_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_PURCHASE_RECIPE_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_PURCHASE_RECIPE_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_PURCHASE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePurchaseIngredientNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePurchaseRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromSQLite;

/* ************************************************************************************************/
/* *** Treat SQLite Maintenance: RECIPE PURCHASE LIST
/* ************************************************************************************************/
public class RecipeDatabaseSQLiteTablePurchase {

    private static SQLiteDatabase database;

    /* ********************************************************************************************/
    /* *** Start SQLite connection
    /* ********************************************************************************************/
    public RecipeDatabaseSQLiteTablePurchase(RecipeDatabaseSQLiteConnectionFactory connectionFactory) {
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeDatabaseSQLiteConnectionFactory connectionFactory1 = connectionFactory;
    }

    /* ********************************************************************************************/
    /* *** Insert register SQLite table
    /* ********************************************************************************************/
    public static void RecipePurchaseSQLiteInsert(
            Context context,
            String recipe_purchase_recipe_name,
            String recipe_purchase_recipe_number,
            String recipe_purchase_ingredient_name,
            String recipe_purchase_ingredient_number,
            String recipe_purchase_ingredient_amount,
            String recipe_purchase_ingredient_unit) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_PURCHASE_RECIPE_NAME, recipe_purchase_recipe_name);
            values.put(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER, recipe_purchase_recipe_number);
            values.put(FIELD_RECIPE_PURCHASE_INGREDIENT_NAME, recipe_purchase_ingredient_name);
            values.put(FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER, recipe_purchase_ingredient_number);
            values.put(FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT, recipe_purchase_ingredient_amount);
            values.put(FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT, recipe_purchase_ingredient_unit);

            database.insert(TABLE_RECIPE_PURCHASE, null, values);
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
    public static void RecipePurchaseSQLiteUpdate(
            Context context,
            int key,
            String recipe_purchase_recipe_name,
            String recipe_purchase_recipe_number,
            String recipe_purchase_ingredient_name,
            String recipe_purchase_ingredient_number,
            String recipe_purchase_ingredient_amount,
            String recipe_purchase_ingredient_unit) {

        try {

            database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();
            database.beginTransaction();

            ContentValues values = new ContentValues();

            values.put(FIELD_RECIPE_PURCHASE_RECIPE_NAME, recipe_purchase_recipe_name);
            values.put(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER, recipe_purchase_recipe_number);
            values.put(FIELD_RECIPE_PURCHASE_INGREDIENT_NAME, recipe_purchase_ingredient_name);
            values.put(FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER, recipe_purchase_ingredient_number);
            values.put(FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT, recipe_purchase_ingredient_amount);
            values.put(FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT, recipe_purchase_ingredient_unit);

            database.update(TABLE_RECIPE_PURCHASE, values, key + " = ?", new String[]{String.valueOf(key)});
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
    public static void RecipePurchaseSQLiteDeleteSingle(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_PURCHASE + COMMAND_WHERE
                + FIELD_RECIPE_PURCHASE_RECIPE_NUMBER + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipePurchaseRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                String number = cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER));

                if (sCurrentRecipePurchaseIngredientNumber.equals(number)) {

                    sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_ID));
                    database.delete(TABLE_RECIPE_PURCHASE, FIELD_RECIPE_PURCHASE_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

                }

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Delete register SQLite table
    /* ********************************************************************************************/
    public static void RecipePurchaseSQLiteDeleteAll(Context context) {

        Cursor cursor;

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_PURCHASE + COMMAND_WHERE
                + FIELD_RECIPE_PURCHASE_RECIPE_NUMBER + COMMAND_EQUAL + COMMAND_QUOTES + sCurrentRecipeNumber + COMMAND_QUOTES;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionWritable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                sRecipeKeyFromSQLite = cursor.getInt(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_ID));
                database.delete(TABLE_RECIPE_PURCHASE, FIELD_RECIPE_PURCHASE_ID + " = ?", new String[]{String.valueOf(sRecipeKeyFromSQLite)});

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

    }

    /* ********************************************************************************************/
    /* *** Obtain all registers from SQLite table and return in a list
    /* ********************************************************************************************/
    public static List<RecipePurchaseModel> RecipePurchaseGetList(Context context) {

        Cursor cursor;
        List<RecipePurchaseModel> list = new ArrayList<>();

        list.clear();

        String selectQuery = COMMAND_SELECT + COMMAND_ASTERISK + COMMAND_FROM + TABLE_RECIPE_PURCHASE;

        database = new RecipeDatabaseSQLiteConnectionFactory(context).databaseConnectionReadable();

        cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {

            do {

                list.add(new RecipePurchaseModel(
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_RECIPE_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_INGREDIENT_NAME)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT)),
                        cursor.getString(cursor.getColumnIndex(FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT))));

            } while (cursor.moveToNext());
        }

        cursor.close();
        database.close();

        return list;

    }
}
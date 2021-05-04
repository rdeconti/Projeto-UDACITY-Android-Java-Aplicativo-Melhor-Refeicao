package com.rosemeire.deconti.bestmeal.ApplicationDefinitions;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/* ************************************************************************************************/
/* *** Database Public Definitions
/* ************************************************************************************************/
public class ApplicationDatabaseDefinitions {

    // .............................................................................. FireStore data
    public static final String PATH_RECIPES_1 = "recipes/";
    public static final String PATH_INGREDIENTS_1 = "ingredients/";
    public static final String PATH_INSTRUCTIONS_1 = "instructions/";
    public static final String PATH_RECIPES_2 = "recipes";
    public static final String PATH_INGREDIENTS_2 = "ingredients";
    public static final String PATH_INSTRUCTIONS_2 = "instructions";
    public static final String FILE_TYPE = ".jpg";
    public static final String CRUD_TYPE_C = "C";
    public static final String CRUD_TYPE_U = "U";
    //................................................ Define SQLite Database: Name, Path and Version
    public static final String DATA_BASE_NAME = "BestMeal.db";
    public static final int DATA_BASE_VERSION = 53;
    //......................................................................... Define data get FROM
    public static final String DATA_GET_FROM_FIREBASE = "FIREBASE";
    public static final String DATA_GET_FROM_SQLITE = "SQLITE";
    //...................................................... Define SQLite Tables and Firebase Nodes
    public static final String TABLE_RECIPE_HEADER = "recipe_header";
    public static final String TABLE_RECIPE_INGREDIENTS = "recipe_ingredients";
    public static final String TABLE_RECIPE_INSTRUCTIONS = "recipe_instructions";
    public static final String TABLE_RECIPE_NUTRITIONAL = "recipe_nutritional";
    public static final String TABLE_RECIPE_TIPS = "recipe_tips";
    public static final String TABLE_RECIPE_COMMENTS = "recipe_comments";
    public static final String TABLE_RECIPE_PURCHASE = "recipe_purchase";
    public static final String TABLE_RECIPE_PURCHASE_WIDGET = "recipe_purchase_widget";
    //............................................................ Define SQLite and Firebase Fields
    //................................................................................ Purchase list
    public static final String FIELD_RECIPE_PURCHASE_ID = "purchase_list_id";
    public static final String FIELD_RECIPE_PURCHASE_RECIPE_NAME = "purchase_list_recipe_name";
    public static final String FIELD_RECIPE_PURCHASE_RECIPE_NUMBER = "purchase_list_recipe_number";
    public static final String FIELD_RECIPE_PURCHASE_INGREDIENT_NAME = "purchase_list_ingredient_name";
    public static final String FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER = "purchase_list_ingredient_number";
    public static final String FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT = "purchase_list_ingredient_amount";
    public static final String FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT = "purchase_list_ingredient_unit";
    //............................................................ Define SQLite and Firebase Fields
    //................................................................................ Purchase widget
    private static final String FIELD_RECIPE_PURCHASE_WIDGET_ID = "_id";
    public static final String FIELD_RECIPE_PURCHASE_WIDGET_TEXT = "purchase_widget_text";
    //............................................................ Define SQLite and Firebase Fields
    //.............................................................................. Recipe Comments
    public static final String FIELD_RECIPE_COMMENTS_ID = "recipe_comments_id";
    public static final String FIELD_RECIPE_COMMENTS_RECIPE = "recipe_comments_recipe";
    public static final String FIELD_RECIPE_COMMENTS_NUMBER = "recipe_comments_number";
    public static final String FIELD_RECIPE_COMMENTS_USER = "recipe_comments_user";
    public static final String FIELD_RECIPE_COMMENTS_TEXT = "recipe_comments_text";
    //............................................................ Define SQLite and Firebase Fields
    //................................................................................ Recipe Header
    public static final String FIELD_RECIPE_HEADER_ID = "recipe_header_id";
    public static final String FIELD_RECIPE_HEADER_NUMBER = "recipe_header_number";
    public static final String FIELD_RECIPE_HEADER_NAME = "recipe_header_name";
    public static final String FIELD_RECIPE_HEADER_AUTHOR = "recipe_header_author";
    public static final String FIELD_RECIPE_HEADER_UPDATE = "recipe_header_update";
    public static final String FIELD_RECIPE_HEADER_PHOTO = "recipe_header_photo";
    public static final String FIELD_RECIPE_HEADER_STATUS = "recipe_header_status";
    public static final String FIELD_RECIPE_HEADER_STATUS_APPROVED = "recipe_header_status_approved";
    public static final String FIELD_RECIPE_HEADER_STATUS_REPROVED = "recipe_header_status_reproved";
    public static final String FIELD_RECIPE_HEADER_STATUS_LIKED = "recipe_header_status_like";
    public static final String FIELD_RECIPE_HEADER_STATUS_DISLIKED = "recipe_header_status_disliked";
    public static final String FIELD_RECIPE_HEADER_STATUS_PRINTED = "recipe_header_status_printed";
    public static final String FIELD_RECIPE_HEADER_STATUS_SHARED = "recipe_header_status_shared";
    public static final String FIELD_RECIPE_HEADER_STATUS_VISUALIZED = "recipe_header_status_visualized";
    public static final String FIELD_RECIPE_HEADER_STATUS_COMMENTED = "recipe_header_status_commented";
    public static final String FIELD_RECIPE_HEADER_LANGUAGE = "recipe_header_language";
    public static final String FIELD_RECIPE_HEADER_NUMBER_LIKES = "recipe_header_likes";
    public static final String FIELD_RECIPE_HEADER_NUMBER_DISLIKES = "recipe_header_dislikes";
    public static final String FIELD_RECIPE_HEADER_NUMBER_APPROVALS = "recipe_header_approvals";
    public static final String FIELD_RECIPE_HEADER_NUMBER_REPROVALS = "recipe_header_reprovals";
    public static final String FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS = "recipe_header_visualizations";
    public static final String FIELD_RECIPE_HEADER_NUMBER_PRINTINGS = "recipe_header_printings";
    public static final String FIELD_RECIPE_HEADER_NUMBER_COMMENTS = "recipe_header_comments";
    public static final String FIELD_RECIPE_HEADER_NUMBER_SHARINGS = "recipe_header_sharings";
    public static final String FIELD_RECIPE_HEADER_RATING_VALUE = "recipe_header_rating_value";
    public static final String FIELD_RECIPE_HEADER_RATING_DISPLAY = "recipe_header_rating_display";
    public static final String FIELD_RECIPE_HEADER_DIET = "recipe_header_diet";
    public static final String FIELD_RECIPE_HEADER_CALORIES = "recipe_header_calories";
    public static final String FIELD_RECIPE_HEADER_CUISENES = "recipe_header_cuisenes";
    public static final String FIELD_RECIPE_HEADER_HEALTH = "recipe_header_health";
    public static final String FIELD_RECIPE_HEADER_OCCASION = "recipe_header_occasion";
    public static final String FIELD_RECIPE_HEADER_SERVINGS = "recipe_header_servings";
    public static final String FIELD_RECIPE_HEADER_PRICE = "recipe_header_price";
    public static final String FIELD_RECIPE_HEADER_TIME = "recipe_header_time";
    public static final String FIELD_RECIPE_HEADER_DIFFICULT = "recipe_header_difficult";
    //............................................................ Define SQLite and Firebase Fields
    //........................................................................... Recipe Ingredients
    public static final String FIELD_RECIPE_INGREDIENTS_ID = "recipe_ingredients_id";
    public static final String FIELD_RECIPE_INGREDIENTS_RECIPE = "recipe_ingredients_recipe";
    public static final String FIELD_RECIPE_INGREDIENTS_NUMBER = "recipe_ingredients_number";
    public static final String FIELD_RECIPE_INGREDIENTS_AMOUNT = "recipe_ingredients_amount";
    public static final String FIELD_RECIPE_INGREDIENTS_UNIT = "recipe_ingredients_unit";
    public static final String FIELD_RECIPE_INGREDIENTS_NAME = "recipe_ingredients_name";
    public static final String FIELD_RECIPE_INGREDIENTS_PHOTO = "recipe_ingredients_photo";
    //............................................................ Define SQLite and Firebase Fields
    //.......................................................................... Recipe Instructions
    public static final String FIELD_RECIPE_INSTRUCTIONS_ID = "recipe_instructions_id";
    public static final String FIELD_RECIPE_INSTRUCTIONS_RECIPE = "recipe_instructions_recipe";
    public static final String FIELD_RECIPE_INSTRUCTIONS_NUMBER = "recipe_instructions_number";
    public static final String FIELD_RECIPE_INSTRUCTIONS_TEXT = "recipe_instructions_text";
    public static final String FIELD_RECIPE_INSTRUCTIONS_PHOTO = "recipe_instructions_photo";
    //............................................................ Define SQLite and Firebase Fields
    //........................................................................... Recipe Nutritional
    public static final String FIELD_RECIPE_NUTRITIONAL_ID = "recipe_nutritional_id";
    public static final String FIELD_RECIPE_NUTRITIONAL_RECIPE = "recipe_nutritional_recipe";
    public static final String FIELD_RECIPE_NUTRITIONAL_CALCIUM = "recipe_nutritional_calcium";
    public static final String FIELD_RECIPE_NUTRITIONAL_CARBS = "recipe_nutritional_carbs";
    public static final String FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL = "recipe_nutritional_cholesterol";
    public static final String FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED = "recipe_nutritional_monounsaturated";
    public static final String FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED = "recipe_nutritional_polyunsaturated";
    public static final String FIELD_RECIPE_NUTRITIONAL_SATURATED = "recipe_nutritional_saturated";
    public static final String FIELD_RECIPE_NUTRITIONAL_FAT = "recipe_nutritional_fat";
    public static final String FIELD_RECIPE_NUTRITIONAL_TRANS = "recipe_nutritional_trans";
    public static final String FIELD_RECIPE_NUTRITIONAL_IRON = "recipe_nutritional_iron";
    public static final String FIELD_RECIPE_NUTRITIONAL_FIBER = "recipe_nutritional_fiber";
    public static final String FIELD_RECIPE_NUTRITIONAL_FOLATE = "recipe_nutritional_folate";
    public static final String FIELD_RECIPE_NUTRITIONAL_POTASSIUM = "recipe_nutritional_potassium";
    public static final String FIELD_RECIPE_NUTRITIONAL_MAGNESIUM = "recipe_nutritional_magnesium";
    public static final String FIELD_RECIPE_NUTRITIONAL_SODIUM = "recipe_nutritional_sodium";
    public static final String FIELD_RECIPE_NUTRITIONAL_ENERGY = "recipe_nutritional_energy";
    public static final String FIELD_RECIPE_NUTRITIONAL_NIACIN = "recipe_nutritional_niacin";
    public static final String FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS = "recipe_nutritional_phosphorus";
    public static final String FIELD_RECIPE_NUTRITIONAL_PROTEIN = "recipe_nutritional_protein";
    public static final String FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN = "recipe_nutritional_riboflavin";
    public static final String FIELD_RECIPE_NUTRITIONAL_SUGARS = "recipe_nutritional_sugars";
    public static final String FIELD_RECIPE_NUTRITIONAL_THIAMIN = "recipe_nutritional_thiamin";
    public static final String FIELD_RECIPE_NUTRITIONAL_VITAMIN_E = "recipe_nutritional_vitamin_e";
    public static final String FIELD_RECIPE_NUTRITIONAL_VITAMIN_A = "recipe_nutritional_vitamin_a";
    public static final String FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12 = "recipe_nutritional_vitamin_b12";
    public static final String FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6 = "recipe_nutritional_vitamin_b6";
    public static final String FIELD_RECIPE_NUTRITIONAL_VITAMIN_C = "recipe_nutritional_vitamin_c";
    public static final String FIELD_RECIPE_NUTRITIONAL_VITAMIN_D = "recipe_nutritional_vitamin_d";
    public static final String FIELD_RECIPE_NUTRITIONAL_VITAMIN_K = "recipe_nutritional_vitamin_k";
    //............................................................ Define SQLite and Firebase Fields
    //.................................................................................. Recipe Tips
    public static final String FIELD_RECIPE_TIPS_ID = "recipe_tips_id";
    public static final String FIELD_RECIPE_TIPS_RECIPE = "recipe_tips_recipe";
    public static final String FIELD_RECIPE_TIPS_NUMBER = "recipe_tips_number";
    public static final String FIELD_RECIPE_TIPS_TEXT = "recipe_tips_text";
    //............................................................... SQLite commands and attributes
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS ";
    public static final String COMMAND_SELECT = "SELECT ";
    public static final String COMMAND_FROM = " FROM ";
    public static final String COMMAND_WHERE = " WHERE ";
    public static final String COMMAND_EQUAL = " = ";
    public static final String COMMAND_ORDER_BY = " ORDER BY ";
    public static final String COMMAND_ASCENDING = " ASC ";
    public static final String COMMAND_ASTERISK = " * ";
    public static final String COMMAND_QUOTES = "'";

    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS ";
    private static final String ATTRIBUTE_INTEGER = " INTEGER ";
    private static final String ATTRIBUTE_TEXT = " TEXT ";
    private static final String ATTRIBUTE_PRIMARY_KEY = "PRIMARY KEY ";
    private static final String ATTRIBUTE_AUTOINCREMENT = "AUTOINCREMENT ";
    private static final String ATTRIBUTE_NOTNULL = "NOT NULL ";
    //............................................................... SQLite Structure Purchase List
    public static final
    String CREATE_TABLE_PURCHASE_LIST = CREATE_TABLE + TABLE_RECIPE_PURCHASE + " ("
            + FIELD_RECIPE_PURCHASE_ID + ATTRIBUTE_INTEGER + ATTRIBUTE_PRIMARY_KEY + ATTRIBUTE_AUTOINCREMENT
            + ", "
            + FIELD_RECIPE_PURCHASE_RECIPE_NAME + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_PURCHASE_RECIPE_NUMBER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_PURCHASE_INGREDIENT_NAME + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ")";
    //............................................................. SQLite Structure Purchase Widget
    public static final
    String CREATE_TABLE_PURCHASE_WIDGET = CREATE_TABLE + TABLE_RECIPE_PURCHASE_WIDGET + " ("
            + FIELD_RECIPE_PURCHASE_WIDGET_ID + ATTRIBUTE_INTEGER + ATTRIBUTE_PRIMARY_KEY + ATTRIBUTE_AUTOINCREMENT
            + ", "
            + FIELD_RECIPE_PURCHASE_WIDGET_TEXT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ")";
    //............................................................. SQLite Structure Recipe Comments
    public static final
    String CREATE_TABLE_RECIPE_COMMENTS = CREATE_TABLE + TABLE_RECIPE_COMMENTS + " ("
            + FIELD_RECIPE_COMMENTS_ID + ATTRIBUTE_INTEGER + ATTRIBUTE_PRIMARY_KEY + ATTRIBUTE_AUTOINCREMENT
            + ", "
            + FIELD_RECIPE_COMMENTS_RECIPE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_COMMENTS_NUMBER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_COMMENTS_USER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_COMMENTS_TEXT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ")";
    //............................................................... SQLite Structure Recipe Header
    public static final
    String CREATE_TABLE_RECIPE_HEADER = CREATE_TABLE + TABLE_RECIPE_HEADER + " ("
            + FIELD_RECIPE_HEADER_ID + ATTRIBUTE_INTEGER + ATTRIBUTE_PRIMARY_KEY + ATTRIBUTE_AUTOINCREMENT
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NAME + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_AUTHOR + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_UPDATE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_PHOTO + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_STATUS_APPROVED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_STATUS_REPROVED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_STATUS_LIKED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_STATUS_DISLIKED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_STATUS_PRINTED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_STATUS_SHARED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_STATUS_VISUALIZED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_STATUS_COMMENTED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_LANGUAGE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER_LIKES + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER_DISLIKES + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER_APPROVALS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER_REPROVALS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER_PRINTINGS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER_COMMENTS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_NUMBER_SHARINGS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_RATING_VALUE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_RATING_DISPLAY + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_DIET + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_CALORIES + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_CUISENES + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_HEALTH + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_OCCASION + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_SERVINGS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_PRICE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_TIME + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_HEADER_DIFFICULT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ")";
    //.......................................................... SQLite Structure Recipe Ingredients
    public static final
    String CREATE_TABLE_RECIPE_INGREDIENTS = CREATE_TABLE + TABLE_RECIPE_INGREDIENTS + " ("
            + FIELD_RECIPE_INGREDIENTS_ID + ATTRIBUTE_INTEGER + ATTRIBUTE_PRIMARY_KEY + ATTRIBUTE_AUTOINCREMENT
            + ", "
            + FIELD_RECIPE_INGREDIENTS_RECIPE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_INGREDIENTS_NUMBER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_INGREDIENTS_AMOUNT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_INGREDIENTS_UNIT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_INGREDIENTS_NAME + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_INGREDIENTS_PHOTO + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ")";
    //......................................................... SQLite Structure Recipe Instructions
    public static final
    String CREATE_TABLE_RECIPE_INSTRUCTIONS = CREATE_TABLE + TABLE_RECIPE_INSTRUCTIONS + " ("
            + FIELD_RECIPE_INSTRUCTIONS_ID + ATTRIBUTE_INTEGER + ATTRIBUTE_PRIMARY_KEY + ATTRIBUTE_AUTOINCREMENT
            + ", "
            + FIELD_RECIPE_INSTRUCTIONS_RECIPE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_INSTRUCTIONS_NUMBER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_INSTRUCTIONS_TEXT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_INSTRUCTIONS_PHOTO + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ")";
    //.......................................................... SQLite Structure Recipe Nutritional
    public static final
    String CREATE_TABLE_RECIPE_NUTRITIONAL = CREATE_TABLE + TABLE_RECIPE_NUTRITIONAL + " ("
            + FIELD_RECIPE_NUTRITIONAL_ID + ATTRIBUTE_INTEGER + ATTRIBUTE_PRIMARY_KEY + ATTRIBUTE_AUTOINCREMENT
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_RECIPE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_CALCIUM + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_CARBS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_SATURATED + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_FAT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_TRANS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_IRON + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_FIBER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_FOLATE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_POTASSIUM + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_MAGNESIUM + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_SODIUM + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_ENERGY + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_NIACIN + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_PROTEIN + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_SUGARS + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_THIAMIN + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_VITAMIN_E + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_VITAMIN_A + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12 + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6 + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_VITAMIN_C + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_VITAMIN_D + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_NUTRITIONAL_VITAMIN_K + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ")";
    //................................................................. SQLite Structure Recipe Tips
    public static final
    String CREATE_TABLE_RECIPE_TIPS = CREATE_TABLE + TABLE_RECIPE_TIPS + " ("
            + FIELD_RECIPE_TIPS_ID + ATTRIBUTE_INTEGER + ATTRIBUTE_PRIMARY_KEY + ATTRIBUTE_AUTOINCREMENT
            + ", "
            + FIELD_RECIPE_TIPS_RECIPE + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_TIPS_NUMBER + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ", "
            + FIELD_RECIPE_TIPS_TEXT + ATTRIBUTE_TEXT + ATTRIBUTE_NOTNULL
            + ")";
    // ............................................................................... Firebase data
    public static FirebaseAuth sFirebaseAuth;
    public static FirebaseUser sFirebaseUser;
    public static String sGetDataFrom;
    public static String sTypeCRUD;

}


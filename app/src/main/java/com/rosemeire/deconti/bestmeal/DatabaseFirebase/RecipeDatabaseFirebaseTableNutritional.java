package com.rosemeire.deconti.bestmeal.DatabaseFirebase;

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

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingDatabaseError;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingExceptionError;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeNutritionalModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableNutritional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_CALCIUM;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_CARBS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_ENERGY;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_FAT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_FIBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_NUTRITIONAL_FOLATE;
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
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCommentNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;

/* ************************************************************************************************/
/* *** Treat Firebase Maintenance: RECIPE NUTRITIONAL
/* ************************************************************************************************/
public class RecipeDatabaseFirebaseTableNutritional {

    private static DatabaseReference databaseReference;
    private static Query firebaseQuery;

    /* ********************************************************************************************/
    /* *** Treat Reset
    /* ********************************************************************************************/
    public static boolean RecipeNutritionalFirebaseReset(Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_NUTRITIONAL);
            databaseReference.removeValue();
            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);
            return false;

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Delete
    /* ********************************************************************************************/
    public static void RecipeNutritionalFirebaseDeleteAll() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_NUTRITIONAL);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_NUTRITIONAL_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        snapshot.getRef().removeValue();

                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                new SupportHandlingDatabaseError(this.getClass().getSimpleName(), databaseError);

            }
        });
    }

    /* ********************************************************************************************/
    /* *** Treat Update
    /* ********************************************************************************************/
    public static boolean RecipeNutritionalFirebaseUpdate(
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
            String firebaseKey,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_NUTRITIONAL);

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_RECIPE).setValue(recipe_nutritional_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_CALCIUM).setValue(recipe_nutritional_calcium);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_CARBS).setValue(recipe_nutritional_carbs);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL).setValue(recipe_nutritional_cholesterol);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED).setValue(recipe_nutritional_monounsaturated);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED).setValue(recipe_nutritional_polyunsaturated);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_SATURATED).setValue(recipe_nutritional_saturated);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_FAT).setValue(recipe_nutritional_fat);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_TRANS).setValue(recipe_nutritional_trans);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_IRON).setValue(recipe_nutritional_iron);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_FIBER).setValue(recipe_nutritional_fiber);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_FOLATE).setValue(recipe_nutritional_folate);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_POTASSIUM).setValue(recipe_nutritional_potassium);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_MAGNESIUM).setValue(recipe_nutritional_magnesium);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_SODIUM).setValue(recipe_nutritional_sodium);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_ENERGY).setValue(recipe_nutritional_energy);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_NIACIN).setValue(recipe_nutritional_niacin);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS).setValue(recipe_nutritional_phosphorus);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_PROTEIN).setValue(recipe_nutritional_protein);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN).setValue(recipe_nutritional_riboflavin);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_SUGARS).setValue(recipe_nutritional_sugars);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_THIAMIN).setValue(recipe_nutritional_thiamin);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_E).setValue(recipe_nutritional_vitamin_e);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_A).setValue(recipe_nutritional_vitamin_a);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12).setValue(recipe_nutritional_vitamin_b12);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6).setValue(recipe_nutritional_vitamin_b6);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_C).setValue(recipe_nutritional_vitamin_c);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_D).setValue(recipe_nutritional_vitamin_d);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_K).setValue(recipe_nutritional_vitamin_k);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);
            return false;

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Update
    /* ********************************************************************************************/
    public static void RecipeNutritionalFirebaseUpdateSingle(
            final String recipe_nutritional_calcium,
            final String recipe_nutritional_carbs,
            final String recipe_nutritional_cholesterol,
            final String recipe_nutritional_monounsaturated,
            final String recipe_nutritional_polyunsaturated,
            final String recipe_nutritional_saturated,
            final String recipe_nutritional_fat,
            final String recipe_nutritional_trans,
            final String recipe_nutritional_iron,
            final String recipe_nutritional_fiber,
            final String recipe_nutritional_folate,
            final String recipe_nutritional_potassium,
            final String recipe_nutritional_magnesium,
            final String recipe_nutritional_sodium,
            final String recipe_nutritional_energy,
            final String recipe_nutritional_niacin,
            final String recipe_nutritional_phosphorus,
            final String recipe_nutritional_protein,
            final String recipe_nutritional_riboflavin,
            final String recipe_nutritional_sugars,
            final String recipe_nutritional_thiamin,
            final String recipe_nutritional_vitamin_e,
            final String recipe_nutritional_vitamin_a,
            final String recipe_nutritional_vitamin_b12,
            final String recipe_nutritional_vitamin_b6,
            final String recipe_nutritional_vitamin_c,
            final String recipe_nutritional_vitamin_d,
            final String recipe_nutritional_vitamin_k,
            final Context context) {
        
        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_NUTRITIONAL);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_NUTRITIONAL_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String number = dataSnapshot.child(FIELD_RECIPE_NUTRITIONAL_RECIPE).getValue(String.class);

                    if (Objects.equals(number, sCurrentRecipeCommentNumber)) {

                        HashMap hashMap = new HashMap();
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_CALCIUM,recipe_nutritional_calcium);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_CARBS,recipe_nutritional_carbs);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL,recipe_nutritional_cholesterol);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED,recipe_nutritional_monounsaturated);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED,recipe_nutritional_polyunsaturated);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_SATURATED,recipe_nutritional_saturated);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_FAT,recipe_nutritional_fat);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_TRANS,recipe_nutritional_trans);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_IRON,recipe_nutritional_iron);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_FIBER,recipe_nutritional_fiber);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_FOLATE,recipe_nutritional_folate);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_POTASSIUM,recipe_nutritional_potassium);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_MAGNESIUM,recipe_nutritional_magnesium);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_SODIUM,recipe_nutritional_sodium);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_ENERGY,recipe_nutritional_energy);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_NIACIN,recipe_nutritional_niacin);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS,recipe_nutritional_phosphorus);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_PROTEIN,recipe_nutritional_protein);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN,recipe_nutritional_riboflavin);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_SUGARS,recipe_nutritional_sugars);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_THIAMIN,recipe_nutritional_thiamin);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_E,recipe_nutritional_vitamin_e);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_A,recipe_nutritional_vitamin_a);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12,recipe_nutritional_vitamin_b12);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6,recipe_nutritional_vitamin_b6);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_C,recipe_nutritional_vitamin_c);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_D,recipe_nutritional_vitamin_d);
                        hashMap.put(FIELD_RECIPE_NUTRITIONAL_VITAMIN_K,recipe_nutritional_vitamin_k);
                        databaseReference.updateChildren(hashMap);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                new SupportHandlingDatabaseError(context.getClass().getSimpleName(), databaseError);

            }
        });
    }
    
    /* ********************************************************************************************/
    /* *** Treat Create
    /* ********************************************************************************************/
    public static void RecipeNutritionalFirebaseCreate(
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

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_NUTRITIONAL);

            String firebaseKey = databaseReference.push().getKey();

            assert firebaseKey != null;

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_RECIPE).setValue(recipe_nutritional_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_CALCIUM).setValue(recipe_nutritional_calcium);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_CARBS).setValue(recipe_nutritional_carbs);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL).setValue(recipe_nutritional_cholesterol);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED).setValue(recipe_nutritional_monounsaturated);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED).setValue(recipe_nutritional_polyunsaturated);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_SATURATED).setValue(recipe_nutritional_saturated);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_FAT).setValue(recipe_nutritional_fat);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_TRANS).setValue(recipe_nutritional_trans);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_IRON).setValue(recipe_nutritional_iron);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_FIBER).setValue(recipe_nutritional_fiber);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_FOLATE).setValue(recipe_nutritional_folate);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_POTASSIUM).setValue(recipe_nutritional_potassium);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_MAGNESIUM).setValue(recipe_nutritional_magnesium);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_SODIUM).setValue(recipe_nutritional_sodium);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_ENERGY).setValue(recipe_nutritional_energy);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_NIACIN).setValue(recipe_nutritional_niacin);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS).setValue(recipe_nutritional_phosphorus);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_PROTEIN).setValue(recipe_nutritional_protein);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN).setValue(recipe_nutritional_riboflavin);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_SUGARS).setValue(recipe_nutritional_sugars);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_THIAMIN).setValue(recipe_nutritional_thiamin);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_E).setValue(recipe_nutritional_vitamin_e);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_A).setValue(recipe_nutritional_vitamin_a);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12).setValue(recipe_nutritional_vitamin_b12);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6).setValue(recipe_nutritional_vitamin_b6);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_C).setValue(recipe_nutritional_vitamin_c);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_D).setValue(recipe_nutritional_vitamin_d);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_K).setValue(recipe_nutritional_vitamin_k);

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Get Recipe Detail Nutritional List
    /* ********************************************************************************************/
    public static List<RecipeNutritionalModel> RecipeListDetailNutritionalGetList() {

        final List<RecipeNutritionalModel> list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_NUTRITIONAL);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_NUTRITIONAL_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeNutritionalModel snapshotDetails = snapshot.getValue(RecipeNutritionalModel.class);
                        list.add(snapshotDetails);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                new SupportHandlingDatabaseError(this.getClass().getSimpleName(), databaseError);
            }
        });

        return list;

    }

    /* ********************************************************************************************/
    /* *** Treat transfer recipe from Firebase to SQLite
    /* ********************************************************************************************/
    public static void RecipeNutritionalTransferFromFirebase(final Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_NUTRITIONAL);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_NUTRITIONAL_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeDatabaseSQLiteTableNutritional.RecipeNutritionalSQLiteInsert(
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_RECIPE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_CALCIUM).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_CARBS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_CHOLESTEROL).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_MONOUNSATURATED).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_POLYUNSATURATED).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_SATURATED).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_FAT).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_TRANS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_IRON).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_FIBER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_FOLATE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_POTASSIUM).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_MAGNESIUM).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_SODIUM).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_ENERGY).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_NIACIN).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_PHOSPHORUS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_PROTEIN).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_RIBOFLAVIN).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_SUGARS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_THIAMIN).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_E).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_A).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_12).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_B_6).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_C).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_D).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_NUTRITIONAL_VITAMIN_K).getValue(String.class),
                                context);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                new SupportHandlingDatabaseError(this.getClass().getSimpleName(), databaseError);
            }
        });
    }
}

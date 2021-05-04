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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipePurchaseModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTablePurchase;

import java.util.ArrayList;
import java.util.List;

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

/* ************************************************************************************************/
/* *** Treat Firebase Maintenance: RECIPE TIPS
/* ************************************************************************************************/
public class RecipeDatabaseFirebaseTablePurchase {

    private static DatabaseReference databaseReference;
    private static Query firebaseQuery;

    /* ********************************************************************************************/
    /* *** Treat Reset
    /* ********************************************************************************************/
    public static boolean RecipePurchaseFirebaseReset(Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_PURCHASE);
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
    public static void RecipePurchaseFirebaseDeleteSingle() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_PURCHASE);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER).equalTo(sCurrentRecipePurchaseRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String number = snapshot.child(FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER).getValue(String.class);

                        assert number != null;
                        if (number.equals(sCurrentRecipePurchaseIngredientNumber)) {

                            snapshot.getRef().removeValue();

                        }
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
    /* *** Treat Delete
    /* ********************************************************************************************/
    public static void RecipePurchaseFirebaseDeleteAll() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_PURCHASE);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER).equalTo(sCurrentRecipeNumber);

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
    public static boolean RecipePurchaseFirebaseUpdate(

            String recipe_purchase_recipe_name,
            String recipe_purchase_recipe_number,
            String recipe_purchase_ingredient_name,
            String recipe_purchase_ingredient_number,
            String recipe_purchase_ingredient_amount,
            String recipe_purchase_ingredient_unit,
            String firebaseKey,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_PURCHASE);

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_RECIPE_NAME).setValue(recipe_purchase_recipe_name);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER).setValue(recipe_purchase_recipe_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_INGREDIENT_NAME).setValue(recipe_purchase_ingredient_name);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER).setValue(recipe_purchase_ingredient_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT).setValue(recipe_purchase_ingredient_amount);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT).setValue(recipe_purchase_ingredient_unit);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);
            return false;

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Create
    /* ********************************************************************************************/
    public static boolean RecipePurchaseFirebaseCreate(

            String recipe_purchase_recipe_name,
            String recipe_purchase_recipe_number,
            String recipe_purchase_ingredient_name,
            String recipe_purchase_ingredient_number,
            String recipe_purchase_ingredient_amount,
            String recipe_purchase_ingredient_unit,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_PURCHASE);

            String firebaseKey = databaseReference.push().getKey();

            assert firebaseKey != null;

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_RECIPE_NAME).setValue(recipe_purchase_recipe_name);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER).setValue(recipe_purchase_recipe_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_INGREDIENT_NAME).setValue(recipe_purchase_ingredient_name);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER).setValue(recipe_purchase_ingredient_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT).setValue(recipe_purchase_ingredient_amount);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT).setValue(recipe_purchase_ingredient_unit);

            return true;

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);
            return false;

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Get Recipe Detail Tips List
    /* ********************************************************************************************/
    public static List<RecipePurchaseModel> RecipeListDetailPurchaseGetList() {

        final List<RecipePurchaseModel> list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_PURCHASE);

        databaseReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipePurchaseModel snapshotDetails = snapshot.getValue(RecipePurchaseModel.class);
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
    public static void RecipePurchaseTransferFromFirebase(final Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_PURCHASE);

        Query firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER).equalTo(sCurrentRecipePurchaseRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeDatabaseSQLiteTablePurchase.RecipePurchaseSQLiteInsert(
                                context,
                                snapshot.child(FIELD_RECIPE_PURCHASE_RECIPE_NAME).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_PURCHASE_RECIPE_NUMBER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_PURCHASE_INGREDIENT_NAME).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_PURCHASE_INGREDIENT_NUMBER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_PURCHASE_INGREDIENT_AMOUNT).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_PURCHASE_INGREDIENT_UNIT).getValue(String.class));

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

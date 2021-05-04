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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeTipsModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableTips;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_TIPS_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_TIPS_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_TIPS_TEXT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_TIPS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeTipNumber;

/* ************************************************************************************************/
/* *** Treat Firebase Maintenance: RECIPE TIPS
/* ************************************************************************************************/
public class RecipeDatabaseFirebaseTableTips {

    private static DatabaseReference databaseReference;
    private static Query firebaseQuery;

    /* ********************************************************************************************/
    /* *** Treat Reset
    /* ********************************************************************************************/
    public static boolean RecipeTipsFirebaseReset(Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_TIPS);
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
    public static void RecipeTipsFirebaseDeleteSingle() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_TIPS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_TIPS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String number = snapshot.child(FIELD_RECIPE_TIPS_NUMBER).getValue(String.class);

                        assert number != null;
                        if (number.equals(sCurrentRecipeTipNumber)) {

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
    public static void RecipeTipsFirebaseDeleteAll() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_TIPS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_TIPS_RECIPE).equalTo(sCurrentRecipeNumber);

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
    public static boolean RecipeTipsFirebaseUpdate(
            String recipe_tips_recipe,
            String recipe_tips_number,
            String recipe_tips_text,
            String firebaseKey,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_TIPS);

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_TIPS_RECIPE).setValue(recipe_tips_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_TIPS_NUMBER).setValue(recipe_tips_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_TIPS_TEXT).setValue(recipe_tips_text);

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
    public static void RecipeTipsFirebaseUpdateSingle(
            final String recipe_tips_text,
            Context context) {


        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_TIPS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_TIPS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String number = snapshot.child(FIELD_RECIPE_TIPS_NUMBER).getValue(String.class);

                        assert number != null;
                        if (number.equals(sCurrentRecipeTipNumber)) {

                            HashMap hashMap = new HashMap();
                            hashMap.put(FIELD_RECIPE_TIPS_TEXT, recipe_tips_text);
                            databaseReference.updateChildren(hashMap);

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
    /* *** Treat Create
    /* ********************************************************************************************/
    public static void RecipeTipsFirebaseCreate(
            Context context,
            String recipe_tips_recipe,
            String recipe_tips_number,
            String recipe_tips_text) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_TIPS);

            String firebaseKey = databaseReference.push().getKey();

            assert firebaseKey != null;

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_TIPS_RECIPE).setValue(recipe_tips_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_TIPS_NUMBER).setValue(recipe_tips_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_TIPS_TEXT).setValue(recipe_tips_text);

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Get Recipe Detail Tips List
    /* ********************************************************************************************/
    public static List<RecipeTipsModel> RecipeListDetailTipsGetList() {

        final List<RecipeTipsModel> list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_TIPS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_TIPS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeTipsModel snapshotDetails = snapshot.getValue(RecipeTipsModel.class);
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
    public static void RecipeTipsTransferFromFirebase(final Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_TIPS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_TIPS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeDatabaseSQLiteTableTips.RecipeTipsSQLiteInsert(
                                context,
                                snapshot.child(FIELD_RECIPE_TIPS_RECIPE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_TIPS_NUMBER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_TIPS_TEXT).getValue(String.class));

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

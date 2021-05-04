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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeCommentsModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableComments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_TEXT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_COMMENTS_USER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_AMOUNT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_COMMENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_INGREDIENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCommentNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;

/* ************************************************************************************************/
/* *** Treat Firebase Maintenance: RECIPE COMMENTS
/* ************************************************************************************************/
public class RecipeDatabaseFirebaseTableComments {

    private static DatabaseReference databaseReference;
    private static Query firebaseQuery;
    private static final List<RecipeCommentsModel> list = new ArrayList<>();

    /* ********************************************************************************************/
    /* *** Treat Reset
    /* ********************************************************************************************/
    public static boolean RecipeCommentsFirebaseReset(Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_COMMENTS);
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
    public static void RecipeCommentsFirebaseDeleteSingle() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_COMMENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_COMMENTS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String number = snapshot.child(FIELD_RECIPE_COMMENTS_NUMBER).getValue(String.class);

                        assert number != null;
                        if (number.equals(sCurrentRecipeCommentNumber)) {

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
    public static void RecipeCommentsFirebaseDeleteAll() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_COMMENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_COMMENTS_RECIPE).equalTo(sCurrentRecipeNumber);

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
    public static boolean RecipeCommentsFirebaseUpdate(
            String recipe_comments_recipe,
            String recipe_comments_number,
            String recipe_comments_user,
            String recipe_comments_text,
            String firebaseKey,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_COMMENTS);

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_COMMENTS_RECIPE).setValue(recipe_comments_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_COMMENTS_NUMBER).setValue(recipe_comments_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_COMMENTS_USER).setValue(recipe_comments_user);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_COMMENTS_TEXT).setValue(recipe_comments_text);

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
    public static void RecipeCommentsFirebaseUpdateSingle(
            final String recipe_comments_text,
            final Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_COMMENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_COMMENTS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String number = dataSnapshot.child(FIELD_RECIPE_COMMENTS_NUMBER).getValue(String.class);

                    assert number != null;
                    if (number.equals(sCurrentRecipeCommentNumber)) {

                        HashMap hashMap = new HashMap();
                        hashMap.put(FIELD_RECIPE_COMMENTS_TEXT, recipe_comments_text);
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
    /* *** Treat Update
    /* ********************************************************************************************/
    public static void RecipeIngredientsFirebaseUpdateSingle(
            final String recipe_ingredients_name,
            final String recipe_ingredients_amount,
            Context context) {


        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INGREDIENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INGREDIENTS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String number = snapshot.child(FIELD_RECIPE_INGREDIENTS_NUMBER).getValue(String.class);

                        assert number != null;
                        if (number.equals(sCurrentRecipeInstructionNumber)) {

                            HashMap hashMap = new HashMap();
                            hashMap.put(FIELD_RECIPE_INGREDIENTS_NAME, recipe_ingredients_name);
                            hashMap.put(FIELD_RECIPE_INGREDIENTS_AMOUNT, recipe_ingredients_amount);
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
    public static void RecipeCommentsFirebaseCreate(
            Context context,
            String recipe_comments_recipe,
            String recipe_comments_number,
            String recipe_comments_user,
            String recipe_comments_text) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_COMMENTS);

            String firebaseKey = databaseReference.push().getKey();

            assert firebaseKey != null;

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_COMMENTS_RECIPE).setValue(recipe_comments_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_COMMENTS_NUMBER).setValue(recipe_comments_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_COMMENTS_USER).setValue(recipe_comments_user);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_COMMENTS_TEXT).setValue(recipe_comments_text);

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Get Recipe Detail Comments List
    /* ********************************************************************************************/
    public static List<RecipeCommentsModel> RecipeListDetailCommentsGetList() {

        list.clear();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_COMMENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_COMMENTS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeCommentsModel snapshotDetails = snapshot.getValue(RecipeCommentsModel.class);
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
    public static void RecipeCommentsTransferFromFirebase(final Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_COMMENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_COMMENTS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeDatabaseSQLiteTableComments.RecipeCommentsSQLiteInsert(
                                context,
                                snapshot.child(FIELD_RECIPE_COMMENTS_RECIPE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_COMMENTS_NUMBER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_COMMENTS_USER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_COMMENTS_TEXT).getValue(String.class));

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

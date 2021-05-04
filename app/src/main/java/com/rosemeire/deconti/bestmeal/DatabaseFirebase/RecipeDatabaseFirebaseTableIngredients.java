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
import android.os.Environment;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingDatabaseError;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingExceptionError;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeIngredientsModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableIngredients;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_AMOUNT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_PHOTO;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INGREDIENTS_UNIT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_INGREDIENTS_1;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_INGREDIENTS_2;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_INGREDIENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIngredientNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIngredientPhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;

/* ************************************************************************************************/
/* *** Treat Firebase Maintenance: RECIPE INGREDIENTS
/* ************************************************************************************************/
public class RecipeDatabaseFirebaseTableIngredients {

    private static DatabaseReference databaseReference;
    private static Query firebaseQuery;

    /* ********************************************************************************************/
    /* *** Treat Reset
    /* ********************************************************************************************/
    public static boolean RecipeIngredientsFirebaseReset(Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INGREDIENTS);
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
    public static void RecipeIngredientsFirebaseDeleteSingle() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INGREDIENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INGREDIENTS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String number = snapshot.child(FIELD_RECIPE_INGREDIENTS_NUMBER).getValue(String.class);

                        assert number != null;
                        if (number.equals(sCurrentRecipeIngredientNumber)) {

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
    public static void RecipeIngredientsFirebaseDeleteAll() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INGREDIENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INGREDIENTS_RECIPE).equalTo(sCurrentRecipeNumber);

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
    public static boolean RecipeIngredientsFirebaseUpdate(

            String recipe_ingredients_recipe,
            String recipe_ingredients_number,
            String recipe_ingredients_amount,
            String recipe_ingredients_unit,
            String recipe_ingredients_name,
            String recipe_ingredients_photo,
            String firebaseKey,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INGREDIENTS);

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_RECIPE).setValue(recipe_ingredients_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_NUMBER).setValue(recipe_ingredients_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_AMOUNT).setValue(recipe_ingredients_amount);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_UNIT).setValue(recipe_ingredients_unit);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_NAME).setValue(recipe_ingredients_name);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_PHOTO).setValue(recipe_ingredients_photo);

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
    public static void RecipeIngredientsFirebaseUpdateSingle(
            final String recipe_ingredients_name,
            final String recipe_ingredients_amount,
            final String recipe_ingredients_unit,
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
                            hashMap.put(FIELD_RECIPE_INGREDIENTS_UNIT, recipe_ingredients_unit);
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
    public static void RecipeIngredientsFirebaseCreate(
            String recipe_ingredients_recipe,
            String recipe_ingredients_number,
            String recipe_ingredients_amount,
            String recipe_ingredients_unit,
            String recipe_ingredients_name,
            String recipe_ingredients_photo,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INGREDIENTS);

            String firebaseKey = databaseReference.push().getKey();

            assert firebaseKey != null;

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_RECIPE).setValue(recipe_ingredients_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_NUMBER).setValue(recipe_ingredients_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_AMOUNT).setValue(recipe_ingredients_amount);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_UNIT).setValue(recipe_ingredients_unit);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_NAME).setValue(recipe_ingredients_name);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INGREDIENTS_PHOTO).setValue(recipe_ingredients_photo);

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Get Recipe Detail Ingredients List
    /* ********************************************************************************************/
    public static List<RecipeIngredientsModel> RecipeListDetailIngredientsGetList() {

        final List<RecipeIngredientsModel> list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INGREDIENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INGREDIENTS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeIngredientsModel snapshotDetails = snapshot.getValue(RecipeIngredientsModel.class);
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
    public static void RecipeIngredientsTransferFromFirebase(final Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INGREDIENTS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INGREDIENTS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeDatabaseSQLiteTableIngredients.RecipeIngredientsSQLiteInsert(
                                snapshot.child(FIELD_RECIPE_INGREDIENTS_RECIPE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_INGREDIENTS_NUMBER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_INGREDIENTS_AMOUNT).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_INGREDIENTS_UNIT).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_INGREDIENTS_NAME).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_INGREDIENTS_PHOTO).getValue(String.class),
                                context);

                        sCurrentRecipeIngredientPhoto = snapshot.child(FIELD_RECIPE_INGREDIENTS_PHOTO).getValue(String.class);
                        downloadRecipePhotoInternalStorage(context);

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
    /* *** Download image to internal storage
    /* ********************************************************************************************/
    private static void downloadRecipePhotoInternalStorage(final Context context) {

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        try {

            File directory = context.getDir(PATH_INGREDIENTS_2, Context.MODE_PRIVATE);
            final File newFile = new File(directory, sCurrentRecipeIngredientPhoto + FILE_TYPE);

            firebaseStorage.getReference().child(PATH_INGREDIENTS_1).child(sCurrentRecipeIngredientPhoto + FILE_TYPE).getFile(newFile).

                    addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                            try {
                                //noinspection ResultOfMethodCallIgnored
                                newFile.createNewFile();

                            } catch (IOException error) {

                                String ClassName = String.class.getName();
                                new SupportHandlingExceptionError(ClassName, error, context);

                            }

                        }

                    }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception error) {
                    String ClassName = String.class.getName();
                    new SupportHandlingExceptionError(ClassName, error, context);

                }

            });

        } catch (Exception error) {
            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }
    }

    /* ********************************************************************************************/
    /* *** Download image to device
    /* ********************************************************************************************/
    private static void downloadRecipePhoto() {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(PATH_INGREDIENTS_1).child(sCurrentRecipeIngredientPhoto + FILE_TYPE);

        File rootPath = new File(Environment.getExternalStorageDirectory(), PATH_INGREDIENTS_1);

        if (!rootPath.exists()) {
            //noinspection ResultOfMethodCallIgnored
            rootPath.mkdirs();
        }

        final File localFile = new File(rootPath, sCurrentRecipeIngredientPhoto + FILE_TYPE);

        storageReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {

            }
        });
    }
}

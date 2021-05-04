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
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingDatabaseError;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingExceptionError;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeInstructionsModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableInstructions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_PHOTO;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_RECIPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_INSTRUCTIONS_TEXT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_INSTRUCTIONS_1;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_INSTRUCTIONS_2;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_INSTRUCTIONS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionPhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;

/* ************************************************************************************************/
/* *** Treat Firebase Maintenance: RECIPE INSTRUCTIONS
/* ************************************************************************************************/
public class RecipeDatabaseFirebaseTableInstructions {

    private static DatabaseReference databaseReference;
    private static Query firebaseQuery;

    /* ********************************************************************************************/
    /* *** Treat Reset
    /* ********************************************************************************************/
    public static boolean RecipeInstructionsFirebaseReset(Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INSTRUCTIONS);
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
    public static void RecipeInstructionsFirebaseDeleteSingle() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INSTRUCTIONS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INSTRUCTIONS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String number = snapshot.child(FIELD_RECIPE_INSTRUCTIONS_NUMBER).getValue(String.class);

                        assert number != null;
                        if (number.equals(sCurrentRecipeInstructionNumber)) {

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
    public static void RecipeInstructionsFirebaseDeleteAll() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INSTRUCTIONS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INSTRUCTIONS_RECIPE).equalTo(sCurrentRecipeNumber);

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
    public static boolean RecipeInstructionsFirebaseUpdate(
            String recipe_instructions_recipe,
            String recipe_instructions_number,
            String recipe_instructions_text,
            String recipe_instructions_photo,
            String firebaseKey,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INSTRUCTIONS);

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INSTRUCTIONS_RECIPE).setValue(recipe_instructions_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INSTRUCTIONS_NUMBER).setValue(recipe_instructions_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INSTRUCTIONS_TEXT).setValue(recipe_instructions_text);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INSTRUCTIONS_PHOTO).setValue(recipe_instructions_photo);

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
    public static void RecipeInstructionsFirebaseUpdateSingle(
            final String recipe_instructions_text,
            Context context) {


        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INSTRUCTIONS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INSTRUCTIONS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        String number = snapshot.child(FIELD_RECIPE_INSTRUCTIONS_NUMBER).getValue(String.class);

                        assert number != null;
                        if (number.equals(sCurrentRecipeInstructionNumber)) {

                            HashMap hashMap = new HashMap();
                            hashMap.put(FIELD_RECIPE_INSTRUCTIONS_TEXT, recipe_instructions_text);
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
    public static void RecipeInstructionsFirebaseCreate(
            Context context,
            String recipe_instructions_recipe,
            String recipe_instructions_number,
            String recipe_instructions_text,
            String recipe_instructions_photo) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INSTRUCTIONS);

            String firebaseKey = databaseReference.push().getKey();

            assert firebaseKey != null;

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INSTRUCTIONS_RECIPE).setValue(recipe_instructions_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INSTRUCTIONS_NUMBER).setValue(recipe_instructions_number);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INSTRUCTIONS_TEXT).setValue(recipe_instructions_text);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_INSTRUCTIONS_PHOTO).setValue(recipe_instructions_photo);

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }
    }

    /* ********************************************************************************************/
    /* *** Treat Get Recipe Detail Instructions List
    /* ********************************************************************************************/
    public static List<RecipeInstructionsModel> RecipeListDetailInstructionsGetList() {

        final List<RecipeInstructionsModel> list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INSTRUCTIONS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INSTRUCTIONS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeInstructionsModel snapshotDetails = snapshot.getValue(RecipeInstructionsModel.class);
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
    public static void RecipeInstructionsTransferFromFirebase(final Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_INSTRUCTIONS);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_INSTRUCTIONS_RECIPE).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeDatabaseSQLiteTableInstructions.RecipeInstructionsSQLiteInsert(
                                context,
                                snapshot.child(FIELD_RECIPE_INSTRUCTIONS_RECIPE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_INSTRUCTIONS_NUMBER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_INSTRUCTIONS_TEXT).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_INSTRUCTIONS_PHOTO).getValue(String.class));

                        sCurrentRecipeInstructionPhoto = snapshot.child(FIELD_RECIPE_INSTRUCTIONS_PHOTO).getValue(String.class);
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

            File directory = context.getDir(PATH_INSTRUCTIONS_2, Context.MODE_PRIVATE);
            final File newFile = new File(directory, sCurrentRecipeInstructionPhoto + FILE_TYPE);

            firebaseStorage.getReference().child(PATH_INSTRUCTIONS_1).child(sCurrentRecipeInstructionPhoto + FILE_TYPE).getFile(newFile).

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
}

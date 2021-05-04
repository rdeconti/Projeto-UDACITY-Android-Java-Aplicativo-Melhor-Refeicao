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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeHeaderModel;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeStatisticsModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableHeader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_AUTHOR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_CALORIES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_CUISENES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_DIET;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_DIFFICULT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_HEALTH;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_LANGUAGE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NAME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_APPROVALS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_COMMENTS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_DISLIKES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_LIKES;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_PRINTINGS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_REPROVALS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_SHARINGS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_OCCASION;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_PHOTO;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_PRICE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_RATING_DISPLAY;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_RATING_VALUE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_SERVINGS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_STATUS;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_TIME;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_HEADER_UPDATE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_RECIPES_1;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_RECIPES_2;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.TABLE_RECIPE_HEADER;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_APPROVED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_COMMENTED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_DISLIKED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_LIKED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_NONE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_PRINTED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_REPROVED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_SHARED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_VISUALIZED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeRating;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusApproved;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusCommented;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusDisliked;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusLiked;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusPrinted;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusReproved;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusShared;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusVisualized;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sDesiredRecipeStatus;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsLanguage;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromFirebase;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeRatingBestLevel;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeStatisticsCalculation;

/* ************************************************************************************************/
/* *** Treat Firebase Maintenance: RECIPE HEADER
/* ************************************************************************************************/
public class RecipeDatabaseFirebaseTableHeader {

    private static DatabaseReference databaseReference;
    private static Query firebaseQuery;
    private static String mLanguage;

    private static String mCurrentRecipeNumberApprovals = null;
    private static String mCurrentRecipeNumberReprovals = null;
    private static String mCurrentRecipeNumberLikes = null;
    private static String mCurrentRecipeNumberDislikes = null;
    private static String mCurrentRecipeNumberComments = null;
    private static String mCurrentRecipeNumberSharings = null;
    private static String mCurrentRecipeNumberPrintings = null;
    private static String mCurrentRecipeNumberVisualizations = null;

    /* ********************************************************************************************/
    /* *** Treat Reset
    /* ********************************************************************************************/
    public static boolean RecipeHeaderFirebaseReset(Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);
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
    public static boolean RecipeHeaderFirebaseDelete(String firebaseKey, Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);
            databaseReference.child(firebaseKey).removeValue();
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
    public static void RecipeHeaderFirebaseDeleteAll() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_NUMBER).equalTo(sCurrentRecipeNumber);

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
    public static boolean RecipeHeaderFirebaseUpdate(
            final String recipe_header_recipe,
            final String recipe_header_name,
            final String recipe_header_author,
            final String recipe_header_update,
            final String recipe_header_photo,
            final String recipe_header_status,
            final String recipe_header_language,
            final String recipe_header_likes,
            final String recipe_header_dislikes,
            final String recipe_header_approvals,
            final String recipe_header_reprovals,
            final String recipe_header_visualizations,
            final String recipe_header_printings,
            final String recipe_header_comments,
            final String recipe_header_sharings,
            final String recipe_header_rating_value,
            final String recipe_header_rating_display,
            final String recipe_header_diet,
            final String recipe_header_calories,
            final String recipe_header_cuisenes,
            final String recipe_header_health,
            final String recipe_header_occasion,
            final String recipe_header_servings,
            final String recipe_header_price,
            final String recipe_header_time,
            final String recipe_header_difficult,
            final String firebaseKey,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER).setValue(recipe_header_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NAME).setValue(recipe_header_name);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_AUTHOR).setValue(recipe_header_author);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_UPDATE).setValue(recipe_header_update);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_PHOTO).setValue(recipe_header_photo);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_STATUS).setValue(recipe_header_status);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_LANGUAGE).setValue(recipe_header_language);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_LIKES).setValue(recipe_header_likes);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_DISLIKES).setValue(recipe_header_dislikes);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_APPROVALS).setValue(recipe_header_approvals);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_REPROVALS).setValue(recipe_header_reprovals);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS).setValue(recipe_header_visualizations);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS).setValue(recipe_header_printings);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_COMMENTS).setValue(recipe_header_comments);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_SHARINGS).setValue(recipe_header_sharings);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_RATING_VALUE).setValue(recipe_header_rating_value);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_RATING_DISPLAY).setValue(recipe_header_rating_display);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_DIET).setValue(recipe_header_diet);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_CALORIES).setValue(recipe_header_calories);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_CUISENES).setValue(recipe_header_cuisenes);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_HEALTH).setValue(recipe_header_health);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_OCCASION).setValue(recipe_header_occasion);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_SERVINGS).setValue(recipe_header_servings);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_PRICE).setValue(recipe_header_price);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_TIME).setValue(recipe_header_time);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_DIFFICULT).setValue(recipe_header_difficult);

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
    public static void RecipeHeaderFirebaseUpdateSingle(
            final String recipe_header_name,
            final String recipe_header_update,
            final String recipe_header_photo,
            final String recipe_header_diet,
            final String recipe_header_calories,
            final String recipe_header_cuisenes,
            final String recipe_header_health,
            final String recipe_header_occasion,
            final String recipe_header_servings,
            final String recipe_header_price,
            final String recipe_header_time,
            final String recipe_header_difficult) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_NUMBER).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        snapshot.child(FIELD_RECIPE_HEADER_NAME).getRef().setValue(recipe_header_name);
                        snapshot.child(FIELD_RECIPE_HEADER_UPDATE).getRef().setValue(recipe_header_update);
                        snapshot.child(FIELD_RECIPE_HEADER_PHOTO).getRef().setValue(recipe_header_photo);
                        snapshot.child(FIELD_RECIPE_HEADER_DIET).getRef().setValue(recipe_header_diet);
                        snapshot.child(FIELD_RECIPE_HEADER_CALORIES).getRef().setValue(recipe_header_calories);
                        snapshot.child(FIELD_RECIPE_HEADER_CUISENES).getRef().setValue(recipe_header_cuisenes);
                        snapshot.child(FIELD_RECIPE_HEADER_HEALTH).getRef().setValue(recipe_header_health);
                        snapshot.child(FIELD_RECIPE_HEADER_OCCASION).getRef().setValue(recipe_header_occasion);
                        snapshot.child(FIELD_RECIPE_HEADER_SERVINGS).getRef().setValue(recipe_header_servings);
                        snapshot.child(FIELD_RECIPE_HEADER_PRICE).getRef().setValue(recipe_header_price);
                        snapshot.child(FIELD_RECIPE_HEADER_TIME).getRef().setValue(recipe_header_time);
                        snapshot.child(FIELD_RECIPE_HEADER_DIFFICULT).getRef().setValue(recipe_header_difficult);

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
    public static void RecipeHeaderFirebaseCreate(
            String recipe_header_recipe,
            String recipe_header_name,
            String recipe_header_author,
            String recipe_header_update,
            String recipe_header_photo,
            String recipe_header_status,
            String recipe_header_language,
            String recipe_header_likes,
            String recipe_header_dislikes,
            String recipe_header_approvals,
            String recipe_header_reprovals,
            String recipe_header_visualizations,
            String recipe_header_printings,
            String recipe_header_comments,
            String recipe_header_sharings,
            String recipe_header_rating_value,
            String recipe_header_rating_display,
            String recipe_header_diet,
            String recipe_header_calories,
            String recipe_header_cuisenes,
            String recipe_header_health,
            String recipe_header_occasion,
            String recipe_header_servings,
            String recipe_header_price,
            String recipe_header_time,
            String recipe_header_difficult,
            Context context) {

        try {

            databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

            String firebaseKey = databaseReference.push().getKey();

            assert firebaseKey != null;

            recipe_header_status = RECIPE_TREAT_NONE;

            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER).setValue(recipe_header_recipe);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NAME).setValue(recipe_header_name);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_AUTHOR).setValue(recipe_header_author);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_UPDATE).setValue(recipe_header_update);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_PHOTO).setValue(recipe_header_photo);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_STATUS).setValue(recipe_header_status);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_LANGUAGE).setValue(recipe_header_language);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_LIKES).setValue(recipe_header_likes);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_DISLIKES).setValue(recipe_header_dislikes);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_APPROVALS).setValue(recipe_header_approvals);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_REPROVALS).setValue(recipe_header_reprovals);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS).setValue(recipe_header_visualizations);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS).setValue(recipe_header_printings);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_COMMENTS).setValue(recipe_header_comments);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_NUMBER_SHARINGS).setValue(recipe_header_sharings);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_RATING_VALUE).setValue(recipe_header_rating_value);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_RATING_DISPLAY).setValue(recipe_header_rating_display);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_DIET).setValue(recipe_header_diet);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_CALORIES).setValue(recipe_header_calories);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_CUISENES).setValue(recipe_header_cuisenes);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_HEALTH).setValue(recipe_header_health);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_OCCASION).setValue(recipe_header_occasion);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_SERVINGS).setValue(recipe_header_servings);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_PRICE).setValue(recipe_header_price);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_TIME).setValue(recipe_header_time);
            databaseReference.child(firebaseKey).child(FIELD_RECIPE_HEADER_DIFFICULT).setValue(recipe_header_difficult);

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }
    }

    /* ********************************************************************************************/
    /* *** Treat get recipes first navigation model - HEADER
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderListGetHeader() {

        final List<RecipeHeaderModel> list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_NUMBER).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeHeaderModel snapshotDetails = snapshot.getValue(RecipeHeaderModel.class);
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
    /* *** Treat get recipes first navigation model - HEADER
    /* ********************************************************************************************/
    public static List<RecipeStatisticsModel> RecipeHeaderListGetStatistics() {

        final List<RecipeStatisticsModel> list = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_NUMBER).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeStatisticsModel snapshotDetails = snapshot.getValue(RecipeStatisticsModel.class);
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
    /* *** Treat get recipes first navigation model - ALL recipes
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderListGetAll() {

        final List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_LANGUAGE);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        mLanguage = snapshot.child(FIELD_RECIPE_HEADER_LANGUAGE).getValue(String.class);

                        if (mLanguage != null) {

                            if (!mLanguage.equals(sPreferredSettingsLanguage)) {

                                RecipeHeaderModel snapshotDetails = snapshot.getValue(RecipeHeaderModel.class);
                                list.add(snapshotDetails);

                            }

                        }
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
    /* *** Treat get recipes first navigation model - BEST recipes
    /* ********************************************************************************************/
    public static List<RecipeHeaderModel> RecipeHeaderListGetBest() {

        final List<RecipeHeaderModel> list = new ArrayList<>();

        list.clear();

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_LANGUAGE);

        firebaseQuery.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        mLanguage = snapshot.child(FIELD_RECIPE_HEADER_LANGUAGE).getValue(String.class);

                        sCurrentRecipeRating = snapshot.child(FIELD_RECIPE_HEADER_RATING_VALUE).getValue(String.class);

                        if (sCurrentRecipeRating != null) {

                            if (Integer.valueOf(sCurrentRecipeRating) >= Integer.valueOf(sRecipeRatingBestLevel)) {

                                if (!mLanguage.equals(sPreferredSettingsLanguage)) {

                                    RecipeHeaderModel snapshotDetails = snapshot.getValue(RecipeHeaderModel.class);
                                    list.add(snapshotDetails);

                                }
                            }
                        }
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
    public static void RecipeHeaderTransferFromFirebase(final Context context) {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_NUMBER).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        RecipeDatabaseSQLiteTableHeader.RecipeHeaderSQLiteInsert(
                                context,
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NAME).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_AUTHOR).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_UPDATE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_PHOTO).getValue(String.class),
                                sCurrentRecipeStatusApproved,
                                sCurrentRecipeStatusReproved,
                                sCurrentRecipeStatusLiked,
                                sCurrentRecipeStatusDisliked,
                                sCurrentRecipeStatusCommented,
                                sCurrentRecipeStatusPrinted,
                                sCurrentRecipeStatusShared,
                                sCurrentRecipeStatusVisualized,
                                snapshot.child(FIELD_RECIPE_HEADER_LANGUAGE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER_APPROVALS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER_REPROVALS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER_LIKES).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER_DISLIKES).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER_COMMENTS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER_SHARINGS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_RATING_VALUE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_RATING_DISPLAY).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_DIET).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_CALORIES).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_CUISENES).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_HEALTH).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_OCCASION).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_SERVINGS).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_PRICE).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_TIME).getValue(String.class),
                                snapshot.child(FIELD_RECIPE_HEADER_DIFFICULT).getValue(String.class));

                        sCurrentRecipePhoto = snapshot.child(FIELD_RECIPE_HEADER_PHOTO).getValue(String.class);
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

            File directory = context.getDir(PATH_RECIPES_2, Context.MODE_PRIVATE);
            final File newFile = new File(directory, sCurrentRecipePhoto + FILE_TYPE);

            firebaseStorage.getReference().child(PATH_RECIPES_1).child(sCurrentRecipePhoto + FILE_TYPE).getFile(newFile).

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
    /* *** Get Recipe Header Status
    /* ********************************************************************************************/
    public static ArrayList<String> RecipeHeaderGetRecipeNumbers(Context context) {

        final ArrayList recipeNumbers = new ArrayList ();

        recipeNumbers.clear();

        recipeNumbers.add(0, 5);
        recipeNumbers.add(1, 10);
        recipeNumbers.add(2, 23);
        recipeNumbers.add(3, 10);
        recipeNumbers.add(4, 45);
        recipeNumbers.add(5, 22);
        recipeNumbers.add(6, 14);
        recipeNumbers.add(7, 55);

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_NUMBER).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        mCurrentRecipeNumberApprovals = snapshot.child(FIELD_RECIPE_HEADER_NUMBER_APPROVALS).getValue(String.class);
                        mCurrentRecipeNumberReprovals = snapshot.child(FIELD_RECIPE_HEADER_NUMBER_REPROVALS).getValue(String.class);
                        mCurrentRecipeNumberLikes = snapshot.child(FIELD_RECIPE_HEADER_NUMBER_LIKES).getValue(String.class);
                        mCurrentRecipeNumberDislikes = snapshot.child(FIELD_RECIPE_HEADER_NUMBER_DISLIKES).getValue(String.class);
                        mCurrentRecipeNumberPrintings = snapshot.child(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS).getValue(String.class);
                        mCurrentRecipeNumberSharings = snapshot.child(FIELD_RECIPE_HEADER_NUMBER_SHARINGS).getValue(String.class);
                        mCurrentRecipeNumberComments = snapshot.child(FIELD_RECIPE_HEADER_NUMBER_COMMENTS).getValue(String.class);
                        mCurrentRecipeNumberVisualizations = snapshot.child(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS).getValue(String.class);

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                new SupportHandlingDatabaseError(this.getClass().getSimpleName(), databaseError);
            }
        });

        recipeNumbers.set(0, mCurrentRecipeNumberApprovals);
        recipeNumbers.set(1, mCurrentRecipeNumberReprovals);
        recipeNumbers.set(2, mCurrentRecipeNumberLikes);
        recipeNumbers.set(3, mCurrentRecipeNumberDislikes);
        recipeNumbers.set(4, mCurrentRecipeNumberPrintings);
        recipeNumbers.set(5, mCurrentRecipeNumberSharings);
        recipeNumbers.set(6, mCurrentRecipeNumberComments);
        recipeNumbers.set(7, mCurrentRecipeNumberVisualizations);

        return recipeNumbers;

    }

    /* ********************************************************************************************/
    /* * Treat update header status
    /* ********************************************************************************************/
    public static void RecipeHeaderUpdateStatistics() {

        databaseReference = FirebaseDatabase.getInstance().getReference(TABLE_RECIPE_HEADER);

        firebaseQuery = databaseReference.orderByChild(FIELD_RECIPE_HEADER_NUMBER).equalTo(sCurrentRecipeNumber);

        firebaseQuery.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        sRecipeKeyFromFirebase = snapshot.getKey();

                        switch (sDesiredRecipeStatus) {

                            case RECIPE_TREAT_APPROVED:
                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_APPROVALS).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation + 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_APPROVALS).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_REPROVALS).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation - 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_REPROVALS).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                break;


                            case RECIPE_TREAT_REPROVED:
                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_REPROVALS).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation + 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_REPROVALS).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_APPROVALS).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation - 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_APPROVALS).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                break;

                            case RECIPE_TREAT_LIKED:
                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_LIKES).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation + 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_LIKES).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_DISLIKES).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation - 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_DISLIKES).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                break;

                            case RECIPE_TREAT_DISLIKED:
                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_DISLIKES).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation + 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_DISLIKES).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_LIKES).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation - 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_LIKES).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                break;

                            case RECIPE_TREAT_COMMENTED:
                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_COMMENTS).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation + 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_COMMENTS).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                break;

                            case RECIPE_TREAT_PRINTED:
                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation + 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_PRINTINGS).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                break;

                            case RECIPE_TREAT_SHARED:
                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_SHARINGS).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation + 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_SHARINGS).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                break;

                            case RECIPE_TREAT_VISUALIZED:
                                sRecipeStatisticsCalculation = Integer.parseInt(Objects.requireNonNull(snapshot.child(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS).getValue(String.class)));
                                sRecipeStatisticsCalculation = sRecipeStatisticsCalculation + 1;
                                databaseReference.child(sRecipeKeyFromFirebase).child(FIELD_RECIPE_HEADER_NUMBER_VISUALIZATIONS).setValue(String.valueOf(sRecipeStatisticsCalculation));

                                break;
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
}

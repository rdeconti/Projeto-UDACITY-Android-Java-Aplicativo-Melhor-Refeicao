package com.rosemeire.deconti.bestmeal.RecipeSecondNavigation;

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
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckNetworkAvailability;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeIngredientsModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTablePurchase;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceIngredientsActivity;
import com.rosemeire.deconti.bestmeal.RecipeWidgetPurchaseList.RecipeWidgetAppWidgetProvider;
import com.rosemeire.deconti.bestmeal.RecipeWidgetPurchaseList.RecipeWidgetContract;

import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_U;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FIELD_RECIPE_PURCHASE_WIDGET_TEXT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_INGREDIENTS_1;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeAuthor;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIngredientAmount;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIngredientNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIngredientPhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIngredientText;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIngredientUnit;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeName;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;

/* ************************************************************************************************/
/* *** Treat recycler view
/* ************************************************************************************************/
public class RecipeSecondNavigationIngredientsRecyclerAdapter extends RecyclerView.Adapter<RecipeSecondNavigationIngredientsRecyclerAdapter.ViewHolder> {

    private final List<RecipeIngredientsModel> mValues;
    private final Context mContext;

    /* ********************************************************************************************/
    /* *** Load data to update recycler view
    /* ********************************************************************************************/
    RecipeSecondNavigationIngredientsRecyclerAdapter(List<RecipeIngredientsModel> items, RecipeSecondNavigationIngredientsFragment.OnListFragmentInteractionListener listener, Context context) {

        mValues = items;
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeSecondNavigationIngredientsFragment.OnListFragmentInteractionListener mListener = listener;
        mContext = context;
    }

    /* ********************************************************************************************/
    /* *** Recycler view OnCreate
    /* ********************************************************************************************/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_second_navigation_fragment_ingredients_item, parent, false);
        return new ViewHolder(view);
    }

    /* ********************************************************************************************/
    /* *** Update recycler view
    /* ********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // ................................................................. Get value from position
        RecipeIngredientsModel model = mValues.get(position);

        // .................................................................... Set data from holder
        holder.recipeDetailIngredientsModelItem = model;

        holder.textView_IngredientName.setText(model.getRecipe_ingredients_name());
        holder.textView_IngredientNumber.setText(model.getRecipe_ingredients_number());
        holder.textView_IngredientAmount.setText(model.getRecipe_ingredients_amount());
        holder.textView_IngredientUnit.setText(model.getRecipe_ingredients_unit());

        // ................................................................ Obtain photo from recipe
        obtainRecipePhoto(holder, mContext);

        sCurrentRecipeIngredientPhoto = model.getRecipe_ingredients_photo();
        obtainRecipePhoto(holder, mContext);

        resetButtons(holder);

        // ........................................................ Save data to maintenance classes
        sCurrentRecipeIngredientText = holder.textView_IngredientName.getText().toString().trim();
        sCurrentRecipeIngredientNumber = holder.textView_IngredientNumber.getText().toString().trim();
        sCurrentRecipeIngredientAmount = holder.textView_IngredientAmount.getText().toString().trim();
        sCurrentRecipeIngredientUnit = holder.textView_IngredientUnit.getText().toString().trim();

        /* ****************************************************************************************/
        /* *** Treat click on button PURCHASE LIST
        /* ****************************************************************************************/
        holder.imageButton_purchaseList.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                RecipeDatabaseSQLiteTablePurchase.RecipePurchaseSQLiteInsert(
                        view.getContext(),
                        sCurrentRecipeName,
                        sCurrentRecipeNumber,
                        holder.textView_IngredientName.getText().toString(),
                        holder.textView_IngredientNumber.getText().toString(),
                        holder.textView_IngredientAmount.getText().toString(),
                        holder.textView_IngredientUnit.getText().toString());

                holder.imageButton_purchaseList.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

                // ................................................................... Update Widget
                String widgetItem =
                        holder.textView_IngredientName.getText().toString().trim()
                        + " " +
                        holder.textView_IngredientAmount.getText().toString().trim()
                        + " " +
                        holder.textView_IngredientUnit.getText().toString().trim();

                ContentValues values = new ContentValues();
                values.put(FIELD_RECIPE_PURCHASE_WIDGET_TEXT, widgetItem);

                Uri uri = mContext.getContentResolver().insert(RecipeWidgetContract.WIDGET_PATH_PURCHASE_URI, values);

                RecipeWidgetAppWidgetProvider.sendRefreshBroadcast(mContext);

            }
        });

        /* ****************************************************************************************/
        /* *** Treat click on button EDIT
        /* ****************************************************************************************/
        holder.imageButton_edit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else {

                    if (sCurrentRecipeAuthor.equals(sCurrentUserFirebaseUid)) {

                        sTypeCRUD = CRUD_TYPE_U;
                        new RecipeToolMaintenanceIngredientsActivity();

                    } else {

                        new SupportMessageToast(mContext, mContext.getString(R.string.message_recipe_not_yours));

                    }
                }
            }
        });

        /* ****************************************************************************************/
        /* *** Treat click on ITEM
        /* ****************************************************************************************/
        holder.mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
    }

    /* ********************************************************************************************/
    /* *** Reset buttons color
    /* ********************************************************************************************/
    private void resetButtons(ViewHolder holder) {

        holder.imageButton_purchaseList.setEnabled(true);
        holder.imageButton_purchaseList.setClickable(true);
        holder.imageButton_purchaseList.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        holder.imageButton_purchaseList.setEnabled(true);
        holder.imageButton_purchaseList.setClickable(true);
        holder.imageButton_purchaseList.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

    }

    /* ********************************************************************************************/
    /* *** Update recipe photo
    /* ********************************************************************************************/
    private void obtainRecipePhoto(final ViewHolder holder, final Context context) {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(PATH_INGREDIENTS_1).child(sCurrentRecipeIngredientPhoto + FILE_TYPE);

        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                if (task.isSuccessful()) {
                    Glide.with(context)
                            .load(task.getResult())
                            .apply(RequestOptions.centerCropTransform())
                            .into(holder.imageView_IngredientPhoto);
                }
            }
        });

    }

    /* ********************************************************************************************/
    /* *** Get Recycler View Size
    /* ********************************************************************************************/
    @Override
    public int getItemCount() {

        return mValues.size();
    }

    /* ********************************************************************************************/
    /* *** A ViewHolder describes an item view and metadata about its place within the RecyclerView
    /* ********************************************************************************************/
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        final ImageView imageView_IngredientPhoto;

        public final ImageButton imageButton_edit;
        final ImageButton imageButton_purchaseList;

        final TextView textView_IngredientName;
        final TextView textView_IngredientNumber;
        final TextView textView_IngredientAmount;
        final TextView textView_IngredientUnit;

        RecipeIngredientsModel recipeDetailIngredientsModelItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;

            imageView_IngredientPhoto = view.findViewById(R.id.imageView_IngredientPhoto);

            imageButton_edit = view.findViewById(R.id.imageButton_edit);
            imageButton_purchaseList = view.findViewById(R.id.imageButton_purchaseList);

            textView_IngredientName = view.findViewById(R.id.textView_IngredientName);
            textView_IngredientNumber = view.findViewById(R.id.textView_IngredientNumber);
            textView_IngredientAmount = view.findViewById(R.id.textView_IngredientAmount);
            textView_IngredientUnit = view.findViewById(R.id.textView_IngredientUnit);

        }
    }
}

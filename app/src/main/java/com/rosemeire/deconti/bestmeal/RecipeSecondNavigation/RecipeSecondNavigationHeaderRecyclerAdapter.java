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

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckNetworkAvailability;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeHeaderModel;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceHeaderActivity;

import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_U;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeAuthor;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCreatedOn;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCuisine;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeDiet;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeDifficult;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIntolerance;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeOccasion;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePrice;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeServings;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeTime;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeType;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;

/* ************************************************************************************************/
/* *** Treat recycler view
/* ************************************************************************************************/
public class RecipeSecondNavigationHeaderRecyclerAdapter extends RecyclerView.Adapter<RecipeSecondNavigationHeaderRecyclerAdapter.ViewHolder> {

    private final List<RecipeHeaderModel> mValues;
    private final Context mContext;

    /* ********************************************************************************************/
    /* *** Load data to update recycler view
    /* ********************************************************************************************/
    RecipeSecondNavigationHeaderRecyclerAdapter(List<RecipeHeaderModel> items, RecipeSecondNavigationHeaderFragment.OnListFragmentInteractionListener listener, Context context) {

        mValues = items;
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeSecondNavigationHeaderFragment.OnListFragmentInteractionListener mListener = listener;
        mContext = context;
    }

    /* ********************************************************************************************/
    /* *** Recycler view OnCreate
    /* ********************************************************************************************/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_second_navigation_fragment_header_item, parent, false);
        return new ViewHolder(view);
    }

    /* ********************************************************************************************/
    /* *** Update recycler view
    /* ********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // ................................................................. Get value from position
        RecipeHeaderModel model = mValues.get(position);

        // .................................................................... Set data from holder
        holder.recipeDetailHeaderModelItem = model;

        holder.textViewValue_CreatedOn.setText(model.getRecipe_header_update());
        holder.textViewValue_Diet.setText(model.getRecipe_header_diet());
        holder.textViewValue_Type.setText(model.getRecipe_header_calories());
        holder.textViewValue_Cuisine.setText(model.getRecipe_header_cuisenes());
        holder.textViewValue_Intolerance.setText(model.getRecipe_header_health());
        holder.textViewValue_Occasion.setText(model.getRecipe_header_occasion());
        holder.textViewValue_Servings.setText(model.getRecipe_header_servings());
        holder.textViewValue_Price.setText(model.getRecipe_header_price());
        holder.textViewValue_Time.setText(model.getRecipe_header_time());
        holder.textViewValue_DifficultLevel.setText(model.getRecipe_header_difficult());

        // ........................................................ Save data to maintenance classes
        sCurrentRecipeCreatedOn =  holder.textViewValue_CreatedOn.getText().toString().trim();
        sCurrentRecipeDiet = holder.textViewValue_Diet.getText().toString().trim();
        sCurrentRecipeType = holder.textViewValue_Type.getText().toString().trim();
        sCurrentRecipeCuisine = holder.textViewValue_Cuisine.getText().toString().trim();
        sCurrentRecipeIntolerance = holder.textViewValue_Intolerance.getText().toString().trim();
        sCurrentRecipeOccasion = holder.textViewValue_Occasion.getText().toString().trim();
        sCurrentRecipeServings = holder.textViewValue_Servings.getText().toString().trim();
        sCurrentRecipePrice = holder.textViewValue_Price.getText().toString().trim();
        sCurrentRecipeTime = holder.textViewValue_Time.getText().toString().trim();
        sCurrentRecipeDifficult = holder.textViewValue_DifficultLevel.getText().toString().trim();

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
                        new RecipeToolMaintenanceHeaderActivity();

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

        public final ImageView imageView_photo;

        public final ImageButton imageButton_comment;
        public final ImageButton imageButton_share;
        public final ImageButton imageButton_approve;
        public final ImageButton imageButton_reprove;
        public final ImageButton imageButton_like;
        public final ImageButton imageButton_dislike;
        public final ImageButton imageButton_edit;
        public final ImageButton imageButton_print;

        final TextView textViewValue_CreatedOn;
        final TextView textViewValue_Diet;
        final TextView textViewValue_Type;
        final TextView textViewValue_Cuisine;
        final TextView textViewValue_Intolerance;
        final TextView textViewValue_Occasion;
        final TextView textViewValue_Servings;
        final TextView textViewValue_Price;
        final TextView textViewValue_Time;
        final TextView textViewValue_DifficultLevel;

        RecipeHeaderModel recipeDetailHeaderModelItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;

            imageView_photo = view.findViewById(R.id.imageView_recipePhoto);

            imageButton_comment = view.findViewById(R.id.imageButton_comment);
            imageButton_share = view.findViewById(R.id.imageButton_share);
            imageButton_approve = view.findViewById(R.id.imageButton_approve);
            imageButton_reprove = view.findViewById(R.id.imageButton_reprove);
            imageButton_like = view.findViewById(R.id.imageButton_like);
            imageButton_dislike = view.findViewById(R.id.imageButton_dislike);
            imageButton_edit = view.findViewById(R.id.imageButton_edit);
            imageButton_print = view.findViewById(R.id.imageButton_print);

            textViewValue_CreatedOn = view.findViewById(R.id.textViewValue_CreatedOn);
            textViewValue_Diet = view.findViewById(R.id.textViewValue_Diet);
            textViewValue_Type = view.findViewById(R.id.textViewValue_Type);
            textViewValue_Cuisine = view.findViewById(R.id.textViewValue_Cuisine);
            textViewValue_Intolerance = view.findViewById(R.id.textViewValue_Intolerance);
            textViewValue_Occasion = view.findViewById(R.id.textViewValue_Occasion);
            textViewValue_Servings = view.findViewById(R.id.textViewValue_Servings);
            textViewValue_Price = view.findViewById(R.id.textViewValue_Price);
            textViewValue_Time = view.findViewById(R.id.textViewValue_Time);
            textViewValue_DifficultLevel = view.findViewById(R.id.textViewValue_DifficultLevel);
        }
    }
}

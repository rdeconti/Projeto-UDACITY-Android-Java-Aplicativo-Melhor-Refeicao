package com.rosemeire.deconti.bestmeal.RecipeFirstNavigation;

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
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipePurchaseModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTablePurchase;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationListPurchaseFragment.OnListFragmentInteractionListener;

import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePurchaseIngredientNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePurchaseRecipeNumber;

/* ************************************************************************************************/
/* *** Treat recycler view
/* ************************************************************************************************/
public class RecipeFirstNavigationListPurchaseRecyclerAdapter extends RecyclerView.Adapter<RecipeFirstNavigationListPurchaseRecyclerAdapter.ViewHolder> {

    private final List<RecipePurchaseModel> mValues;
    private final Context mContext;

    /* ********************************************************************************************/
    /* *** Load data to update recycler view
    /* ********************************************************************************************/
    RecipeFirstNavigationListPurchaseRecyclerAdapter(List<RecipePurchaseModel> items, RecipeFirstNavigationListPurchaseFragment.OnListFragmentInteractionListener listener, Context context) {

        mValues = items;
        @SuppressWarnings("UnnecessaryLocalVariable") OnListFragmentInteractionListener mListener = listener;
        mContext = context;

    }

    /* ********************************************************************************************/
    /* *** Recycler view OnCreate
    /* ********************************************************************************************/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_first_navigation_fragment_purchase_item, parent, false);
        return new ViewHolder(view);
    }

    /* ********************************************************************************************/
    /* *** Update recycler view
    /* ********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // ................................................................. Get value from position
        RecipePurchaseModel model = mValues.get(position);
        holder.recipePurchaseModelItem = model;

        holder.recipe_purchase_recipe_name.setText(model.getRecipe_purchase_ingredients_recipe_name());
        holder.recipe_purchase_recipe_number.setText(model.getRecipe_purchase_ingredients_recipe_number());
        holder.recipe_purchase_ingredient_name.setText(model.getRecipe_purchase_ingredients_name());
        holder.recipe_purchase_ingredient_number.setText(model.getRecipe_purchase_ingredients_number());
        holder.recipe_purchase_ingredient_amount.setText(model.getRecipe_purchase_ingredients_amount());
        holder.recipe_purchase_ingredient_unit.setText(model.getRecipe_purchase_ingredients_unit());

        holder.imageButton_delete.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sCurrentRecipePurchaseRecipeNumber = holder.recipe_purchase_recipe_number.getText().toString().trim();
                sCurrentRecipePurchaseIngredientNumber = holder.recipe_purchase_ingredient_number.getText().toString().trim();

                saveDataFromCurrentRecipe(holder);
                resetButtons(holder);
                RecipeDatabaseSQLiteTablePurchase.RecipePurchaseSQLiteDeleteSingle(view.getContext());
                updateButtons(holder);

            }
        });

    }

    /* ********************************************************************************************/
    /* *** Store current recipe data
    /* ********************************************************************************************/
    private void saveDataFromCurrentRecipe(ViewHolder holder) {

        sCurrentRecipePurchaseRecipeNumber = holder.recipe_purchase_recipe_number.getText().toString();
        sCurrentRecipePurchaseIngredientNumber = holder.recipe_purchase_ingredient_number.getText().toString();

    }

    /* ********************************************************************************************/
    /* *** Reset buttons color
    /* ********************************************************************************************/
    private void resetButtons(ViewHolder holder) {

        holder.imageButton_delete.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

    }

    /* ********************************************************************************************/
    /* *** Update buttons color and numbers
    /* ********************************************************************************************/
    private void updateButtons(ViewHolder holder) {

        holder.imageButton_delete.setEnabled(false);
        holder.imageButton_delete.setClickable(false);
        holder.imageButton_delete.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);

    }

    /* ********************************************************************************************/
    /* *** Get Recycler View Size
    /* ********************************************************************************************/
    @Override
    public int getItemCount() {

        if (mValues == null) {

            return 0;

        } else {

            return mValues.size();
        }

    }

    /* ********************************************************************************************/
    /* *** A ViewHolder describes an item view and metadata about its place within the RecyclerView
    /* ********************************************************************************************/
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public final ImageButton imageButton_delete;

        final TextView recipe_purchase_recipe_name;
        final TextView recipe_purchase_recipe_number;
        final TextView recipe_purchase_ingredient_name;
        final TextView recipe_purchase_ingredient_number;
        final TextView recipe_purchase_ingredient_amount;
        final TextView recipe_purchase_ingredient_unit;

        RecipePurchaseModel recipePurchaseModelItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;

            imageButton_delete = view.findViewById(R.id.imageButton_delete);

            recipe_purchase_recipe_name = view.findViewById(R.id.textView_recipe_name);
            recipe_purchase_recipe_number = view.findViewById(R.id.textView_RecipeNumber);
            recipe_purchase_ingredient_name = view.findViewById(R.id.textView_IngredientName);
            recipe_purchase_ingredient_number = view.findViewById(R.id.textView_IngredientNumber);
            recipe_purchase_ingredient_amount = view.findViewById(R.id.textView_IngredientAmount);
            recipe_purchase_ingredient_unit = view.findViewById(R.id.textView_IngredientUnit);

        }
    }
}

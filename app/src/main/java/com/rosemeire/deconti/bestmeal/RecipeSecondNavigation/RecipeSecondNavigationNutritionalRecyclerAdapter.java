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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeNutritionalModel;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceNutritionalActivity;

import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_U;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeAuthor;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_calcium;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_carbs;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_cholesterol;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_energy;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_fat;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_fiber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_folate;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_iron;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_magnesium;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_monounsaturated;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_niacin;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_phosphorus;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_polyunsaturated;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_potassium;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_protein;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_riboflavin;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_saturated;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_sodium;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_sugars;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_thiamin;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_trans;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_vitamin_a;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_vitamin_b12;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_vitamin_b6;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_vitamin_c;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_vitamin_d;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_vitamin_e;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_vitamin_k;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;

/* ************************************************************************************************/
/* *** Treat recycler view
/* ************************************************************************************************/
public class RecipeSecondNavigationNutritionalRecyclerAdapter extends RecyclerView.Adapter<RecipeSecondNavigationNutritionalRecyclerAdapter.ViewHolder> {

    private final List<RecipeNutritionalModel> mValues;
    private final Context mContext;

    /* ********************************************************************************************/
    /* *** Load data to update recycler view
    /* ********************************************************************************************/
    RecipeSecondNavigationNutritionalRecyclerAdapter(List<RecipeNutritionalModel> items, RecipeSecondNavigationNutritionalFragment.OnListFragmentInteractionListener listener, Context context) {

        mValues = items;
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeSecondNavigationNutritionalFragment.OnListFragmentInteractionListener mListener = listener;
        mContext = context;
    }

    /* ********************************************************************************************/
    /* *** Recycler view OnCreate
    /* ********************************************************************************************/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_second_navigation_fragment_nutritional_item, parent, false);
        return new ViewHolder(view);
    }

    /* ********************************************************************************************/
    /* *** Update recycler view
    /* ********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // ................................................................. Get value from position
        RecipeNutritionalModel model = mValues.get(position);

        // .................................................................... Set data from holder
        holder.recipeDetailNutritionalModelItem = model;

        holder.textViewValue_Calcium.setText(model.getRecipe_nutritional_calcium());
        holder.textViewValue_Carbs.setText(model.getRecipe_nutritional_carbs());
        holder.textViewValue_Cholesterol.setText(model.getRecipe_nutritional_cholesterol());
        holder.textViewValue_Monounsaturated.setText(model.getRecipe_nutritional_monounsaturated());
        holder.textViewValue_Polyunsaturated.setText(model.getRecipe_nutritional_polyunsaturated());
        holder.textViewValue_Saturated.setText(model.getRecipe_nutritional_saturated());
        holder.textViewValue_Fat.setText(model.getRecipe_nutritional_fat());
        holder.textViewValue_Trans.setText(model.getRecipe_nutritional_trans());
        holder.textViewValue_Iron.setText(model.getRecipe_nutritional_iron());
        holder.textViewValue_Fiber.setText(model.getRecipe_nutritional_fiber());
        holder.textViewValue_Folate.setText(model.getRecipe_nutritional_folate());
        holder.textViewValue_Potassium.setText(model.getRecipe_nutritional_potassium());
        holder.textViewValue_Magnesium.setText(model.getRecipe_nutritional_magnesium());
        holder.textViewValue_Sodium.setText(model.getRecipe_nutritional_sodium());
        holder.textViewValue_Energy.setText(model.getRecipe_nutritional_energy());
        holder.textViewValue_Niacin.setText(model.getRecipe_nutritional_niacin());
        holder.textViewValue_Phosphorus.setText(model.getRecipe_nutritional_phosphorus());
        holder.textViewValue_Protein.setText(model.getRecipe_nutritional_protein());
        holder.textViewValue_Riboflavin.setText(model.getRecipe_nutritional_riboflavin());
        holder.textViewValue_Sugars.setText(model.getRecipe_nutritional_sugars());
        holder.textViewValue_Thiamin.setText(model.getRecipe_nutritional_thiamin());
        holder.textViewValue_Vitamin_E.setText(model.getRecipe_nutritional_vitamin_e());
        holder.textViewValue_Vitamin_A.setText(model.getRecipe_nutritional_vitamin_a());
        holder.textViewValue_Vitamin_B12.setText(model.getRecipe_nutritional_vitamin_b12());
        holder.textViewValue_Vitamin_B6.setText(model.getRecipe_nutritional_vitamin_b6());
        holder.textViewValue_Vitamin_C.setText(model.getRecipe_nutritional_vitamin_c());
        holder.textViewValue_Vitamin_D.setText(model.getRecipe_nutritional_vitamin_d());
        holder.textViewValue_Vitamin_K.setText(model.getRecipe_nutritional_vitamin_k());

        // ........................................................ Save data to maintenance classes
        sCurrent_recipe_nutritional_calcium = holder.textViewValue_Calcium.getText().toString().trim();
        sCurrent_recipe_nutritional_carbs = holder.textViewValue_Carbs.getText().toString().trim();
        sCurrent_recipe_nutritional_cholesterol = holder.textViewValue_Cholesterol.getText().toString().trim();
        sCurrent_recipe_nutritional_monounsaturated = holder.textViewValue_Monounsaturated.getText().toString().trim();
        sCurrent_recipe_nutritional_polyunsaturated = holder.textViewValue_Polyunsaturated.getText().toString().trim();
        sCurrent_recipe_nutritional_saturated = holder.textViewValue_Saturated.getText().toString().trim();
        sCurrent_recipe_nutritional_fat = holder.textViewValue_Fat.getText().toString().trim();
        sCurrent_recipe_nutritional_trans = holder.textViewValue_Trans.getText().toString().trim();
        sCurrent_recipe_nutritional_iron = holder.textViewValue_Iron.getText().toString().trim();
        sCurrent_recipe_nutritional_fiber = holder.textViewValue_Fiber.getText().toString().trim();
        sCurrent_recipe_nutritional_folate = holder.textViewValue_Folate.getText().toString().trim();
        sCurrent_recipe_nutritional_potassium = holder.textViewValue_Potassium.getText().toString().trim();
        sCurrent_recipe_nutritional_magnesium = holder.textViewValue_Magnesium.getText().toString().trim();
        sCurrent_recipe_nutritional_sodium = holder.textViewValue_Sodium.getText().toString().trim();
        sCurrent_recipe_nutritional_energy = holder.textViewValue_Energy.getText().toString().trim();
        sCurrent_recipe_nutritional_niacin = holder.textViewValue_Niacin.getText().toString().trim();
        sCurrent_recipe_nutritional_phosphorus = holder.textViewValue_Phosphorus.getText().toString().trim();
        sCurrent_recipe_nutritional_protein = holder.textViewValue_Protein.getText().toString().trim();
        sCurrent_recipe_nutritional_riboflavin = holder.textViewValue_Riboflavin.getText().toString().trim();
        sCurrent_recipe_nutritional_sugars = holder.textViewValue_Sugars.getText().toString().trim();
        sCurrent_recipe_nutritional_thiamin = holder.textViewValue_Thiamin.getText().toString().trim();
        sCurrent_recipe_nutritional_vitamin_e = holder.textViewValue_Vitamin_E.getText().toString().trim();
        sCurrent_recipe_nutritional_vitamin_a = holder.textViewValue_Vitamin_A.getText().toString().trim();
        sCurrent_recipe_nutritional_vitamin_b12 = holder.textViewValue_Vitamin_B12.getText().toString().trim();
        sCurrent_recipe_nutritional_vitamin_b6 = holder.textViewValue_Vitamin_B6.getText().toString().trim();
        sCurrent_recipe_nutritional_vitamin_c = holder.textViewValue_Vitamin_C.getText().toString().trim();
        sCurrent_recipe_nutritional_vitamin_d = holder.textViewValue_Vitamin_D.getText().toString().trim();
        sCurrent_recipe_nutritional_vitamin_k = holder.textViewValue_Vitamin_K.getText().toString().trim();

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
                        new RecipeToolMaintenanceNutritionalActivity();

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

        public final ImageButton imageButton_edit;

        public final ImageView imageView_recipePhoto;

        final TextView textViewValue_Calcium;
        final TextView textViewValue_Carbs;
        final TextView textViewValue_Cholesterol;
        final TextView textViewValue_Monounsaturated;
        final TextView textViewValue_Polyunsaturated;
        final TextView textViewValue_Saturated;
        final TextView textViewValue_Fat;
        final TextView textViewValue_Trans;
        final TextView textViewValue_Iron;
        final TextView textViewValue_Fiber;
        final TextView textViewValue_Folate;
        final TextView textViewValue_Potassium;
        final TextView textViewValue_Magnesium;
        final TextView textViewValue_Sodium;
        final TextView textViewValue_Energy;
        final TextView textViewValue_Niacin;
        final TextView textViewValue_Phosphorus;
        final TextView textViewValue_Protein;
        final TextView textViewValue_Riboflavin;
        final TextView textViewValue_Sugars;
        final TextView textViewValue_Thiamin;
        final TextView textViewValue_Vitamin_E;
        final TextView textViewValue_Vitamin_A;
        final TextView textViewValue_Vitamin_B12;
        final TextView textViewValue_Vitamin_B6;
        final TextView textViewValue_Vitamin_C;
        final TextView textViewValue_Vitamin_D;
        final TextView textViewValue_Vitamin_K;

        RecipeNutritionalModel recipeDetailNutritionalModelItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;

            imageButton_edit = view.findViewById(R.id.imageButton_edit);

            imageView_recipePhoto = view.findViewById(R.id.imageView_recipePhoto);

            textViewValue_Calcium = view.findViewById(R.id.textViewValue_Calcium);
            textViewValue_Carbs = view.findViewById(R.id.textViewValue_Carbs);
            textViewValue_Cholesterol = view.findViewById(R.id.textViewValue_Cholesterol);
            textViewValue_Monounsaturated = view.findViewById(R.id.textViewValue_Monounsaturated);
            textViewValue_Polyunsaturated = view.findViewById(R.id.textViewValue_Polyunsaturated);
            textViewValue_Saturated = view.findViewById(R.id.textViewValue_Saturated);
            textViewValue_Fat = view.findViewById(R.id.textViewValue_Fat);
            textViewValue_Trans = view.findViewById(R.id.textViewValue_Trans);
            textViewValue_Iron = view.findViewById(R.id.textViewValue_Iron);
            textViewValue_Fiber = view.findViewById(R.id.textViewValue_Fiber);
            textViewValue_Folate = view.findViewById(R.id.textViewValue_Folate);
            textViewValue_Potassium = view.findViewById(R.id.textViewValue_Potassium);
            textViewValue_Magnesium = view.findViewById(R.id.textViewValue_Magnesium);
            textViewValue_Sodium = view.findViewById(R.id.textViewValue_Sodium);
            textViewValue_Energy = view.findViewById(R.id.textViewValue_Energy);
            textViewValue_Niacin = view.findViewById(R.id.textViewValue_Niacin);
            textViewValue_Phosphorus = view.findViewById(R.id.textViewValue_Phosphorus);
            textViewValue_Protein = view.findViewById(R.id.textViewValue_Protein);
            textViewValue_Riboflavin = view.findViewById(R.id.textViewValue_Riboflavin);
            textViewValue_Sugars = view.findViewById(R.id.textViewValue_Sugars);
            textViewValue_Thiamin = view.findViewById(R.id.textViewValue_Thiamin);
            textViewValue_Vitamin_E = view.findViewById(R.id.textViewValue_Vitamin_E);
            textViewValue_Vitamin_A = view.findViewById(R.id.textViewValue_Vitamin_A);
            textViewValue_Vitamin_B12 = view.findViewById(R.id.textViewValue_Vitamin_B12);
            textViewValue_Vitamin_B6 = view.findViewById(R.id.textViewValue_Vitamin_B6);
            textViewValue_Vitamin_C = view.findViewById(R.id.textViewValue_Vitamin_C);
            textViewValue_Vitamin_D = view.findViewById(R.id.textViewValue_Vitamin_D);
            textViewValue_Vitamin_K = view.findViewById(R.id.textViewValue_Vitamin_K);
        }
    }
}

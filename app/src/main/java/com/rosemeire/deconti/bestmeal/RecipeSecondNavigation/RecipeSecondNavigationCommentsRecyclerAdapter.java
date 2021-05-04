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
import android.widget.TextView;

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckNetworkAvailability;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeCommentsModel;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceCommentsActivity;

import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_U;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeAuthor;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCommentNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCommentText;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;

/* ************************************************************************************************/
/* *** Treat recycler view
/* ************************************************************************************************/
public class RecipeSecondNavigationCommentsRecyclerAdapter extends RecyclerView.Adapter<RecipeSecondNavigationCommentsRecyclerAdapter.ViewHolder> {

    private final List<RecipeCommentsModel> mValues;
    private final Context mContext;

    /* ********************************************************************************************/
    /* *** Load data to update recycler view
    /* ********************************************************************************************/
    RecipeSecondNavigationCommentsRecyclerAdapter(List<RecipeCommentsModel> items, RecipeSecondNavigationCommentsFragment.OnListFragmentInteractionListener listener, Context context) {

        mValues = items;
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeSecondNavigationCommentsFragment.OnListFragmentInteractionListener mListener = listener;
        mContext = context;
    }

    /* ********************************************************************************************/
    /* *** Recycler view OnCreate
    /* ********************************************************************************************/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_second_navigation_fragment_comments_item, parent, false);
        return new ViewHolder(view);
    }

    /* ********************************************************************************************/
    /* *** Update recycler view
    /* ********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // ................................................................. Get value from position
        RecipeCommentsModel model = mValues.get(position);

        // .................................................................... Set data from holder
        holder.recipeDetailCommentsModelItem = model;

        holder.textView_RecipeCommentText.setText(model.getRecipe_comments_text());
        holder.textView_RecipeCommentNumber.setText(model.getRecipe_comments_number());

        // ........................................................ Save data to maintenance classes
        sCurrentRecipeCommentText = holder.textView_RecipeCommentText.getText().toString().trim();
        sCurrentRecipeCommentNumber = holder.textView_RecipeCommentNumber.getText().toString().trim();

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
                        new RecipeToolMaintenanceCommentsActivity();

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

        final TextView textView_RecipeCommentText;
        final TextView textView_RecipeCommentNumber;

        RecipeCommentsModel recipeDetailCommentsModelItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;
            imageButton_edit = view.findViewById(R.id.imageButton_edit);
            textView_RecipeCommentText = view.findViewById(R.id.textView_CommentText);
            textView_RecipeCommentNumber = view.findViewById(R.id.textView_CommentNumber);

        }
    }
}

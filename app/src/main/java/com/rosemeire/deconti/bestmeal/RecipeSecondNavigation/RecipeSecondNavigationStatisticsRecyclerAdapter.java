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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeStatisticsModel;
import com.rosemeire.deconti.bestmeal.R;

import java.util.List;

/* ************************************************************************************************/
/* *** Treat recycler view
/* ************************************************************************************************/
public class RecipeSecondNavigationStatisticsRecyclerAdapter extends RecyclerView.Adapter<RecipeSecondNavigationStatisticsRecyclerAdapter.ViewHolder> {

    private final List<RecipeStatisticsModel> mValues;
    private final Context mContext;

    /* ********************************************************************************************/
    /* *** Load data to update recycler view
    /* ********************************************************************************************/
    RecipeSecondNavigationStatisticsRecyclerAdapter(List<RecipeStatisticsModel> items, RecipeSecondNavigationStatisticsFragment.OnListFragmentInteractionListener listener, Context context) {

        mValues = items;
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeSecondNavigationStatisticsFragment.OnListFragmentInteractionListener mListener = listener;
        mContext = context;
    }

    /* ********************************************************************************************/
    /* *** Recycler view OnCreate
    /* ********************************************************************************************/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_second_navigation_fragment_statistics_item, parent, false);
        return new ViewHolder(view);
    }

    /* ********************************************************************************************/
    /* *** Update recycler view
    /* ********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // ................................................................. Get value from position
        RecipeStatisticsModel model = mValues.get(position);

        // .................................................................... Set data from holder
        holder.recipeDetailStatisticsModelItem = model;

        holder.textViewValue_Approved.setText(model.getRecipe_header_approvals());
        holder.textViewValue_Reproved.setText(model.getRecipe_header_reprovals());
        holder.textViewValue_Liked.setText(model.getRecipe_header_likes());
        holder.textViewValue_Disliked.setText(model.getRecipe_header_dislikes());
        holder.textViewValue_Printed.setText(model.getRecipe_header_printings());
        holder.textViewValue_Shared.setText(model.getRecipe_header_sharings());
        holder.textViewValue_Commented.setText(model.getRecipe_header_comments());
        holder.textViewValue_Visualized.setText(model.getRecipe_header_visualizations());

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

        final TextView textViewValue_Approved;
        final TextView textViewValue_Reproved;
        final TextView textViewValue_Liked;
        final TextView textViewValue_Disliked;
        final TextView textViewValue_Printed;
        final TextView textViewValue_Commented;
        final TextView textViewValue_Visualized;
        final TextView textViewValue_Shared;

        RecipeStatisticsModel recipeDetailStatisticsModelItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;

            imageButton_edit = view.findViewById(R.id.imageButton_edit);

            imageView_recipePhoto = view.findViewById(R.id.imageView_recipePhoto);

            textViewValue_Approved = view.findViewById(R.id.textViewValue_Approved);
            textViewValue_Reproved = view.findViewById(R.id.textViewValue_Reproved);
            textViewValue_Liked = view.findViewById(R.id.textViewValue_Liked);
            textViewValue_Disliked = view.findViewById(R.id.textViewValue_Disliked);
            textViewValue_Printed = view.findViewById(R.id.textViewValue_Printed);
            textViewValue_Visualized = view.findViewById(R.id.textViewValue_Visualized);
            textViewValue_Commented = view.findViewById(R.id.textViewValue_Commented);
            textViewValue_Shared = view.findViewById(R.id.textViewValue_Shared);

        }
    }
}

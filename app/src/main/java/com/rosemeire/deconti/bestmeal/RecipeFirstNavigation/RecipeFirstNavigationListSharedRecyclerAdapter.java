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
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckNetworkAvailability;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeHeaderModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableHeader;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationListSharedFragment.OnListFragmentInteractionListener;
import com.rosemeire.deconti.bestmeal.RecipeSecondNavigation.RecipeSecondNavigationCaptainActivity;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolApprove;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolComment;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolDislike;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolLike;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolPrint;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolReprove;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolShare;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolVisualize;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_RECIPES_2;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeAuthor;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeName;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusApproved;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusCommented;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusDisliked;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusLiked;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusPrinted;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusReproved;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusShared;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeStatusVisualized;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;

/* ************************************************************************************************/
/* *** Treat recycler view
/* ************************************************************************************************/
public class RecipeFirstNavigationListSharedRecyclerAdapter extends RecyclerView.Adapter<RecipeFirstNavigationListSharedRecyclerAdapter.ViewHolder> {

    private final List<RecipeHeaderModel> mValues;
    private final Context mContext;
    private Intent intent;

    private static String mCurrentRecipeStatusApproved = null;
    private static String mCurrentRecipeStatusReproved = null;
    private static String mCurrentRecipeStatusLiked = null;
    private static String mCurrentRecipeStatusDisliked = null;
    private static String mCurrentRecipeStatusPrinted = null;
    private static String mCurrentRecipeStatusShared = null;
    private static String mCurrentRecipeStatusCommented = null;
    private static String mCurrentRecipeStatusVisualized = null;

    /* ********************************************************************************************/
    /* *** Load data to update recycler view
    /* ********************************************************************************************/
    RecipeFirstNavigationListSharedRecyclerAdapter(List<RecipeHeaderModel> items, OnListFragmentInteractionListener listener, Context context) {

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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_first_navigation_fragment_all_purpose_item, parent, false);
        return new ViewHolder(view);
    }

    /* ********************************************************************************************/
    /* *** Update recycler view
    /* ********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // ................................................................. Get value from position
        RecipeHeaderModel model = mValues.get(position);
        holder.recipeListModelItem = model;

        // ............................................................. Upload data into Text Views
        uploadFromDataToLayout(holder, model);

        // ................................................................ Obtain photo from recipe
        sCurrentRecipePhoto = model.getRecipe_header_photo();
        obtainRecipePhoto(holder);

        // ................................................ Set buttons according to recipe statuses
        sCurrentRecipeNumber = model.getRecipe_header_number();
        getSQLiteUpdatedButtons();
        updateLayoutButtons(holder);

        // .................................................................... Treat button approve
        holder.imageButton_approve.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else{

                    mCurrentRecipeStatusApproved = mContext.getString(R.string.label_recipe_status_yes);
                    mCurrentRecipeStatusReproved = mContext.getString(R.string.label_recipe_status_no);

                    saveDataFromCurrentRecipe(holder);
                    new RecipeToolApprove(view.getContext());

                    updateLayoutButtons(holder);

                }
            }
        });

        // .................................................................... Treat button reprove
        holder.imageButton_reprove.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else{

                    mCurrentRecipeStatusReproved = mContext.getString(R.string.label_recipe_status_yes);
                    mCurrentRecipeStatusApproved = mContext.getString(R.string.label_recipe_status_no);

                    saveDataFromCurrentRecipe(holder);
                    new RecipeToolReprove(view.getContext());

                    updateLayoutButtons(holder);

                }
            }
        });

        // ....................................................................... Treat button like
        holder.imageButton_like.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else{

                    mCurrentRecipeStatusLiked = mContext.getString(R.string.label_recipe_status_yes);
                    mCurrentRecipeStatusDisliked = mContext.getString(R.string.label_recipe_status_no);

                    saveDataFromCurrentRecipe(holder);
                    new RecipeToolLike(view.getContext());

                    updateLayoutButtons(holder);

                }
            }
        });

        // .................................................................... Treat button dislike
        holder.imageButton_dislike.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else{

                    mCurrentRecipeStatusDisliked = mContext.getString(R.string.label_recipe_status_yes);
                    mCurrentRecipeStatusLiked = mContext.getString(R.string.label_recipe_status_no);

                    saveDataFromCurrentRecipe(holder);
                    new RecipeToolDislike(view.getContext());

                    updateLayoutButtons(holder);

                }
            }
        });

        // .................................................................... Treat button comment
        holder.imageButton_comment.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else{

                    mCurrentRecipeStatusCommented = mContext.getString(R.string.label_recipe_status_yes);

                    saveDataFromCurrentRecipe(holder);
                    new RecipeToolComment(view.getContext());

                    updateLayoutButtons(holder);

                }
            }
        });

        // ...................................................................... Treat button share
        holder.imageButton_share.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else{

                    mCurrentRecipeStatusShared = mContext.getString(R.string.label_recipe_status_yes);

                    saveDataFromCurrentRecipe(holder);
                    new RecipeToolShare(view.getContext());

                    updateLayoutButtons(holder);

                }
            }
        });

        // ...................................................................... Treat button print
        holder.imageButton_print.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else{

                    mCurrentRecipeStatusPrinted = mContext.getString(R.string.label_recipe_status_yes);

                    saveDataFromCurrentRecipe(holder);
                    new RecipeToolPrint(view.getContext());

                    updateLayoutButtons(holder);

                }
            }
        });

        // ................................................................... Treat button click on
        holder.mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else{

                    mCurrentRecipeStatusVisualized = mContext.getString(R.string.label_recipe_status_yes);

                    saveDataFromCurrentRecipe(holder);
                    new RecipeToolVisualize(view.getContext());

                    updateLayoutButtons(holder);

                    intent = new Intent(view.getContext(), RecipeSecondNavigationCaptainActivity.class);
                    view.getContext().startActivity(intent);

                }
            }
        });
    }

    /* ********************************************************************************************/
    /* *** Get updated buttons from SQLite
    /* ********************************************************************************************/
    private void getSQLiteUpdatedButtons() {

        ArrayList recipeStatuses = RecipeDatabaseSQLiteTableHeader.RecipeHeaderGetRecipeStatus(mContext);

        mCurrentRecipeStatusApproved = String.valueOf(recipeStatuses.get(0));
        mCurrentRecipeStatusReproved = String.valueOf(recipeStatuses.get(1));
        mCurrentRecipeStatusLiked = String.valueOf(recipeStatuses.get(2));
        mCurrentRecipeStatusDisliked = String.valueOf(recipeStatuses.get(3));
        mCurrentRecipeStatusPrinted = String.valueOf(recipeStatuses.get(4));
        mCurrentRecipeStatusShared = String.valueOf(recipeStatuses.get(5));
        mCurrentRecipeStatusCommented = String.valueOf(recipeStatuses.get(6));
        mCurrentRecipeStatusVisualized = String.valueOf(recipeStatuses.get(7));

    }

    /* ********************************************************************************************/
    /* *** Update recipe photo
    /* ********************************************************************************************/
    private void obtainRecipePhoto(final ViewHolder holder) {

        File directory = mContext.getDir(PATH_RECIPES_2, Context.MODE_PRIVATE);
        final File newFile = new File(directory, sCurrentRecipePhoto + FILE_TYPE);

        Glide.with(mContext)
                .load(newFile)
                .apply(RequestOptions.centerCropTransform())
                .into(holder.imageView_photo);

    }

    /* ********************************************************************************************/
    /* *** Store current recipe data
    /* ********************************************************************************************/
    private void saveDataFromCurrentRecipe(ViewHolder holder) {

        sCurrentRecipeName = holder.recipe_name.getText().toString();
        sCurrentRecipeNumber = holder.recipe_number.getText().toString();
        sCurrentRecipePhoto = holder.recipe_photo.getText().toString();
        sCurrentRecipeAuthor = holder.recipe_author.getText().toString();

        sCurrentRecipeStatusApproved = mCurrentRecipeStatusApproved;
        sCurrentRecipeStatusReproved = mCurrentRecipeStatusReproved;
        sCurrentRecipeStatusLiked = mCurrentRecipeStatusLiked;
        sCurrentRecipeStatusDisliked = mCurrentRecipeStatusDisliked;
        sCurrentRecipeStatusPrinted = mCurrentRecipeStatusPrinted;
        sCurrentRecipeStatusShared = mCurrentRecipeStatusShared;
        sCurrentRecipeStatusCommented = mCurrentRecipeStatusCommented;
        sCurrentRecipeStatusVisualized = mCurrentRecipeStatusVisualized;

    }

    /* ********************************************************************************************/
    /* *** Format buttons
    /* ********************************************************************************************/
    private void updateLayoutButtons(ViewHolder holder) {

        // ...................................................................... Reset button color
        holder.imageButton_comment.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        holder.imageButton_share.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        holder.imageButton_approve.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        holder.imageButton_reprove.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        holder.imageButton_like.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        holder.imageButton_dislike.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        holder.imageButton_print.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        // ................................................................ Set button color approve
        if (mCurrentRecipeStatusApproved.equals(mContext.getString(R.string.label_recipe_status_yes))) {
            holder.imageButton_approve.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // ................................................................ Set button color reprove
        if (mCurrentRecipeStatusReproved.equals(mContext.getString(R.string.label_recipe_status_yes))) {
            holder.imageButton_reprove.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // ................................................................... Set button color like
        if (mCurrentRecipeStatusLiked.equals(mContext.getString(R.string.label_recipe_status_yes))) {
            holder.imageButton_like.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // ................................................................ Set button color dislike
        if (mCurrentRecipeStatusDisliked.equals(mContext.getString(R.string.label_recipe_status_yes))) {
            holder.imageButton_dislike.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // ................................................................ Set button color comment
        if (mCurrentRecipeStatusCommented.equals(mContext.getString(R.string.label_recipe_status_yes))) {
            holder.imageButton_comment.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // .................................................................. Set button color share
        if (mCurrentRecipeStatusShared.equals(mContext.getString(R.string.label_recipe_status_yes))) {
            holder.imageButton_share.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // .................................................................. Set button color print
        if (mCurrentRecipeStatusPrinted.equals(mContext.getString(R.string.label_recipe_status_yes))) {
            holder.imageButton_print.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

    }

    /* ********************************************************************************************/
    /* *** Format data
    /* ********************************************************************************************/
    private void uploadFromDataToLayout(ViewHolder holder, RecipeHeaderModel model) {

        holder.recipe_name.setText(model.getRecipe_header_name());
        holder.recipe_number.setText(model.getRecipe_header_number());
        holder.recipe_photo.setText(model.getRecipe_header_photo());
        holder.recipe_price.setText(model.getRecipe_header_price());
        holder.recipe_time.setText(model.getRecipe_header_time());
        holder.recipe_servings.setText(model.getRecipe_header_servings());
        holder.recipe_difficult.setText(model.getRecipe_header_difficult());

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

        public final ImageView imageView_photo;

        public final ImageButton imageButton_comment;
        public final ImageButton imageButton_share;
        public final ImageButton imageButton_approve;
        public final ImageButton imageButton_reprove;
        public final ImageButton imageButton_like;
        public final ImageButton imageButton_dislike;
        public final ImageButton imageButton_print;

        public final TextView recipe_name;
        public final TextView recipe_number;
        public final TextView recipe_author;
        public final TextView recipe_photo;
        public final TextView recipe_status;
        public final TextView recipe_price;
        public final TextView recipe_time;
        public final TextView recipe_servings;
        public final TextView recipe_difficult;

        public RecipeHeaderModel recipeListModelItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;

            imageView_photo = view.findViewById(R.id.imageView_recipePhoto);

            recipe_name = view.findViewById(R.id.textView_recipe_name);
            recipe_number = view.findViewById(R.id.textView_RecipeNumber);
            recipe_photo = view.findViewById(R.id.textView_RecipePhoto);
            recipe_author = view.findViewById(R.id.textView_RecipeAuthor);
            recipe_status = view.findViewById(R.id.textView_RecipeStatus);
            recipe_price = view.findViewById(R.id.textView_RecipePrice);
            recipe_time = view.findViewById(R.id.textView_RecipeTime);
            recipe_servings = view.findViewById(R.id.textView_RecipeServings);
            recipe_difficult = view.findViewById(R.id.textView_RecipeDifficult);

            imageButton_comment = view.findViewById(R.id.imageButton_comment);
            imageButton_share = view.findViewById(R.id.imageButton_share);
            imageButton_approve = view.findViewById(R.id.imageButton_approve);
            imageButton_reprove = view.findViewById(R.id.imageButton_reprove);
            imageButton_like = view.findViewById(R.id.imageButton_like);
            imageButton_dislike = view.findViewById(R.id.imageButton_dislike);
            imageButton_print = view.findViewById(R.id.imageButton_print);

        }
    }
}

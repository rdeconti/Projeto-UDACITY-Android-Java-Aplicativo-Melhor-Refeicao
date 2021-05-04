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
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckNetworkAvailability;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSettingLocalization;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSharedPreferencesGet;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableNutritional;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeNutritionalModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableHeader;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableNutritional;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolApprove;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolComment;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolDislike;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolLike;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolPrint;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolReprove;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolShare;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_GET_FROM_FIREBASE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_RECIPES_1;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sGetDataFrom;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentLanguageIsoCode;
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
/* *** Treat Fragment Detail Recipe: NUTRITIONAL
/* ************************************************************************************************/
public class RecipeSecondNavigationNutritionalFragment extends Fragment {

    private ImageView imageView_recipePhoto;

    private TextView recipe_name;
    private TextView recipe_number;

    private ImageButton imageButton_comment;
    private ImageButton imageButton_share;
    private ImageButton imageButton_approve;
    private ImageButton imageButton_reprove;
    private ImageButton imageButton_like;
    private ImageButton imageButton_dislike;
    private ImageButton imageButton_print;

    private OnListFragmentInteractionListener mListener;

    private List<RecipeNutritionalModel> list = new ArrayList<>();

    private static String mCurrentRecipeStatusApproved = null;
    private static String mCurrentRecipeStatusReproved = null;
    private static String mCurrentRecipeStatusLiked = null;
    private static String mCurrentRecipeStatusDisliked = null;
    private static String mCurrentRecipeStatusPrinted = null;
    private static String mCurrentRecipeStatusShared = null;
    private static String mCurrentRecipeStatusCommented = null;
    private static String mCurrentRecipeStatusVisualized = null;

    /* ********************************************************************************************/
    /* *** Mandatory empty constructor for the fragment manager to instantiate the fragment
    /* ********************************************************************************************/
    public RecipeSecondNavigationNutritionalFragment() {

    }

    /* ********************************************************************************************/
    /* *** Fragment onCreateView
    /* ********************************************************************************************/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // .............................................................................. Set Layout
        View view = inflater.inflate(R.layout.recipe_second_navigation_fragment_nutritional_list, container, false);

        // ............................................................................... Set title
        Objects.requireNonNull(getActivity()).setTitle(R.string.label_detail);

        // ................................................. Get shared preferences and set language
        new SupportSharedPreferencesGet(getContext());
        new SupportSettingLocalization();

        Locale locale = new Locale(sCurrentLanguageIsoCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // ........................................................... Set recycler view and divider
        RecyclerView recyclerView = view.findViewById(R.id.recipeNutritionalModels);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));

        // ........................................................................ Set progress bar
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        // .......................................................................... Set mobile ads
        MobileAds.initialize(getContext(), getString(R.string.admob_banner_ad_unit_number_key));
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        LinearLayout linearLayoutAdMob = view.findViewById(R.id.linearLayoutAdMob);
        linearLayoutAdMob.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_TOOLBAR);

        // .............................................................. Check network availability
        sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(getContext());

        if (!sReturnCode) {
            new SupportMessageToast(getContext(), getString(R.string.message_NetworkNotAvailableList));
        }

        // ................................................................ Obtain photo from recipe
        imageView_recipePhoto = view.findViewById(R.id.imageView_recipePhoto);
        obtainRecipePhoto(getContext());

        // ............................................................. Upload data into Text Views
        recipe_name = view.findViewById(R.id.textView_recipe_name);
        recipe_number = view.findViewById(R.id.textView_RecipeNumber);

        recipe_name.setText(sCurrentRecipeName);
        recipe_number.setText(sCurrentRecipeNumber);

        // ................................................ Set buttons according to recipe statuses
        imageButton_comment = view.findViewById(R.id.imageButton_comment);
        imageButton_share = view.findViewById(R.id.imageButton_share);
        imageButton_approve = view.findViewById(R.id.imageButton_approve);
        imageButton_reprove = view.findViewById(R.id.imageButton_reprove);
        imageButton_like = view.findViewById(R.id.imageButton_like);
        imageButton_dislike = view.findViewById(R.id.imageButton_dislike);
        imageButton_print = view.findViewById(R.id.imageButton_print);

        // ................................................ Set buttons according to recipe statuses
        getSQLiteUpdatedButtons();
        updateLayoutButtons(getContext());

        // ............................................................ Obtain data to recycler view
        progressBar.setVisibility(View.VISIBLE);

        list.clear();

        if (sGetDataFrom.equals(DATA_GET_FROM_FIREBASE)) {

            list = RecipeDatabaseFirebaseTableNutritional.RecipeListDetailNutritionalGetList();

        } else {

            list = RecipeDatabaseSQLiteTableNutritional.RecipeNutritionalGetList(getContext());

        }

        recyclerView.setAdapter(new RecipeSecondNavigationNutritionalRecyclerAdapter(list, mListener, getContext()));

        progressBar.setVisibility(View.INVISIBLE);

        // .................................................................... Treat button approve
        imageButton_approve.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(Objects.requireNonNull(getContext()));

                if (!sReturnCode) {

                    new SupportMessageToast(getContext(), getContext().getString(R.string.message_network_not_available));

                }else {

                    mCurrentRecipeStatusApproved = Objects.requireNonNull(getContext()).getString(R.string.label_recipe_status_yes);
                    mCurrentRecipeStatusReproved = getContext().getString(R.string.label_recipe_status_no);

                    saveDataFromCurrentRecipe();
                    new RecipeToolApprove(view.getContext());

                    updateLayoutButtons(getContext());
                }
            }
        });

        // .................................................................... Treat button reprove
        imageButton_reprove.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(Objects.requireNonNull(getContext()));

                if (!sReturnCode) {

                    new SupportMessageToast(getContext(), getContext().getString(R.string.message_network_not_available));

                }else {

                    mCurrentRecipeStatusReproved = Objects.requireNonNull(getContext()).getString(R.string.label_recipe_status_yes);
                    mCurrentRecipeStatusApproved = getContext().getString(R.string.label_recipe_status_no);

                    saveDataFromCurrentRecipe();
                    new RecipeToolReprove(view.getContext());

                    updateLayoutButtons(getContext());

                    saveDataFromCurrentRecipe();

                }
            }
        });

        // ....................................................................... Treat button like
        imageButton_like.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(Objects.requireNonNull(getContext()));

                if (!sReturnCode) {

                    new SupportMessageToast(getContext(), getContext().getString(R.string.message_network_not_available));

                }else {

                    mCurrentRecipeStatusLiked = Objects.requireNonNull(getContext()).getString(R.string.label_recipe_status_yes);
                    mCurrentRecipeStatusDisliked = getContext().getString(R.string.label_recipe_status_no);

                    saveDataFromCurrentRecipe();
                    new RecipeToolLike(view.getContext());

                    updateLayoutButtons(getContext());

                }
            }
        });

        // .................................................................... Treat button dislike
        imageButton_dislike.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(Objects.requireNonNull(getContext()));

                if (!sReturnCode) {

                    new SupportMessageToast(getContext(), getContext().getString(R.string.message_network_not_available));

                }else {

                    mCurrentRecipeStatusDisliked = Objects.requireNonNull(getContext()).getString(R.string.label_recipe_status_yes);
                    mCurrentRecipeStatusLiked = getContext().getString(R.string.label_recipe_status_no);

                    saveDataFromCurrentRecipe();
                    new RecipeToolDislike(view.getContext());

                    updateLayoutButtons(getContext());

                }
            }
        });

        // .................................................................... Treat button comment
        imageButton_comment.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(Objects.requireNonNull(getContext()));

                if (!sReturnCode) {

                    new SupportMessageToast(getContext(), getContext().getString(R.string.message_network_not_available));

                }else {

                    mCurrentRecipeStatusCommented = Objects.requireNonNull(getContext()).getString(R.string.label_recipe_status_yes);

                    saveDataFromCurrentRecipe();
                    new RecipeToolComment(view.getContext());

                    updateLayoutButtons(getContext());

                }
            }
        });

        // ...................................................................... Treat button share
        imageButton_share.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(Objects.requireNonNull(getContext()));

                if (!sReturnCode) {

                    new SupportMessageToast(getContext(), getContext().getString(R.string.message_network_not_available));

                }else {

                    mCurrentRecipeStatusShared = Objects.requireNonNull(getContext()).getString(R.string.label_recipe_status_yes);

                    saveDataFromCurrentRecipe();
                    new RecipeToolShare(view.getContext());

                    updateLayoutButtons(getContext());
                }
            }
        });

        // ...................................................................... Treat button print
        imageButton_print.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(Objects.requireNonNull(getContext()));

                if (!sReturnCode) {

                    new SupportMessageToast(getContext(), getContext().getString(R.string.message_network_not_available));

                }else {

                    mCurrentRecipeStatusPrinted = Objects.requireNonNull(getContext()).getString(R.string.label_recipe_status_yes);

                    saveDataFromCurrentRecipe();
                    new RecipeToolPrint(view.getContext());

                    updateLayoutButtons(getContext());

                }
            }
        });

        // ................................................................... Treat button click on
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        return view;
    }

    /* ********************************************************************************************/
    /* *** Get updated buttons from SQLite
    /* ********************************************************************************************/
    private void getSQLiteUpdatedButtons() {

        ArrayList recipeStatuses = RecipeDatabaseSQLiteTableHeader.RecipeHeaderGetRecipeStatus(Objects.requireNonNull(getContext()));

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
    /* *** Store current recipe data
    /* ********************************************************************************************/
    private void saveDataFromCurrentRecipe() {

        sCurrentRecipeName = recipe_name.getText().toString();
        sCurrentRecipeNumber = recipe_number.getText().toString();

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
    /* *** Obtain recipe photo
    /* ********************************************************************************************/
    private void obtainRecipePhoto(final Context context) {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(PATH_RECIPES_1).child(sCurrentRecipePhoto + FILE_TYPE);

        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                if (task.isSuccessful()) {
                    Glide.with(context)
                            .load(task.getResult())
                            .apply(RequestOptions.centerCropTransform())
                            .into(imageView_recipePhoto);
                }
            }
        });
    }

    /* ********************************************************************************************/
    /* *** Initialize buttons
    /* ********************************************************************************************/
    private void updateLayoutButtons(Context context) {

        // ...................................................................... Reset button color
        imageButton_comment.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        imageButton_share.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        imageButton_approve.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        imageButton_reprove.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        imageButton_like.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        imageButton_dislike.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
        imageButton_print.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

        // ................................................................ Set button color approve
        if (mCurrentRecipeStatusApproved.equals(context.getString(R.string.label_recipe_status_yes))) {
            imageButton_approve.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // ................................................................ Set button color reprove
        if (mCurrentRecipeStatusReproved.equals(context.getString(R.string.label_recipe_status_yes))) {
            imageButton_reprove.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // ................................................................... Set button color like
        if (mCurrentRecipeStatusLiked.equals(context.getString(R.string.label_recipe_status_yes))) {
            imageButton_like.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // ................................................................ Set button color dislike
        if (mCurrentRecipeStatusDisliked.equals(context.getString(R.string.label_recipe_status_yes))) {
            imageButton_dislike.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // ................................................................ Set button color comment
        if (mCurrentRecipeStatusCommented.equals(context.getString(R.string.label_recipe_status_yes))) {
            imageButton_comment.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // .................................................................. Set button color share
        if (mCurrentRecipeStatusShared.equals(context.getString(R.string.label_recipe_status_yes))) {
            imageButton_share.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

        // .................................................................. Set button color print
        if (mCurrentRecipeStatusPrinted.equals(context.getString(R.string.label_recipe_status_yes))) {
            imageButton_print.setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
        }

    }

    /* ********************************************************************************************/
    /* *** Refresh fragment
    /* ********************************************************************************************/
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if (isVisibleToUser) {

            assert getFragmentManager() != null;
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();

        }
    }

    /* ********************************************************************************************/
    /* *** Fragment OnAttach
    /* ********************************************************************************************/
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnListFragmentInteractionListener) {

            mListener = (OnListFragmentInteractionListener) context;

        } else {

            throw new RuntimeException(context.toString() + getString(R.string.message_error_003));
        }
    }

    /* ********************************************************************************************/
    /* *** Fragment onDetach
    /* ********************************************************************************************/
    @Override
    public void onDetach() {
        super.onDetach();

        mListener = null;
    }

    /* ********************************************************************************************/
    /* *** Fragment Interface OnListFragmentInteractionListener
    /* ********************************************************************************************/
    public interface OnListFragmentInteractionListener {

        void onListFragmentInteraction(RecipeNutritionalModel item);
    }
}

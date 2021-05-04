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
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSettingLocalization;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSharedPreferencesGet;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeHeaderModel;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableHeader;
import com.rosemeire.deconti.bestmeal.R;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_REPROVED_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentLanguageIsoCode;

/* ************************************************************************************************/
/* *** Treat Fragment List Recipe: REPROVED
/* ************************************************************************************************/
public class RecipeFirstNavigationListReprovedFragment extends Fragment {

    private OnListFragmentInteractionListener mListener;

    private ProgressBar progressBar;

    /* ********************************************************************************************/
    /* *** Mandatory empty constructor for the fragment manager to instantiate the fragment
    /* ********************************************************************************************/
    public RecipeFirstNavigationListReprovedFragment() {

    }

    /* ********************************************************************************************/
    /* *** Fragment onCreateView
    /* ********************************************************************************************/
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // .............................................................................. Set Layout
        View view = inflater.inflate(R.layout.recipe_first_navigation_fragment_reproved_list, container, false);

        // ............................................................................... Set title
        Objects.requireNonNull(getActivity()).setTitle(R.string.label_list);

        // ................................................. Get shared preferences and set language
        new SupportSharedPreferencesGet(getContext());
        new SupportSettingLocalization();

        Locale locale = new Locale(sCurrentLanguageIsoCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // ........................................................... Set recycler view and divider
        RecyclerView recyclerView = view.findViewById(R.id.recipeListModelsReproved);
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getContext()), DividerItemDecoration.VERTICAL));

        // ........................................................................ Set progress bar
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        // .......................................................................... Set mobile ads
        MobileAds.initialize(getContext(), getString(R.string.admob_banner_ad_unit_number_key));
        AdView mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        LinearLayout linearLayoutAdMob = view.findViewById(R.id.linearLayoutAdMob);
        linearLayoutAdMob.setBackgroundColor(INT_COLOR_RECIPE_LIST_REPROVED_TOOLBAR);

        // ........................................... Get data from SQLite and update recycler view
        progressBar.setVisibility(View.VISIBLE);

        List<RecipeHeaderModel> list_reproved = RecipeDatabaseSQLiteTableHeader.RecipeHeaderGetListReproved(getContext());

        recyclerView.setAdapter(new RecipeFirstNavigationListReprovedRecyclerAdapter(list_reproved, mListener, getContext()));

        progressBar.setVisibility(View.INVISIBLE);

        return view;

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

        void onListFragmentInteraction(RecipeHeaderModel item);
    }
}

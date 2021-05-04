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

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rosemeire.deconti.bestmeal.ApplicationAuthorization.AuthorizationChangeEmailActivity;
import com.rosemeire.deconti.bestmeal.ApplicationAuthorization.AuthorizationChangePasswordActivity;
import com.rosemeire.deconti.bestmeal.ApplicationAuthorization.AuthorizationLogOutActivity;
import com.rosemeire.deconti.bestmeal.ApplicationAuthorization.AuthorizationResetActivity;
import com.rosemeire.deconti.bestmeal.ApplicationAuthorization.AuthorizationSignOutActivity;
import com.rosemeire.deconti.bestmeal.ApplicationSettings.ApplicationSettingActivity;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSettingLocalization;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSharedPreferencesGet;
import com.rosemeire.deconti.bestmeal.ApplicationTexts.ApplicationTextAboutUsActivity;
import com.rosemeire.deconti.bestmeal.ApplicationTexts.ApplicationTextHelpActivity;
import com.rosemeire.deconti.bestmeal.ApplicationTexts.ApplicationTextTermsActivity;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeHeaderModel;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipePurchaseModel;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceHeaderActivity;

import java.util.Locale;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_C;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_GET_FROM_FIREBASE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.DATA_GET_FROM_SQLITE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sGetDataFrom;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_ALL_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_ALL_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_ALL_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_ALL_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_APPROVED_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_APPROVED_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_APPROVED_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_APPROVED_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_BEST_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_BEST_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_BEST_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_BEST_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_BOOK_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_BOOK_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_BOOK_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_BOOK_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_DISLIKED_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_DISLIKED_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_DISLIKED_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_DISLIKED_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_LIKED_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_LIKED_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_LIKED_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_LIKED_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_PRINTED_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_PRINTED_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_PRINTED_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_PRINTED_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_PURCHASE_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_PURCHASE_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_PURCHASE_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_PURCHASE_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_REPROVED_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_REPROVED_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_REPROVED_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_REPROVED_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_SHARED_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_SHARED_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_SHARED_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_SHARED_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_TEXT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_VISUALIZED_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_VISUALIZED_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_VISUALIZED_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_LIST_VISUALIZED_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentLanguageIsoCode;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sFirstNavigationTabSelected;

/* ************************************************************************************************/
/* *** Treat Main Activity Recipe First Navigation
/* ************************************************************************************************/
public class RecipeFirstNavigationCaptainActivity extends AppCompatActivity
        implements
        SharedPreferences.OnSharedPreferenceChangeListener,
        NavigationView.OnNavigationItemSelectedListener,
        RecipeFirstNavigationListBestFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListAllFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListLikedFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListDislikedFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListApprovedFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListReprovedFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListBookFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListPurchaseFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListSharedFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListPrintedFragment.OnListFragmentInteractionListener,
        RecipeFirstNavigationListVisualizedFragment.OnListFragmentInteractionListener {

    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private TabLayout.Tab tabLayoutTabAt;
    private Intent intent;

    private final int[] tabLayoutIcons = {
            R.drawable.ic_recipe_best_white_24dp,
            R.drawable.ic_recipe_all_white_24dp,
            R.drawable.ic_recipe_like_white_24dp,
            R.drawable.ic_recipe_dislike_white_24dp,
            R.drawable.ic_recipe_approved_white_24dp,
            R.drawable.ic_recipe_reproved_white_24dp,
            R.drawable.ic_recipe_book_white_24dp,
            R.drawable.ic_recipe_share_white_24dp,
            R.drawable.ic_recipe_print_white_24dp,
            R.drawable.ic_recipe_visibility_white_24dp,
            R.drawable.ic_recipe_purchase_list_white_24dp};

    /* ********************************************************************************************/
    /* *** Set and create all screen objects
    /* ********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // .................................................................. Get Shared Preferences
        new SupportSharedPreferencesGet(getApplicationContext());
        new SupportSettingLocalization();

        // ............................................................................ Set Language
        Locale locale = new Locale(sCurrentLanguageIsoCode);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getResources().getDisplayMetrics());

        // ............................................................... Initialize Layout Objects
        setContentView(R.layout.recipe_first_navigation_activity);

        appBarLayout = findViewById(R.id.appBarLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(7);

        // .............................................................. Set up Floating Action Bar
        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);

        // .................................................................. Set up TabLayout Icons
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabLayoutIcons[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabLayoutIcons[1]);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(tabLayoutIcons[2]);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(tabLayoutIcons[3]);
        Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(tabLayoutIcons[4]);
        Objects.requireNonNull(tabLayout.getTabAt(5)).setIcon(tabLayoutIcons[5]);
        Objects.requireNonNull(tabLayout.getTabAt(6)).setIcon(tabLayoutIcons[6]);
        Objects.requireNonNull(tabLayout.getTabAt(7)).setIcon(tabLayoutIcons[7]);
        Objects.requireNonNull(tabLayout.getTabAt(8)).setIcon(tabLayoutIcons[8]);
        Objects.requireNonNull(tabLayout.getTabAt(9)).setIcon(tabLayoutIcons[9]);
        Objects.requireNonNull(tabLayout.getTabAt(10)).setIcon(tabLayoutIcons[10]);

        // ........................................................................ Set up First Tab
        sFirstNavigationTabSelected = 0;
        sGetDataFrom = DATA_GET_FROM_FIREBASE;
        setFragmentColor();

        tabLayoutTabAt = tabLayout.getTabAt(0);
        assert tabLayoutTabAt != null;
        tabLayoutTabAt.select();

        /* ****************************************************************************************/
        /* *** Treat Floating Action Bar
        /* ****************************************************************************************/
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sTypeCRUD = CRUD_TYPE_C;

                intent = new Intent(getApplicationContext(), RecipeToolMaintenanceHeaderActivity.class);
                startActivity(intent);

            }
        });

        /* ****************************************************************************************/
        /* *** This method will be invoked when a new page becomes selected
        /* ****************************************************************************************/
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {

                int pageSelected = viewPager.getCurrentItem();

                switch (pageSelected) {

                    case 0:
                        sFirstNavigationTabSelected = 0;
                        sGetDataFrom = DATA_GET_FROM_FIREBASE;
                        break;

                    case 1:
                        sFirstNavigationTabSelected = 1;
                        sGetDataFrom = DATA_GET_FROM_FIREBASE;
                        break;

                    case 2:
                        sFirstNavigationTabSelected = 2;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    case 3:
                        sFirstNavigationTabSelected = 3;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    case 4:
                        sFirstNavigationTabSelected = 4;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    case 5:
                        sFirstNavigationTabSelected = 5;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    case 6:
                        sFirstNavigationTabSelected = 6;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    case 7:

                        sFirstNavigationTabSelected = 7;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    case 8:
                        sFirstNavigationTabSelected = 8;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    case 9:
                        sFirstNavigationTabSelected = 9;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    case 10:
                        sFirstNavigationTabSelected = 10;
                        sGetDataFrom = DATA_GET_FROM_SQLITE;
                        break;

                    default:
                        sFirstNavigationTabSelected = 0;
                        sGetDataFrom = DATA_GET_FROM_FIREBASE;
                        break;
                }

                setFragmentColor();

                new FragmentAdapter(getSupportFragmentManager());

            }

        /* ****************************************************************************************/
        /* *** This method will be invoked when the current page is scrolled
        /* ****************************************************************************************/
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

        /* ****************************************************************************************/
        /* *** Called when the scroll state changes
        /* ****************************************************************************************/
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /* ********************************************************************************************/
    /* *** Treat navigation drawer back pressed
    /* ********************************************************************************************/
    @Override
    public void onBackPressed() {

        drawerLayout = findViewById(R.id.drawer_layout);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else {

            super.onBackPressed();

        }
    }

    /* ********************************************************************************************/
    /* *** Inflate menu: this adds items to the action bar if it is present.
    /* ********************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.first_navigation_menu_activity, menu);
        return true;
    }

    /* ********************************************************************************************/
    /* *** Handle action bar item clicks here. The action bar will automatically handle clicks on
    /* *** the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
    /* ********************************************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        TabLayout.Tab tabLayoutTabAt;

        int menuItem = item.getItemId();

        switch (menuItem) {

            case R.id.back:
                finish();
                break;

            case R.id.home:

                sFirstNavigationTabSelected = 0;
                sGetDataFrom = DATA_GET_FROM_FIREBASE;
                setFragmentColor();

                new FragmentAdapter(getSupportFragmentManager());

                tabLayoutTabAt = tabLayout.getTabAt(0);
                assert tabLayoutTabAt != null;
                tabLayoutTabAt.select();

                break;

            case R.id.purchaseList:

                sFirstNavigationTabSelected = 10;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                setFragmentColor();

                new FragmentAdapter(getSupportFragmentManager());

                tabLayoutTabAt = tabLayout.getTabAt(10);
                assert tabLayoutTabAt != null;
                tabLayoutTabAt.select();

                break;

            case R.id.settings:
                intent = new Intent(getApplicationContext(), ApplicationSettingActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;

            case R.id.logout:
                intent = new Intent(getApplicationContext(), AuthorizationLogOutActivity.class);
                startActivity(intent);
                break;

            case R.id.reset_password:
                intent = new Intent(getApplicationContext(), AuthorizationResetActivity.class);
                startActivity(intent);
                break;

            case R.id.change_email:
                intent = new Intent(getApplicationContext(), AuthorizationChangeEmailActivity.class);
                startActivity(intent);
                break;

            case R.id.change_password:
                intent = new Intent(getApplicationContext(), AuthorizationChangePasswordActivity.class);
                startActivity(intent);
                break;

            case R.id.signout:
                intent = new Intent(getApplicationContext(), AuthorizationSignOutActivity.class);
                startActivity(intent);
                break;

            case R.id.help:
                intent = new Intent(getApplicationContext(), ApplicationTextHelpActivity.class);
                startActivity(intent);
                break;

            case R.id.terms:
                intent = new Intent(getApplicationContext(), ApplicationTextTermsActivity.class);
                startActivity(intent);
                break;

            case R.id.about_us:
                intent = new Intent(getApplicationContext(), ApplicationTextAboutUsActivity.class);
                startActivity(intent);
                break;

            default:
                sFirstNavigationTabSelected = 0;
                sGetDataFrom = DATA_GET_FROM_FIREBASE;
                setFragmentColor();

                new FragmentAdapter(getSupportFragmentManager());

                tabLayoutTabAt = tabLayout.getTabAt(0);
                assert tabLayoutTabAt != null;
                tabLayoutTabAt.select();

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    /* ********************************************************************************************/
    /* *** Handle navigation view item clicks here
    /* ********************************************************************************************/
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int tabSelected = menuItem.getItemId();

        switch (tabSelected) {

            case R.id.recipe_list_best:
                sFirstNavigationTabSelected = 0;
                sGetDataFrom = DATA_GET_FROM_FIREBASE;
                break;

            case R.id.recipe_list_all:
                sFirstNavigationTabSelected = 1;
                sGetDataFrom = DATA_GET_FROM_FIREBASE;
                break;

            case R.id.recipe_list_liked:
                sFirstNavigationTabSelected = 2;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            case R.id.recipe_list_dislike:
                sFirstNavigationTabSelected = 3;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            case R.id.recipe_list_approved:
                sFirstNavigationTabSelected = 4;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            case R.id.recipe_list_reproved:
                sFirstNavigationTabSelected = 5;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            case R.id.recipe_list_book:
                sFirstNavigationTabSelected = 6;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            case R.id.recipe_list_shared:
                sFirstNavigationTabSelected = 7;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            case R.id.recipe_list_printed:
                sFirstNavigationTabSelected = 8;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            case R.id.recipe_list_visualized:
                sFirstNavigationTabSelected = 9;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            case R.id.recipe_list_purchase:
                sFirstNavigationTabSelected = 10;
                sGetDataFrom = DATA_GET_FROM_SQLITE;
                break;

            default:
                sFirstNavigationTabSelected = 0;
                sGetDataFrom = DATA_GET_FROM_FIREBASE;
                break;
        }

        setFragmentColor();

        new FragmentAdapter(getSupportFragmentManager());

        tabLayoutTabAt = tabLayout.getTabAt(sFirstNavigationTabSelected);
        assert tabLayoutTabAt != null;
        tabLayoutTabAt.select();

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /* ********************************************************************************************/
    /* *** Set Fragment Colors
    /* ********************************************************************************************/
    private void setFragmentColor() {

        toolbar.setTitleTextColor(INT_COLOR_RECIPE_LIST_TEXT);

        switch (sFirstNavigationTabSelected) {

            case 0:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_NAVIGATION_VIEW);
                break;

            case 1:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_ALL_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_ALL_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_ALL_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_ALL_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_ALL_NAVIGATION_VIEW);
                break;

            case 2:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_LIKED_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_LIKED_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_LIKED_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_LIKED_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_LIKED_NAVIGATION_VIEW);
                break;

            case 3:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_DISLIKED_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_DISLIKED_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_DISLIKED_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_DISLIKED_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_DISLIKED_NAVIGATION_VIEW);
                break;

            case 4:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_APPROVED_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_APPROVED_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_APPROVED_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_APPROVED_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_APPROVED_NAVIGATION_VIEW);
                break;

            case 5:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_REPROVED_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_REPROVED_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_REPROVED_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_REPROVED_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_REPROVED_NAVIGATION_VIEW);
                break;

            case 6:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_BOOK_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_BOOK_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_BOOK_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_BOOK_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_BOOK_NAVIGATION_VIEW);
                break;

            case 7:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_SHARED_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_SHARED_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_SHARED_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_SHARED_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_SHARED_NAVIGATION_VIEW);
                break;

            case 8:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_PRINTED_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_PRINTED_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_PRINTED_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_PRINTED_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_PRINTED_NAVIGATION_VIEW);
                break;

            case 9:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_VISUALIZED_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_VISUALIZED_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_VISUALIZED_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_VISUALIZED_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_VISUALIZED_NAVIGATION_VIEW);
                break;

            case 10:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_PURCHASE_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_PURCHASE_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_PURCHASE_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_PURCHASE_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_PURCHASE_NAVIGATION_VIEW);
                break;

            default:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_LIST_BEST_NAVIGATION_VIEW);
                break;
        }
    }

    /* ********************************************************************************************/
    /* *** Fragment onListFragmentInteraction
    /* ********************************************************************************************/
    @Override
    public void onListFragmentInteraction(RecipeHeaderModel item) {

    }

    @Override
    public void onListFragmentInteraction(RecipePurchaseModel item) {

    }

    /* ********************************************************************************************/
    /* *** onSharedPreferenceChanged
    /* ********************************************************************************************/
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    /* ********************************************************************************************/
    /* *** TREAT FRAGEMTN
    /* ********************************************************************************************/
    public class FragmentAdapter extends FragmentPagerAdapter {

        private final int position = 11;

        FragmentAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);

        }

        @Override
        public int getCount() {

            return position;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:
                    sFirstNavigationTabSelected = 0;
                    return new RecipeFirstNavigationListBestFragment();

                case 1:
                    sFirstNavigationTabSelected = 1;
                    return new RecipeFirstNavigationListAllFragment();

                case 2:
                    sFirstNavigationTabSelected = 2;
                    return new RecipeFirstNavigationListLikedFragment();

                case 3:
                    sFirstNavigationTabSelected = 3;
                    return new RecipeFirstNavigationListDislikedFragment();

                case 4:
                    sFirstNavigationTabSelected = 4;
                    return new RecipeFirstNavigationListApprovedFragment();

                case 5:
                    sFirstNavigationTabSelected = 5;
                    return new RecipeFirstNavigationListReprovedFragment();

                case 6:
                    sFirstNavigationTabSelected = 6;
                    return new RecipeFirstNavigationListBookFragment();

                case 7:
                    sFirstNavigationTabSelected = 7;
                    return new RecipeFirstNavigationListSharedFragment();

                case 8:
                    sFirstNavigationTabSelected = 8;
                    return new RecipeFirstNavigationListPrintedFragment();

                case 9:
                    sFirstNavigationTabSelected = 9;
                    return new RecipeFirstNavigationListVisualizedFragment();

                case 10:
                    sFirstNavigationTabSelected = 10;
                    return new RecipeFirstNavigationListPurchaseFragment();

                default:
                    sFirstNavigationTabSelected = 0;
                    return new RecipeFirstNavigationListBestFragment();

            }

        }

        /* ****************************************************************************************/
        /* *** Get fragment page title and named each tab layouts
        /* ****************************************************************************************/
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {

                case 0:
                    return getString(R.string.label_best);

                case 1:
                    return getString(R.string.label_all);

                case 2:
                    return getString(R.string.label_liked);

                case 3:
                    return getString(R.string.label_disliked);

                case 4:
                    return getString(R.string.label_approved);

                case 5:
                    return getString(R.string.label_reproved);

                case 6:
                    return getString(R.string.label_book);

                case 7:
                    return getString(R.string.label_shared);

                case 8:
                    return getString(R.string.label_printed);

                case 9:
                    return getString(R.string.label_visualized);

                case 10:
                    return getString(R.string.label_purchase);

                default:
                    return getString(R.string.label_best);
            }
        }
    }
}

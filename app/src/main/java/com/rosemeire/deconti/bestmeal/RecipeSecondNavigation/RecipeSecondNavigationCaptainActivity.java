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
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeCommentsModel;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeHeaderModel;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeIngredientsModel;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeInstructionsModel;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeNutritionalModel;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeTipsModel;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceCommentsActivity;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceHeaderActivity;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceIngredientsActivity;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceInstructionsActivity;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceNutritionalActivity;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceTipsActivity;

import java.util.Locale;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_C;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_COMMENTS_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_COMMENTS_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_COMMENTS_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_COMMENTS_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_HEADER_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_HEADER_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_HEADER_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_HEADER_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_INGREDIENTS_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_INGREDIENTS_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_INGREDIENTS_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_INGREDIENTS_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_STATISTICS_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_STATISTICS_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_STATISTICS_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_STATISTICS_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_TEXT;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_TIPS_BACKGROUND;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_TIPS_LAYOUT_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_TIPS_NAVIGATION_VIEW;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.INT_COLOR_RECIPE_DETAIL_TIPS_TOOLBAR;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentLanguageIsoCode;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sSecondNavigationTabSelected;

/* ************************************************************************************************/
/* *** Treat Main Activity Recipe Second Navigation
/* ************************************************************************************************/
public class RecipeSecondNavigationCaptainActivity extends AppCompatActivity
        implements
        SharedPreferences.OnSharedPreferenceChangeListener,
        NavigationView.OnNavigationItemSelectedListener,
        RecipeSecondNavigationHeaderFragment.OnListFragmentInteractionListener,
        RecipeSecondNavigationIngredientsFragment.OnListFragmentInteractionListener,
        RecipeSecondNavigationInstructionsFragment.OnListFragmentInteractionListener,
        RecipeSecondNavigationNutritionalFragment.OnListFragmentInteractionListener,
        RecipeSecondNavigationTipsFragment.OnListFragmentInteractionListener,
        RecipeSecondNavigationStatisticsFragment.OnListFragmentInteractionListener,
        RecipeSecondNavigationCommentsFragment.OnListFragmentInteractionListener {

    private FloatingActionButton floatingActionButton;
    private Toolbar toolbar;
    private AppBarLayout appBarLayout;
    private NavigationView navigationView;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private DrawerLayout drawerLayout;
    private TabLayout.Tab tabLayoutTabAt;
    private Intent intent;

    private final int[] tabLayoutIcons = {
            R.drawable.ic_recipe_header_white_24dp,
            R.drawable.ic_recipe_ingredients_white_24dp,
            R.drawable.ic_recipe_instructions_white_24dp,
            R.drawable.ic_recipe_nutritional_white_24dp,
            R.drawable.ic_recipe_tips_white_24dp,
            R.drawable.ic_recipe_comment_white_24dp,
            R.drawable.ic_recipe_statistics_white_24dp};

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
        setContentView(R.layout.recipe_second_navigation_activity);

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

        // .............................................................. Set Floating Action Button
        floatingActionButton = findViewById(R.id.floatingActionButton);

        switch (sSecondNavigationTabSelected) {

            case 0:
                floatingActionButton.show();
                break;

            case 1:
                floatingActionButton.show();
                break;

            case 2:
                floatingActionButton.show();
                break;

            case 3:
                floatingActionButton.show();
                break;

            case 4:
                floatingActionButton.show();
                break;

            case 5:
                floatingActionButton.show();
                break;

            case 6:
                floatingActionButton.hide();
                break;

        }

        // ..................................................................... Set TabLayout Icons
        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(tabLayoutIcons[0]);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(tabLayoutIcons[1]);
        Objects.requireNonNull(tabLayout.getTabAt(2)).setIcon(tabLayoutIcons[2]);
        Objects.requireNonNull(tabLayout.getTabAt(3)).setIcon(tabLayoutIcons[3]);
        Objects.requireNonNull(tabLayout.getTabAt(4)).setIcon(tabLayoutIcons[4]);
        Objects.requireNonNull(tabLayout.getTabAt(5)).setIcon(tabLayoutIcons[5]);
        Objects.requireNonNull(tabLayout.getTabAt(6)).setIcon(tabLayoutIcons[6]);

        // ........................................................................ Set up First Tab
        sSecondNavigationTabSelected = 0;
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

                switch (sSecondNavigationTabSelected) {
                    case 0:
                        intent = new Intent(getApplicationContext(), RecipeToolMaintenanceHeaderActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        intent = new Intent(getApplicationContext(), RecipeToolMaintenanceIngredientsActivity.class);
                        startActivity(intent);
                        break;

                    case 2:
                        intent = new Intent(getApplicationContext(), RecipeToolMaintenanceInstructionsActivity.class);
                        startActivity(intent);
                        break;

                    case 3:
                        intent = new Intent(getApplicationContext(), RecipeToolMaintenanceNutritionalActivity.class);
                        startActivity(intent);
                        break;

                    case 4:
                        intent = new Intent(getApplicationContext(), RecipeToolMaintenanceTipsActivity.class);
                        startActivity(intent);
                        break;

                    case 5:
                        intent = new Intent(getApplicationContext(), RecipeToolMaintenanceCommentsActivity.class);
                        startActivity(intent);
                        break;

                }
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
                        sSecondNavigationTabSelected = 0;
                        break;

                    case 1:
                        sSecondNavigationTabSelected = 1;
                        break;

                    case 2:
                        sSecondNavigationTabSelected = 2;
                        break;

                    case 3:
                        sSecondNavigationTabSelected = 3;
                        break;

                    case 4:
                        sSecondNavigationTabSelected = 4;
                        break;

                    case 5:
                        sSecondNavigationTabSelected = 5;
                        break;

                    case 6:
                        sSecondNavigationTabSelected = 6;
                        break;

                    default:
                        sSecondNavigationTabSelected = 0;
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

        getMenuInflater().inflate(R.menu.second_navigation_menu_activity, menu);
        return true;
    }

    /* ********************************************************************************************/
    /* *** Handle action bar item clicks here. The action bar will automatically handle clicks on
    /* *** the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
    /* ********************************************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuItem = item.getItemId();

        switch (menuItem) {

            case R.id.back:
                finish();
                break;

            case R.id.home:
                Intent intent1 = new Intent(getApplicationContext(), RecipeFirstNavigationCaptainActivity.class);
                startActivity(intent1);
                finish();
                break;

            case R.id.settings:
                Intent intent = new Intent(getApplicationContext(), ApplicationSettingActivity.class);
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
                intent = new Intent(getApplicationContext(), RecipeFirstNavigationCaptainActivity.class);
                startActivity(intent);
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

            case R.id.recipe_detail_header:
                sSecondNavigationTabSelected = 0;
                break;

            case R.id.recipe_detail_ingredients:
                sSecondNavigationTabSelected = 1;
                break;

            case R.id.recipe_detail_instructions:
                sSecondNavigationTabSelected = 2;
                break;

            case R.id.recipe_detail_nutrition:
                sSecondNavigationTabSelected = 3;
                break;

            case R.id.recipe_detail_tips:
                sSecondNavigationTabSelected = 4;
                break;

            case R.id.recipe_detail_comments:
                sSecondNavigationTabSelected = 5;
                break;

            case R.id.recipe_detail_statistics:
                sSecondNavigationTabSelected = 6;
                break;

            default:
                sSecondNavigationTabSelected = 0;
                break;
        }

        setFragmentColor();

        new FragmentAdapter(getSupportFragmentManager());

        tabLayoutTabAt = tabLayout.getTabAt(sSecondNavigationTabSelected);
        assert tabLayoutTabAt != null;
        tabLayoutTabAt.select();

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /* ********************************************************************************************/
    /* *** Set Fragment Colors
    /* ********************************************************************************************/
    private void setFragmentColor() {

        toolbar.setTitleTextColor(INT_COLOR_RECIPE_DETAIL_TEXT);

        switch (sSecondNavigationTabSelected) {

            case 0:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_TOOLBAR);
                floatingActionButton.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_NAVIGATION_VIEW);
                break;

            case 1:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INGREDIENTS_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INGREDIENTS_TOOLBAR);
                floatingActionButton.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INGREDIENTS_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INGREDIENTS_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INGREDIENTS_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INGREDIENTS_NAVIGATION_VIEW);
                break;

            case 2:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_TOOLBAR);
                floatingActionButton.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_INSTRUCTIONS_NAVIGATION_VIEW);
                break;

            case 3:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_TOOLBAR);
                floatingActionButton.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_NUTRITIONAL_NAVIGATION_VIEW);
                break;

            case 4:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_TIPS_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_TIPS_TOOLBAR);
                floatingActionButton.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_TIPS_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_TIPS_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_TIPS_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_TIPS_NAVIGATION_VIEW);
                break;

            case 5:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_COMMENTS_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_COMMENTS_TOOLBAR);
                floatingActionButton.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_COMMENTS_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_COMMENTS_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_COMMENTS_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_COMMENTS_NAVIGATION_VIEW);
                break;

            case 6:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_STATISTICS_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_STATISTICS_TOOLBAR);
                floatingActionButton.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_STATISTICS_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_STATISTICS_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_STATISTICS_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_STATISTICS_NAVIGATION_VIEW);
                break;

            default:
                appBarLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_LAYOUT_TOOLBAR);
                toolbar.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_TOOLBAR);
                floatingActionButton.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_TOOLBAR);
                tabLayout.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_LAYOUT_TOOLBAR);
                viewPager.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_BACKGROUND);
                navigationView.setBackgroundColor(INT_COLOR_RECIPE_DETAIL_HEADER_NAVIGATION_VIEW);
                break;
        }
    }

    /* ********************************************************************************************/
    /* *** Fragment onListFragmentInteraction RECIPE COMMENTS
    /* ********************************************************************************************/
    @Override
    public void onListFragmentInteraction(RecipeCommentsModel item) {

    }

    /* ********************************************************************************************/
    /* *** Fragment onListFragmentInteraction RECIPE HEADER
    /* ********************************************************************************************/
    @Override
    public void onListFragmentInteraction(RecipeHeaderModel item) {

    }

    /* ********************************************************************************************/
    /* *** Fragment onListFragmentInteraction RECIPE INGREDIENTS
    /* ********************************************************************************************/
    @Override
    public void onListFragmentInteraction(RecipeIngredientsModel item) {

    }

    /* ********************************************************************************************/
    /* *** Fragment onListFragmentInteraction RECIPE INSTRUCTIONS
    /* ********************************************************************************************/
    @Override
    public void onListFragmentInteraction(RecipeInstructionsModel item) {

    }

    /* ********************************************************************************************/
    /* *** Fragment onListFragmentInteraction RECIPE NUTRITIONAL
    /* ********************************************************************************************/
    @Override
    public void onListFragmentInteraction(RecipeNutritionalModel item) {

    }

    /* ********************************************************************************************/
    /* *** Fragment onListFragmentInteraction RECIPE TIPS
    /* ********************************************************************************************/
    @Override
    public void onListFragmentInteraction(RecipeTipsModel item) {

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }

    /* ********************************************************************************************/
    /* *** Fragment Adapter
    /* ********************************************************************************************/
    public class FragmentAdapter extends FragmentPagerAdapter {

        private final int position = 7;

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

                case 1:
                    sSecondNavigationTabSelected = 1;
                    return new RecipeSecondNavigationIngredientsFragment();

                case 2:
                    sSecondNavigationTabSelected = 2;
                    return new RecipeSecondNavigationInstructionsFragment();

                case 3:
                    sSecondNavigationTabSelected = 3;
                    return new RecipeSecondNavigationNutritionalFragment();

                case 4:
                    sSecondNavigationTabSelected = 4;
                    return new RecipeSecondNavigationTipsFragment();

                case 5:
                    sSecondNavigationTabSelected = 5;
                    return new RecipeSecondNavigationCommentsFragment();

                case 6:
                    sSecondNavigationTabSelected = 6;
                    return new RecipeSecondNavigationStatisticsFragment();

                default:
                    sSecondNavigationTabSelected = 0;
                    return new RecipeSecondNavigationHeaderFragment();

            }
        }

        /* ****************************************************************************************/
        /* *** Get fragment page title and named each tab layouts
        /* ****************************************************************************************/
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position) {

                case 0:
                    return getString(R.string.label_header);

                case 1:
                    return getString(R.string.label_ingredients);

                case 2:
                    return getString(R.string.label_instructions);

                case 3:
                    return getString(R.string.label_nutritional);

                case 4:
                    return getString(R.string.label_tips);

                case 5:
                    return getString(R.string.label_comments);

                case 6:
                    return getString(R.string.label_statistics);

                default:
                    return getString(R.string.label_header);
            }
        }
    }
}

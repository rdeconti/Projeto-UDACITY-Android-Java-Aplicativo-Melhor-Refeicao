package com.rosemeire.deconti.bestmeal.RecipeTool;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckInformationInput;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableTips;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableTips;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_C;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCommentText;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeName;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;

public class RecipeToolMaintenanceTipsActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // .............................................................................. Set Layout
        setContentView(R.layout.recipe_tool_maintenance_tips_activity);

        // ............................................................. Set Toolbar with back arrow
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.label_tips);
        getSupportActionBar().setSubtitle(R.string.label_maintenance);

        // ........................................................................ Set Progress Bar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        // ......................................................................... Set Recipe Name
        final TextView textView_recipe_name = findViewById(R.id.textView_recipe_name);



        final EditText editText_tips = findViewById(R.id.editText_tips);

        // ...................................................................... Set layout buttons
        Button button_change = findViewById(R.id.imageButton_change);
        Button button_delete = findViewById(R.id.imageButton_delete);
        Button button_insert = findViewById(R.id.imageButton_insert);

        if (sTypeCRUD.equals(CRUD_TYPE_C)) {

            button_change.setVisibility(View.INVISIBLE);
            button_delete.setVisibility(View.INVISIBLE);
            button_insert.setVisibility(View.VISIBLE);

        } else {

            button_change.setVisibility(View.VISIBLE);
            button_delete.setVisibility(View.VISIBLE);
            button_insert.setVisibility(View.INVISIBLE);

        }

        // ..................................................................... Set value of fields
        textView_recipe_name.setText(sCurrentRecipeName);

        if (sTypeCRUD.equals(CRUD_TYPE_C)) {

            editText_tips.setText(R.string.label_enter_text);

        } else {

            editText_tips.setText(sCurrentRecipeCommentText);

        }

        /* ****************************************************************************************/
        /* *** TREAT BUTTON CHANGE
        /* ****************************************************************************************/
        button_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                check_information_from_user();

                boolean ssReturnCode = SupportCheckInformationInput.SupportCheckInformationEmpty(editText_tips, getString(R.string.message_user_information_required));
                if (!sReturnCode) {
                    return;
                }

                Context context = getApplicationContext();
                String Text = editText_tips.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseFirebaseTableTips.RecipeTipsFirebaseUpdateSingle(
                        Text,
                        context);

                RecipeDatabaseSQLiteTableTips.RecipeTipsSQLiteUpdateSingle(
                        Text,
                        context);

                progressBar.setVisibility(View.INVISIBLE);

                new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_change));
                finish();


            }
        });

        /* ****************************************************************************************/
        /* *** TREAT BUTTON DELETE
        /* ****************************************************************************************/
        button_delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseSQLiteTableTips.RecipeTipsSQLiteDeleteSingle(getApplicationContext());
                RecipeDatabaseFirebaseTableTips.RecipeTipsFirebaseDeleteSingle();

                progressBar.setVisibility(View.INVISIBLE);

                new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_delete));
                finish();

            }
        });

        /* ****************************************************************************************/
        /* *** TREAT BUTTON INSERT
        /* ****************************************************************************************/
        button_insert.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                check_information_from_user();

                boolean sReturnCode = SupportCheckInformationInput.SupportCheckInformationEmpty(editText_tips, getString(R.string.message_user_information_required));
                if (!sReturnCode) {
                    return;
                }

                Context context = getApplicationContext();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.simple_data_format), Locale.US);
                String record_number = simpleDateFormat.format(new Date());

                String tips = editText_tips.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseFirebaseTableTips.RecipeTipsFirebaseCreate(
                        context,
                        sCurrentRecipeNumber,
                        record_number,
                        tips);

                RecipeDatabaseSQLiteTableTips.RecipeTipsSQLiteInsert(
                        context,
                        sCurrentRecipeNumber,
                        record_number,
                        tips);

                progressBar.setVisibility(View.INVISIBLE);

                new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_insert));
                finish();

            }
        });
    }

    /* ********************************************************************************************/
    /* *** TREAT CHECK INFORMATION GIVEN BY USER
    /* ********************************************************************************************/
    private void check_information_from_user() {



    }

    /* ********************************************************************************************/
    /* *** TREAT CLICK ON TOP RIGHT ARROW
    /* ********************************************************************************************/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    /* ********************************************************************************************/
    /* *** TREAT CLICK ON TOP RIGHT ARROW
    /* ********************************************************************************************/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /* ********************************************************************************************/
    /* *** Inflate menu: this adds items to the action bar if it is present.
    /* ********************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_navigation_menu_activity, menu);
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

            case R.id.home:

                Intent intent = new Intent(getApplicationContext(), RecipeFirstNavigationCaptainActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);

    }
}
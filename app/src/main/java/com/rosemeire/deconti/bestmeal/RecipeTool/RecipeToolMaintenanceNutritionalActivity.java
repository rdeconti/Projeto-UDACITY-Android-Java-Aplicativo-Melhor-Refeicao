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

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckInformationInput;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableNutritional;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableHeader;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableNutritional;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_C;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeName;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_calcium;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_carbs;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_cholesterol;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_energy;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_fat;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_fiber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_folate;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_iron;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrent_recipe_nutritional_magnesium;
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

public class RecipeToolMaintenanceNutritionalActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // .............................................................................. Set Layout
        setContentView(R.layout.recipe_tool_maintenance_nutritional_activity);

        // ............................................................. Set Toolbar with back arrow
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.label_header);
        getSupportActionBar().setSubtitle(R.string.label_maintenance);

        // ........................................................................ Set Progress Bar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        // ......................................................................... Set Recipe Name
        final EditText editTextValue_RecipeName = findViewById(R.id.textViewValue_RecipeName);

        final EditText editTextValue_recipe_nutritional_calcium = findViewById(R.id.textViewValue_Calcium);
        final EditText editTextValue_recipe_nutritional_carbs = findViewById(R.id.textViewValue_Carbs);
        final EditText editTextValue_recipe_nutritional_cholesterol = findViewById(R.id.textViewValue_Cholesterol);
        final EditText editTextValue_recipe_nutritional_monounsaturated = findViewById(R.id.textViewValue_Monounsaturated);
        final EditText editTextValue_recipe_nutritional_polyunsaturated = findViewById(R.id.textViewValue_Polyunsaturated);
        final EditText editTextValue_recipe_nutritional_saturated = findViewById(R.id.textViewValue_Saturated);
        final EditText editTextValue_recipe_nutritional_fat = findViewById(R.id.textViewValue_Fat);
        final EditText editTextValue_recipe_nutritional_trans = findViewById(R.id.textViewValue_Trans);
        final EditText editTextValue_recipe_nutritional_iron = findViewById(R.id.textViewValue_Iron);
        final EditText editTextValue_recipe_nutritional_fiber = findViewById(R.id.textViewValue_Fiber);
        final EditText editTextValue_recipe_nutritional_folate = findViewById(R.id.textViewValue_Folate);
        final EditText editTextValue_recipe_nutritional_potassium = findViewById(R.id.textViewValue_Potassium);
        final EditText editTextValue_recipe_nutritional_magnesium = findViewById(R.id.textViewValue_Magnesium);
        final EditText editTextValue_recipe_nutritional_sodium = findViewById(R.id.textViewValue_Sodium);
        final EditText editTextValue_recipe_nutritional_energy = findViewById(R.id.textViewValue_Energy);
        final EditText editTextValue_recipe_nutritional_niacin = findViewById(R.id.textViewValue_Niacin);
        final EditText editTextValue_recipe_nutritional_phosphorus = findViewById(R.id.textViewValue_Phosphorus);
        final EditText editTextValue_recipe_nutritional_protein = findViewById(R.id.textViewValue_Protein);
        final EditText editTextValue_recipe_nutritional_riboflavin = findViewById(R.id.textViewValue_Riboflavin);
        final EditText editTextValue_recipe_nutritional_sugars = findViewById(R.id.textViewValue_Sugars);
        final EditText editTextValue_recipe_nutritional_thiamin = findViewById(R.id.textViewValue_Thiamin);
        final EditText editTextValue_recipe_nutritional_vitamin_e = findViewById(R.id.textViewValue_Vitamin_E);
        final EditText editTextValue_recipe_nutritional_vitamin_a = findViewById(R.id.textViewValue_Vitamin_A);
        final EditText editTextValue_recipe_nutritional_vitamin_b12 = findViewById(R.id.textViewValue_Vitamin_B12);
        final EditText editTextValue_recipe_nutritional_vitamin_b6 = findViewById(R.id.textViewValue_Vitamin_B6);
        final EditText editTextValue_recipe_nutritional_vitamin_c = findViewById(R.id.textViewValue_Vitamin_C);
        final EditText editTextValue_recipe_nutritional_vitamin_d = findViewById(R.id.textViewValue_Vitamin_D);
        final EditText editTextValue_recipe_nutritional_vitamin_k = findViewById(R.id.textViewValue_Vitamin_K);

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
        editTextValue_RecipeName.setText(sCurrentRecipeName);

        if (sTypeCRUD.equals(CRUD_TYPE_C)) {
            
            editTextValue_recipe_nutritional_calcium.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_carbs.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_cholesterol.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_polyunsaturated .setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_saturated.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_fat.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_trans.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_iron.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_fiber.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_folate.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_potassium.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_magnesium.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_sodium.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_energy.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_niacin.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_phosphorus.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_protein.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_riboflavin.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_sugars.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_thiamin.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_vitamin_e.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_vitamin_a.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_vitamin_b12.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_vitamin_b6.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_vitamin_c.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_vitamin_d.setText(R.string.label_enter_text);
            editTextValue_recipe_nutritional_vitamin_k.setText(R.string.label_enter_text);
            
        } else {
            
            editTextValue_recipe_nutritional_calcium.setText(sCurrent_recipe_nutritional_calcium);
            editTextValue_recipe_nutritional_carbs.setText(sCurrent_recipe_nutritional_carbs);
            editTextValue_recipe_nutritional_cholesterol.setText(sCurrent_recipe_nutritional_cholesterol);
            editTextValue_recipe_nutritional_polyunsaturated .setText(sCurrent_recipe_nutritional_polyunsaturated);
            editTextValue_recipe_nutritional_saturated.setText(sCurrent_recipe_nutritional_saturated);
            editTextValue_recipe_nutritional_fat.setText(sCurrent_recipe_nutritional_fat);
            editTextValue_recipe_nutritional_trans.setText(sCurrent_recipe_nutritional_trans);
            editTextValue_recipe_nutritional_iron.setText(sCurrent_recipe_nutritional_iron);
            editTextValue_recipe_nutritional_fiber.setText(sCurrent_recipe_nutritional_fiber);
            editTextValue_recipe_nutritional_folate.setText(sCurrent_recipe_nutritional_folate);
            editTextValue_recipe_nutritional_potassium.setText(sCurrent_recipe_nutritional_potassium);
            editTextValue_recipe_nutritional_magnesium.setText(sCurrent_recipe_nutritional_magnesium);
            editTextValue_recipe_nutritional_sodium.setText(sCurrent_recipe_nutritional_sodium);
            editTextValue_recipe_nutritional_energy.setText(sCurrent_recipe_nutritional_energy);
            editTextValue_recipe_nutritional_niacin.setText(sCurrent_recipe_nutritional_niacin);
            editTextValue_recipe_nutritional_phosphorus.setText(sCurrent_recipe_nutritional_phosphorus);
            editTextValue_recipe_nutritional_protein.setText(sCurrent_recipe_nutritional_protein);
            editTextValue_recipe_nutritional_riboflavin.setText(sCurrent_recipe_nutritional_riboflavin);
            editTextValue_recipe_nutritional_sugars.setText(sCurrent_recipe_nutritional_sugars);
            editTextValue_recipe_nutritional_thiamin.setText(sCurrent_recipe_nutritional_thiamin);
            editTextValue_recipe_nutritional_vitamin_e.setText(sCurrent_recipe_nutritional_vitamin_e);
            editTextValue_recipe_nutritional_vitamin_a.setText(sCurrent_recipe_nutritional_vitamin_a);
            editTextValue_recipe_nutritional_vitamin_b12.setText(sCurrent_recipe_nutritional_vitamin_b12);
            editTextValue_recipe_nutritional_vitamin_b6.setText(sCurrent_recipe_nutritional_vitamin_b6);
            editTextValue_recipe_nutritional_vitamin_c.setText(sCurrent_recipe_nutritional_vitamin_c);
            editTextValue_recipe_nutritional_vitamin_d.setText(sCurrent_recipe_nutritional_vitamin_d);
            editTextValue_recipe_nutritional_vitamin_k.setText(sCurrent_recipe_nutritional_vitamin_k);

        }

        /* ****************************************************************************************/
        /* *** TREAT BUTTON CHANGE
        /* ****************************************************************************************/
        button_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                check_information_from_user();

                AtomicBoolean sReturnCode = new AtomicBoolean(false);

                sReturnCode.set(SupportCheckInformationInput.SupportCheckInformationEmpty(editTextValue_RecipeName, getString(R.string.message_user_information_required)));
                if (!sReturnCode.get()) {
                    return;
                }

                Context context = getApplicationContext();

                String recipe_nutritional_calcium = editTextValue_recipe_nutritional_calcium.getText().toString().trim();
                String recipe_nutritional_carbs = editTextValue_recipe_nutritional_carbs.getText().toString().trim();
                String recipe_nutritional_cholesterol = editTextValue_recipe_nutritional_cholesterol.getText().toString().trim();
                String recipe_nutritional_monounsaturated = editTextValue_recipe_nutritional_monounsaturated.getText().toString().trim();
                String recipe_nutritional_polyunsaturated = editTextValue_recipe_nutritional_polyunsaturated.getText().toString().trim();
                String recipe_nutritional_saturated = editTextValue_recipe_nutritional_saturated.getText().toString().trim();
                String recipe_nutritional_fat = editTextValue_recipe_nutritional_fat.getText().toString().trim();
                String recipe_nutritional_trans = editTextValue_recipe_nutritional_trans.getText().toString().trim();
                String recipe_nutritional_iron = editTextValue_recipe_nutritional_iron.getText().toString().trim();
                String recipe_nutritional_fiber = editTextValue_recipe_nutritional_fiber.getText().toString().trim();
                String recipe_nutritional_folate = editTextValue_recipe_nutritional_folate.getText().toString().trim();
                String recipe_nutritional_potassium = editTextValue_recipe_nutritional_potassium.getText().toString().trim();
                String recipe_nutritional_magnesium = editTextValue_recipe_nutritional_magnesium.getText().toString().trim();
                String recipe_nutritional_sodium = editTextValue_recipe_nutritional_sodium.getText().toString().trim();
                String recipe_nutritional_energy = editTextValue_recipe_nutritional_energy.getText().toString().trim();
                String recipe_nutritional_niacin = editTextValue_recipe_nutritional_niacin.getText().toString().trim();
                String recipe_nutritional_phosphorus = editTextValue_recipe_nutritional_phosphorus.getText().toString().trim();
                String recipe_nutritional_protein = editTextValue_recipe_nutritional_protein.getText().toString().trim();
                String recipe_nutritional_riboflavin = editTextValue_recipe_nutritional_riboflavin.getText().toString().trim();
                String recipe_nutritional_sugars = editTextValue_recipe_nutritional_sugars.getText().toString().trim();
                String recipe_nutritional_thiamin = editTextValue_recipe_nutritional_thiamin.getText().toString().trim();
                String recipe_nutritional_vitamin_e = editTextValue_recipe_nutritional_vitamin_e.getText().toString().trim();
                String recipe_nutritional_vitamin_a = editTextValue_recipe_nutritional_vitamin_a.getText().toString().trim();
                String recipe_nutritional_vitamin_b12 = editTextValue_recipe_nutritional_vitamin_b12.getText().toString().trim();
                String recipe_nutritional_vitamin_b6 = editTextValue_recipe_nutritional_vitamin_b6.getText().toString().trim();
                String recipe_nutritional_vitamin_c = editTextValue_recipe_nutritional_vitamin_c.getText().toString().trim();
                String recipe_nutritional_vitamin_d = editTextValue_recipe_nutritional_vitamin_d.getText().toString().trim();
                String recipe_nutritional_vitamin_k = editTextValue_recipe_nutritional_vitamin_k.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseSQLiteTableHeader.RecipeHeaderCheckLocalStorageByNumber(getApplicationContext());

                RecipeDatabaseFirebaseTableNutritional.RecipeNutritionalFirebaseUpdateSingle(
                        recipe_nutritional_calcium,
                        recipe_nutritional_carbs,
                        recipe_nutritional_cholesterol,
                        recipe_nutritional_monounsaturated,
                        recipe_nutritional_polyunsaturated,
                        recipe_nutritional_saturated,
                        recipe_nutritional_fat,
                        recipe_nutritional_trans,
                        recipe_nutritional_iron,
                        recipe_nutritional_fiber,
                        recipe_nutritional_folate,
                        recipe_nutritional_potassium,
                        recipe_nutritional_magnesium,
                        recipe_nutritional_sodium,
                        recipe_nutritional_energy,
                        recipe_nutritional_niacin,
                        recipe_nutritional_phosphorus,
                        recipe_nutritional_protein,
                        recipe_nutritional_riboflavin,
                        recipe_nutritional_sugars,
                        recipe_nutritional_thiamin,
                        recipe_nutritional_vitamin_e,
                        recipe_nutritional_vitamin_a,
                        recipe_nutritional_vitamin_b12,
                        recipe_nutritional_vitamin_b6,
                        recipe_nutritional_vitamin_c,
                        recipe_nutritional_vitamin_d,
                        recipe_nutritional_vitamin_k,
                        context);

                RecipeDatabaseSQLiteTableNutritional.RecipeNutritionalSQLiteUpdateSingle(
                        recipe_nutritional_calcium,
                        recipe_nutritional_carbs,
                        recipe_nutritional_cholesterol,
                        recipe_nutritional_monounsaturated,
                        recipe_nutritional_polyunsaturated,
                        recipe_nutritional_saturated,
                        recipe_nutritional_fat,
                        recipe_nutritional_trans,
                        recipe_nutritional_iron,
                        recipe_nutritional_fiber,
                        recipe_nutritional_folate,
                        recipe_nutritional_potassium,
                        recipe_nutritional_magnesium,
                        recipe_nutritional_sodium,
                        recipe_nutritional_energy,
                        recipe_nutritional_niacin,
                        recipe_nutritional_phosphorus,
                        recipe_nutritional_protein,
                        recipe_nutritional_riboflavin,
                        recipe_nutritional_sugars,
                        recipe_nutritional_thiamin,
                        recipe_nutritional_vitamin_e,
                        recipe_nutritional_vitamin_a,
                        recipe_nutritional_vitamin_b12,
                        recipe_nutritional_vitamin_b6,
                        recipe_nutritional_vitamin_c,
                        recipe_nutritional_vitamin_d,
                        recipe_nutritional_vitamin_k,
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

                RecipeDatabaseSQLiteTableNutritional.RecipeNutritionalSQLiteDeleteAll(getApplicationContext());

                RecipeDatabaseFirebaseTableNutritional.RecipeNutritionalFirebaseDeleteAll();

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

                AtomicBoolean sReturnCode = new AtomicBoolean(false);

                sReturnCode.set(SupportCheckInformationInput.SupportCheckInformationEmpty(editTextValue_RecipeName, getString(R.string.message_user_information_required)));
                if (!sReturnCode.get()) {
                    return;
                }

                Context context = getApplicationContext();

                String recipe_nutritional_calcium = editTextValue_recipe_nutritional_calcium.getText().toString().trim();
                String recipe_nutritional_carbs = editTextValue_recipe_nutritional_carbs.getText().toString().trim();
                String recipe_nutritional_cholesterol = editTextValue_recipe_nutritional_cholesterol.getText().toString().trim();
                String recipe_nutritional_monounsaturated = editTextValue_recipe_nutritional_monounsaturated.getText().toString().trim();
                String recipe_nutritional_polyunsaturated = editTextValue_recipe_nutritional_polyunsaturated.getText().toString().trim();
                String recipe_nutritional_saturated = editTextValue_recipe_nutritional_saturated.getText().toString().trim();
                String recipe_nutritional_fat = editTextValue_recipe_nutritional_fat.getText().toString().trim();
                String recipe_nutritional_trans = editTextValue_recipe_nutritional_trans.getText().toString().trim();
                String recipe_nutritional_iron = editTextValue_recipe_nutritional_iron.getText().toString().trim();
                String recipe_nutritional_fiber = editTextValue_recipe_nutritional_fiber.getText().toString().trim();
                String recipe_nutritional_folate = editTextValue_recipe_nutritional_folate.getText().toString().trim();
                String recipe_nutritional_potassium = editTextValue_recipe_nutritional_potassium.getText().toString().trim();
                String recipe_nutritional_magnesium = editTextValue_recipe_nutritional_magnesium.getText().toString().trim();
                String recipe_nutritional_sodium = editTextValue_recipe_nutritional_sodium.getText().toString().trim();
                String recipe_nutritional_energy = editTextValue_recipe_nutritional_energy.getText().toString().trim();
                String recipe_nutritional_niacin = editTextValue_recipe_nutritional_niacin.getText().toString().trim();
                String recipe_nutritional_phosphorus = editTextValue_recipe_nutritional_phosphorus.getText().toString().trim();
                String recipe_nutritional_protein = editTextValue_recipe_nutritional_protein.getText().toString().trim();
                String recipe_nutritional_riboflavin = editTextValue_recipe_nutritional_riboflavin.getText().toString().trim();
                String recipe_nutritional_sugars = editTextValue_recipe_nutritional_sugars.getText().toString().trim();
                String recipe_nutritional_thiamin = editTextValue_recipe_nutritional_thiamin.getText().toString().trim();
                String recipe_nutritional_vitamin_e = editTextValue_recipe_nutritional_vitamin_e.getText().toString().trim();
                String recipe_nutritional_vitamin_a = editTextValue_recipe_nutritional_vitamin_a.getText().toString().trim();
                String recipe_nutritional_vitamin_b12 = editTextValue_recipe_nutritional_vitamin_b12.getText().toString().trim();
                String recipe_nutritional_vitamin_b6 = editTextValue_recipe_nutritional_vitamin_b6.getText().toString().trim();
                String recipe_nutritional_vitamin_c = editTextValue_recipe_nutritional_vitamin_c.getText().toString().trim();
                String recipe_nutritional_vitamin_d = editTextValue_recipe_nutritional_vitamin_d.getText().toString().trim();
                String recipe_nutritional_vitamin_k = editTextValue_recipe_nutritional_vitamin_k.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseFirebaseTableNutritional.RecipeNutritionalFirebaseCreate(
                        sCurrentRecipeNumber,
                        recipe_nutritional_calcium,
                        recipe_nutritional_carbs,
                        recipe_nutritional_cholesterol,
                        recipe_nutritional_monounsaturated,
                        recipe_nutritional_polyunsaturated,
                        recipe_nutritional_saturated,
                        recipe_nutritional_fat,
                        recipe_nutritional_trans,
                        recipe_nutritional_iron,
                        recipe_nutritional_fiber,
                        recipe_nutritional_folate,
                        recipe_nutritional_potassium,
                        recipe_nutritional_magnesium,
                        recipe_nutritional_sodium,
                        recipe_nutritional_energy,
                        recipe_nutritional_niacin,
                        recipe_nutritional_phosphorus,
                        recipe_nutritional_protein,
                        recipe_nutritional_riboflavin,
                        recipe_nutritional_sugars,
                        recipe_nutritional_thiamin,
                        recipe_nutritional_vitamin_e,
                        recipe_nutritional_vitamin_a,
                        recipe_nutritional_vitamin_b12,
                        recipe_nutritional_vitamin_b6,
                        recipe_nutritional_vitamin_c,
                        recipe_nutritional_vitamin_d,
                        recipe_nutritional_vitamin_k,
                        context);

                RecipeDatabaseSQLiteTableNutritional.RecipeNutritionalSQLiteInsert(
                        sCurrentRecipeNumber,
                        recipe_nutritional_calcium,
                        recipe_nutritional_carbs,
                        recipe_nutritional_cholesterol,
                        recipe_nutritional_monounsaturated,
                        recipe_nutritional_polyunsaturated,
                        recipe_nutritional_saturated,
                        recipe_nutritional_fat,
                        recipe_nutritional_trans,
                        recipe_nutritional_iron,
                        recipe_nutritional_fiber,
                        recipe_nutritional_folate,
                        recipe_nutritional_potassium,
                        recipe_nutritional_magnesium,
                        recipe_nutritional_sodium,
                        recipe_nutritional_energy,
                        recipe_nutritional_niacin,
                        recipe_nutritional_phosphorus,
                        recipe_nutritional_protein,
                        recipe_nutritional_riboflavin,
                        recipe_nutritional_sugars,
                        recipe_nutritional_thiamin,
                        recipe_nutritional_vitamin_e,
                        recipe_nutritional_vitamin_a,
                        recipe_nutritional_vitamin_b12,
                        recipe_nutritional_vitamin_b6,
                        recipe_nutritional_vitamin_c,
                        recipe_nutritional_vitamin_d,
                        recipe_nutritional_vitamin_k,
                        context);

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

package com.rosemeire.deconti.bestmeal.RecipeTool;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckInformationInput;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingExceptionError;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableComments;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableHeader;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableIngredients;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableInstructions;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableNutritional;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTablePurchase;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableTips;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableComments;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableHeader;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableIngredients;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableInstructions;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableNutritional;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTablePurchase;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableTips;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_C;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_RECIPES_1;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_RECIPES_2;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_BOOK;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_NONE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeCuisine;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeDiet;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeDifficult;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionPhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeIntolerance;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeName;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeOccasion;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePrice;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeServings;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeTime;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeType;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sPreferredSettingsLanguage;

public class RecipeToolMaintenanceHeaderActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private ImageView imageView_recipePhoto;
    private Button button_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // .............................................................................. Set Layout
        setContentView(R.layout.recipe_tool_maintenance_header_activity);

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

        imageView_recipePhoto = findViewById(R.id.imageView_recipePhoto);

        final Spinner spinner_Diet = findViewById(R.id.spinner_diet);
        final Spinner spinner_Type = findViewById(R.id.spinner_type);
        final Spinner spinner_Cuisine = findViewById(R.id.spinner_cuisine);
        final Spinner spinner_Intolerance = findViewById(R.id.spinner_intolerance);
        final Spinner spinner_Occasion = findViewById(R.id.spinner_occasion);
        final Spinner spinner_Servings = findViewById(R.id.spinner_servings);
        final Spinner spinner_Price = findViewById(R.id.spinner_price);
        final Spinner spinner_Time = findViewById(R.id.spinner_time);
        final Spinner spinner_Difficult = findViewById(R.id.spinner_difficult);

        Button button_change = findViewById(R.id.imageButton_change);
        Button button_delete = findViewById(R.id.imageButton_delete);
        Button button_insert = findViewById(R.id.imageButton_insert);
        Button button_choose = findViewById(R.id.imageButton_choose);

        button_load = findViewById(R.id.imageButton_load);

        sCurrentRecipePhoto = getString(R.string.label_not_available);

        button_load.setVisibility(View.INVISIBLE);
        button_choose.setVisibility(View.VISIBLE);

        // ...................................................................... Set layout buttons
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

            editTextValue_RecipeName.setText(null);

            spinner_Diet.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));
            spinner_Type.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));
            spinner_Cuisine.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));
            spinner_Intolerance.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));
            spinner_Occasion.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));
            spinner_Servings.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));
            spinner_Price.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));
            spinner_Time.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));
            spinner_Difficult.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(getString(R.string.label_Other)));


        } else {

            editTextValue_RecipeName.setText(sCurrentRecipeName);

            spinner_Diet.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipeDiet));
            spinner_Type.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipeType));
            spinner_Cuisine.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipeCuisine));
            spinner_Intolerance.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipeIntolerance));
            spinner_Occasion.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipeOccasion));
            spinner_Servings.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipeServings));
            spinner_Price.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipePrice));
            spinner_Time.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipeTime));
            spinner_Difficult.setSelection(((ArrayAdapter<String>)spinner_Diet.getAdapter()).getPosition(sCurrentRecipeDifficult));

        }

        /* ****************************************************************************************/
        /* *** TREAT BUTTON CHOOSE
        /* ****************************************************************************************/
        button_choose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                get_image_from_device();

                button_load.setVisibility(View.VISIBLE);

            }
        });


        /* ****************************************************************************************/
        /* *** TREAT BUTTON LOAD
        /* ****************************************************************************************/
        button_load.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                load_image_on_firebase_storage();

                load_image_on_sqlite_storage();

            }
        });

        /* ****************************************************************************************/
        /* *** TREAT BUTTON CHANGE
        /* ****************************************************************************************/
        button_change.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AtomicBoolean sReturnCode = new AtomicBoolean(false);

                sReturnCode.set(SupportCheckInformationInput.SupportCheckInformationEmpty(editTextValue_RecipeName, getString(R.string.message_user_information_required)));
                if (!sReturnCode.get()) {
                    return;
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.simple_data_format), Locale.US);
                String record_number = simpleDateFormat.format(new Date());

                Context context = getApplicationContext();

                String RecipeName = editTextValue_RecipeName.getText().toString().trim();
                String Diet = String.valueOf(spinner_Diet.getSelectedItem());
                String Type = String.valueOf(spinner_Type.getSelectedItem());
                String Cuisine = String.valueOf(spinner_Cuisine.getSelectedItem());
                String Intolerance = String.valueOf(spinner_Intolerance.getSelectedItem());
                String Occasion = String.valueOf(spinner_Occasion.getSelectedItem());
                String Servings = String.valueOf(spinner_Servings.getSelectedItem());
                String Price = String.valueOf(spinner_Price.getSelectedItem());
                String Time = String.valueOf(spinner_Time.getSelectedItem());
                String Difficult = String.valueOf(spinner_Difficult.getSelectedItem());

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseFirebaseTableHeader.RecipeHeaderFirebaseUpdateSingle(
                        RecipeName,
                        record_number,
                        sCurrentRecipePhoto,
                        Diet,
                        Type,
                        Cuisine,
                        Intolerance,
                        Occasion,
                        Servings,
                        Price,
                        Time,
                        Difficult);

                RecipeDatabaseSQLiteTableHeader.RecipeHeaderCheckLocalStorageByNumber(getApplicationContext());

                RecipeDatabaseSQLiteTableHeader.RecipeHeaderSQLiteUpdate(
                        context,
                        RecipeName,
                        record_number,
                        sCurrentRecipePhoto,
                        Diet,
                        Type,
                        Cuisine,
                        Intolerance,
                        Occasion,
                        Servings,
                        Price,
                        Time,
                        Difficult);

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

                RecipeDatabaseSQLiteTableIngredients.RecipeIngredientsSQLiteDeleteAll(getApplicationContext());
                RecipeDatabaseSQLiteTableInstructions.RecipeInstructionsSQLiteDeleteAll(getApplicationContext());
                RecipeDatabaseSQLiteTableNutritional.RecipeNutritionalSQLiteDeleteAll(getApplicationContext());
                RecipeDatabaseSQLiteTablePurchase.RecipePurchaseSQLiteDeleteAll(getApplicationContext());
                RecipeDatabaseSQLiteTableTips.RecipeTipsSQLiteDeleteAll(getApplicationContext());
                RecipeDatabaseSQLiteTableComments.RecipeCommentsSQLiteDeleteAll(getApplicationContext());
                RecipeDatabaseSQLiteTableHeader.RecipeHeaderSQLiteDeleteAll(getApplicationContext());

                RecipeDatabaseFirebaseTableIngredients.RecipeIngredientsFirebaseDeleteAll();
                RecipeDatabaseFirebaseTableInstructions.RecipeInstructionsFirebaseDeleteAll();
                RecipeDatabaseFirebaseTableNutritional.RecipeNutritionalFirebaseDeleteAll();
                RecipeDatabaseFirebaseTablePurchase.RecipePurchaseFirebaseDeleteAll();
                RecipeDatabaseFirebaseTableTips.RecipeTipsFirebaseDeleteAll();
                RecipeDatabaseFirebaseTableComments.RecipeCommentsFirebaseDeleteAll();
                RecipeDatabaseFirebaseTableHeader.RecipeHeaderFirebaseDeleteAll();

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

                AtomicBoolean sReturnCode = new AtomicBoolean(false);

                sReturnCode.set(SupportCheckInformationInput.SupportCheckInformationEmpty(editTextValue_RecipeName, getString(R.string.message_user_information_required)));
                if (!sReturnCode.get()) {
                    return;
                }

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.simple_data_format), Locale.US);
                String record_number = simpleDateFormat.format(new Date());

                Context context = getApplicationContext();

                String RecipeName = editTextValue_RecipeName.getText().toString().trim();
                String Diet = String.valueOf(spinner_Diet.getSelectedItem());
                String Type = String.valueOf(spinner_Type.getSelectedItem());
                String Cuisine = String.valueOf(spinner_Cuisine.getSelectedItem());
                String Intolerance = String.valueOf(spinner_Intolerance.getSelectedItem());
                String Occasion = String.valueOf(spinner_Occasion.getSelectedItem());
                String Servings = String.valueOf(spinner_Servings.getSelectedItem());
                String Price = String.valueOf(spinner_Price.getSelectedItem());
                String Time = String.valueOf(spinner_Time.getSelectedItem());
                String Difficult = String.valueOf(spinner_Difficult.getSelectedItem());

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseFirebaseTableHeader.RecipeHeaderFirebaseCreate(
                        record_number,
                        RecipeName,
                        sCurrentUserFirebaseUid,
                        record_number,
                        sCurrentRecipePhoto,
                        RECIPE_TREAT_BOOK,
                        sPreferredSettingsLanguage,
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        record_number,
                        Diet,
                        Type,
                        Cuisine,
                        Intolerance,
                        Occasion,
                        Servings,
                        Price,
                        Time,
                        Difficult,
                        context);

                RecipeDatabaseSQLiteTableHeader.RecipeHeaderSQLiteInsert(
                        context,
                        record_number,
                        RecipeName,
                        sCurrentUserFirebaseUid,
                        record_number,
                        sCurrentRecipePhoto,
                        RECIPE_TREAT_NONE,
                        RECIPE_TREAT_NONE,
                        RECIPE_TREAT_NONE,
                        RECIPE_TREAT_NONE,
                        RECIPE_TREAT_NONE,
                        RECIPE_TREAT_NONE,
                        RECIPE_TREAT_NONE,
                        RECIPE_TREAT_NONE,
                        sPreferredSettingsLanguage,
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        "0",
                        record_number,
                        Diet,
                        Type,
                        Cuisine,
                        Intolerance,
                        Occasion,
                        Servings,
                        Price,
                        Time,
                        Difficult);

                progressBar.setVisibility(View.INVISIBLE);

                new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_insert));
                finish();

            }
        });
    }

    /* ********************************************************************************************/
    /* *** CHOOSE IMAGE TO LOAD
    /* ********************************************************************************************/
    private void get_image_from_device() {

        Intent intent = new Intent();
        intent.setType(getString(R.string.label_load_image_directory));
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, getString(R.string.label_load_image_title)), PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);

                Glide.with(getApplicationContext())
                        .load(bitmap)
                        .apply(RequestOptions.centerCropTransform())
                        .into(imageView_recipePhoto);

            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    /* ********************************************************************************************/
    /* *** LOAD IMAGE INTO SQLITE STORAGE
    /* ********************************************************************************************/
    private void load_image_on_sqlite_storage() {

        downloadRecipePhotoInternalStorage(getApplicationContext());

    }

    /* ********************************************************************************************/
    /* *** LOAD IMAGE INTO FIREBASE STORAGE
    /* ********************************************************************************************/
    private void load_image_on_firebase_storage() {

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle(getString(R.string.label_load_image_uploading));
            progressDialog.show();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.simple_data_format), Locale.US);
            String record_number = simpleDateFormat.format(new Date());

            sCurrentRecipePhoto = sCurrentUserFirebaseUid + getString(R.string.label_underscore) + record_number;

            storageReference = storageReference.child(PATH_RECIPES_1 + sCurrentRecipePhoto);

            storageReference.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_image_loaded));
                    }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception error) {
                            progressDialog.dismiss();
                            String ClassName = String.class.getName();
                            new SupportHandlingExceptionError(ClassName, error, getApplicationContext());
                            new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_image_not_loaded));
                        }

                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage(getString(R.string.label_load_image_uploaded) + (int)progress + getString(R.string.label_load_image_percent));
                        }
                    });
        }
    }


    /* ********************************************************************************************/
    /* *** Download image to internal storage
    /* ********************************************************************************************/
    private static void downloadRecipePhotoInternalStorage(final Context context) {

        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();

        try {

            File directory = context.getDir(PATH_RECIPES_2, Context.MODE_PRIVATE);
            final File newFile = new File(directory, sCurrentRecipeInstructionPhoto + FILE_TYPE);

            firebaseStorage.getReference().child(PATH_RECIPES_1).child(sCurrentRecipeInstructionPhoto + FILE_TYPE).getFile(newFile).

                    addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                            try {
                                //noinspection ResultOfMethodCallIgnored
                                newFile.createNewFile();

                            } catch (IOException error) {

                                String ClassName = String.class.getName();
                                new SupportHandlingExceptionError(ClassName, error, context);

                            }

                        }

                    }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception error) {
                    String ClassName = String.class.getName();
                    new SupportHandlingExceptionError(ClassName, error, context);

                }

            });

        } catch (Exception error) {
            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }
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

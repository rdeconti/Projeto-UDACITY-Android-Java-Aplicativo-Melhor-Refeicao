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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableInstructions;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableInstructions;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_C;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_INSTRUCTIONS_1;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_INSTRUCTIONS_2;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionPhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionText;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeName;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipePhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;

public class RecipeToolMaintenanceInstructionsActivity extends AppCompatActivity {

    private ProgressBar progressBar;

    private Uri filePath;
    private final int PICK_IMAGE_REQUEST = 71;
    private ImageView imageView_recipePhoto;
    private Button button_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // .............................................................................. Set Layout
        setContentView(R.layout.recipe_tool_maintenance_instructions_activity);

        // ............................................................. Set Toolbar with back arrow
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.label_instructions);
        getSupportActionBar().setSubtitle(R.string.label_maintenance);

        // ........................................................................ Set Progress Bar
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        // ......................................................................... Set Recipe Name
        final TextView textView_recipe_name = findViewById(R.id.textView_recipe_name);
        final EditText editText_instructions = findViewById(R.id.editText_instructions);

        // ...................................................................... Set layout buttons
        Button button_change = findViewById(R.id.imageButton_change);
        Button button_delete = findViewById(R.id.imageButton_delete);
        Button button_insert = findViewById(R.id.imageButton_insert);
        Button button_choose = findViewById(R.id.imageButton_choose);

        button_load = findViewById(R.id.imageButton_load);

        sCurrentRecipePhoto = getString(R.string.label_not_available);

        button_load.setVisibility(View.INVISIBLE);
        button_choose.setVisibility(View.VISIBLE);

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

            editText_instructions.setText(R.string.label_enter_text);

        } else {

            editText_instructions.setText(sCurrentRecipeInstructionText);

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

                boolean sReturnCode = SupportCheckInformationInput.SupportCheckInformationEmpty(editText_instructions, getString(R.string.message_user_information_required));
                if (!sReturnCode) {
                    return;
                }

                Context context = getApplicationContext();
                String Text = editText_instructions.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseFirebaseTableInstructions.RecipeInstructionsFirebaseUpdateSingle(
                        Text,
                        context);

                RecipeDatabaseSQLiteTableInstructions.RecipeInstructionsSQLiteUpdateSingle(
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

                RecipeDatabaseSQLiteTableInstructions.RecipeInstructionsSQLiteDeleteSingle(getApplicationContext());
                RecipeDatabaseFirebaseTableInstructions.RecipeInstructionsFirebaseDeleteSingle();

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

                boolean sReturnCode = SupportCheckInformationInput.SupportCheckInformationEmpty(editText_instructions, getString(R.string.message_user_information_required));
                if (!sReturnCode) {
                    return;
                }

                Context context = getApplicationContext();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(getString(R.string.simple_data_format), Locale.US);
                String record_number = simpleDateFormat.format(new Date());

                String instructions = editText_instructions.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);

                RecipeDatabaseFirebaseTableInstructions.RecipeInstructionsFirebaseCreate(
                        context,
                        sCurrentRecipeNumber,
                        record_number,
                        sCurrentUserFirebaseUid,
                        instructions);

                RecipeDatabaseSQLiteTableInstructions.RecipeInstructionsSQLiteInsert(
                        context,
                        sCurrentRecipeNumber,
                        record_number,
                        sCurrentUserFirebaseUid,
                        instructions);

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

            storageReference = storageReference.child(PATH_INSTRUCTIONS_1 + sCurrentRecipePhoto);

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

            File directory = context.getDir(PATH_INSTRUCTIONS_2, Context.MODE_PRIVATE);
            final File newFile = new File(directory, sCurrentRecipeInstructionPhoto + FILE_TYPE);

            firebaseStorage.getReference().child(PATH_INSTRUCTIONS_1).child(sCurrentRecipeInstructionPhoto + FILE_TYPE).getFile(newFile).

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

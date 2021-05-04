package com.rosemeire.deconti.bestmeal.ApplicationAuthorization;

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

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckNetworkAvailability;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSettingLocalization;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportSharedPreferencesGet;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;

import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sFirebaseAuth;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseEmail;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;
import static com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckInformationEmail.SupportCheckEmail;

/* ************************************************************************************************/
/* *** TREAT AUTHORIZATION: USER LOGIN
/* ************************************************************************************************/
public class AuthorizationLogInActivity extends AppCompatActivity {

    private EditText user_email;
    private EditText user_password;
    private ProgressBar progressBar;
    private SharedPreferences sharedPreferences;

    /* ********************************************************************************************/
    /* *** TREAT ON CREATE
    /* ********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        checkNetworkAvailability();

        checkUserAgreement();

        new SupportSharedPreferencesGet(this);
        new SupportSettingLocalization();

        setContentView(R.layout.application_authorization_login_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitle(R.string.label_authorization);
        toolbar.setSubtitle(R.string.label_login);

        user_email = findViewById(R.id.editText_user_email);
        user_password = findViewById(R.id.editText_user_password);

        Button button_signin = findViewById(R.id.button_signin);
        Button button_login = findViewById(R.id.button_login);
        Button button_reset = findViewById(R.id.button_reset);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        sFirebaseAuth = FirebaseAuth.getInstance();

        /* ****************************************************************************************/
        /* *** TREAT BUTTON LOGIN
        /* ****************************************************************************************/
        button_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String email = user_email.getText().toString();
                String password = user_password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_email));
                    return;
                }

                sReturnCode = SupportCheckEmail(email);
                if (!sReturnCode) {
                    new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_email_invalid));
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_password));
                    return;
                }

                if (password.length() < 6) {
                    new SupportMessageToast(getApplicationContext(), getString(R.string.message_password_short));
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                sFirebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(AuthorizationLogInActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {
                                    new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_logIn_failed));
                                    return;
                                }

                                progressBar.setVisibility(View.INVISIBLE);

                                sCurrentUserFirebaseUid = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getUid();
                                sCurrentUserFirebaseEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();

                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(getString(R.string.label_userFirebaseId), sCurrentUserFirebaseUid);
                                editor.putString(getString(R.string.label_UserFirebaseEmail), sCurrentUserFirebaseEmail);
                                editor.apply();

                                startActivity(new Intent(getApplicationContext(), RecipeFirstNavigationCaptainActivity.class));
                                finish();
                            }

                        });
            }
        });

        /* ****************************************************************************************/
        /* *** TREAT BUTTON SIGN IN
        /* ****************************************************************************************/
        button_signin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AuthorizationSignInActivity.class));

            }
        });

        /* ****************************************************************************************/
        /* *** TREAT BUTTON RESET
        /* ****************************************************************************************/
        button_reset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AuthorizationResetActivity.class));

            }
        });

    }

    /* ********************************************************************************************/
    /* *** Check network availability
    /* ********************************************************************************************/
    private void checkNetworkAvailability() {

        sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(getApplicationContext());

        if (!sReturnCode) {

            AlertDialog.Builder builder = new AlertDialog.Builder(AuthorizationLogInActivity.this);

            builder.setTitle(R.string.label_user_warning);
            builder.setMessage(R.string.message_NetworkNotAvailableLogin);

            builder.setPositiveButton(R.string.label_continue, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(), RecipeFirstNavigationCaptainActivity.class));
                    finish();
                }
            });

            builder.setNegativeButton(R.string.label_stop, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            builder.show();

        }
    }

    /* ********************************************************************************************/
    /* *** Obtain / Initialize Shared Preferences
    /* ********************************************************************************************/
    private void checkUserAgreement() {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        boolean userAgreement = sharedPreferences.getBoolean(getString(R.string.label_user_agreement), false);

        AlertDialog.Builder builder = new AlertDialog.Builder(AuthorizationLogInActivity.this);

        if (!userAgreement) {
            builder.setTitle(R.string.label_user_agreement);
            builder.setPositiveButton(R.string.label_continue, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putBoolean(getString(R.string.label_user_agreement), true);
                    editor.apply();
                }
            });

            builder.setMessage(R.string.message_TermsAndConditions);
            builder.show();
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
}
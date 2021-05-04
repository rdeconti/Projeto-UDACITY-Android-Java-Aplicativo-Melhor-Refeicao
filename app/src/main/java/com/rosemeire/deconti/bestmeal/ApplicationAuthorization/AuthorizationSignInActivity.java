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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.rosemeire.deconti.bestmeal.R;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sFirebaseAuth;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;
import static com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckInformationEmail.SupportCheckEmail;

/* ************************************************************************************************/
/* *** TREAT AUTHORIZATION: CHANGE USER SIGN IN
/* ************************************************************************************************/
public class AuthorizationSignInActivity extends AppCompatActivity {

    private EditText editText_email;
    private EditText editText_password;
    private ProgressBar progressBar;

    /* ********************************************************************************************/
    /* *** TREAT ON CREATE
    /* ********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(getApplicationContext());
        if (!sReturnCode) {
            new SupportMessageToast(getApplicationContext(), getString(R.string.message_network_not_available));
            startActivity(new Intent(getApplicationContext(), AuthorizationLogInActivity.class));
            finish();
        }

        sFirebaseAuth = FirebaseAuth.getInstance();

        setContentView(R.layout.application_authorization_signin_activity);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        toolbar.setTitle(R.string.label_authorization);
        toolbar.setSubtitle(R.string.label_signIn);

        editText_email = findViewById(R.id.editText_user_email);
        editText_password = findViewById(R.id.editText_user_password);

        Button button_signin = findViewById(R.id.button_signin);
        Button button_reset = findViewById(R.id.button_reset);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        /* ****************************************************************************************/
        /* *** TREAT BUTTON RESET
        /* ****************************************************************************************/
        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(), AuthorizationResetActivity.class));
                finish();
            }
        });

        /* ****************************************************************************************/
        /* *** TREAT BUTTON SIGN IN
        /* ****************************************************************************************/
        button_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = editText_email.getText().toString().trim();
                String password = editText_password.getText().toString().trim();

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

                sFirebaseAuth = FirebaseAuth.getInstance();

                sFirebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(AuthorizationSignInActivity.this, new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (!task.isSuccessful()) {

                                    new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_signIn_failed));

                                } else {

                                    new SupportMessageToast(getApplicationContext(), getString(R.string.message_user_signIn_success));
                                    progressBar.setVisibility(View.INVISIBLE);
                                    startActivity(new Intent(getApplicationContext(), AuthorizationLogInActivity.class));
                                    finish();
                                }
                            }
                        });
            }
        });
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

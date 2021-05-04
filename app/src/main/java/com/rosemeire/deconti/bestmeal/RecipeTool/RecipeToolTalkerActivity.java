package com.rosemeire.deconti.bestmeal.RecipeTool;

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
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;

import java.util.Locale;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionText;

/* ********************************************************************************************** **
/* **** Treat Recipe Teller (Hands-off)
/* ********************************************************************************************** */
public class RecipeToolTalkerActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener {

    private static TextToSpeech textToSpeech;
    private EditText editText_instruction;

    /* ********************************************************************************************/
    /* *** Treat OnCreate
    /* ********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // .............................................................................. Set Layout
        setContentView(R.layout.recipe_tool_talker_activity);

        // ............................................................. Set Toolbar with back arrow
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.label_talker);

        // ...................................................................... Set Text to Speech
        textToSpeech = new TextToSpeech(RecipeToolTalkerActivity.this, this);

        Button button_talker = findViewById(R.id.button_talker);

        editText_instruction = findViewById(R.id.editText_instruction);
        editText_instruction.setText(sCurrentRecipeInstructionText);

        // ..................................................................... Treat button talker
        button_talker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                //noinspection deprecation
                textToSpeech.speak(sCurrentRecipeInstructionText, TextToSpeech.QUEUE_FLUSH, null);

            }

        });

    }

    /* ********************************************************************************************/
    /* *** Treat onDestroy
    /* ********************************************************************************************/
    @Override
    public void onDestroy() {

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }

        super.onDestroy();

    }

    /* ********************************************************************************************/
    /* *** Set TTS (Language, Level, Speed rate)
    /* ********************************************************************************************/
    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = textToSpeech.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                new SupportMessageToast(getApplicationContext(), getString(R.string.message_talker_error_01));

            } else {

                String text = editText_instruction.getText().toString();
                textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
            }

        } else {

            new SupportMessageToast(getApplicationContext(), getString(R.string.message_talker_error_02));

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

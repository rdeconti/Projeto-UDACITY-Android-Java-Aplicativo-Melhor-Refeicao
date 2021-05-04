package com.rosemeire.deconti.bestmeal.ApplicationSupport;

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

import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

/* ************************************************************************************************/
/* *** CHECK IF USER ENTER INFORMATION REQUIRED BY APPLICATION
/* ************************************************************************************************/
public class SupportCheckInformationInput {

    public static boolean SupportCheckInformationEmpty(View view, String message) {

        EditText editText = (EditText) view;
        Editable editable = editText.getText();

        if (TextUtils.isEmpty(editable)) {

            editText.setError(message);
            editText.setFocusable(true);
            editText.requestFocus();

            return false;

        } else {

            return true;

        }
    }
}

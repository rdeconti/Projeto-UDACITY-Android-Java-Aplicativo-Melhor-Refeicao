package com.rosemeire.deconti.bestmeal.ApplicationSupport;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

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

public class SupportMessageToast {

    public SupportMessageToast(Context context, String message) {

        Toast m_toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        m_toast.setText(message);
        m_toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL, 0, 0);
        m_toast.setDuration(Toast.LENGTH_LONG);
        m_toast.show();

    }
}

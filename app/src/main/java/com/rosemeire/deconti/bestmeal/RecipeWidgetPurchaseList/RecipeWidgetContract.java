package com.rosemeire.deconti.bestmeal.RecipeWidgetPurchaseList;

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

import android.net.Uri;
import android.provider.BaseColumns;

/* ************************************************************************************************/
/* *** DEFINE WIDGET CONTRACT
/* ************************************************************************************************/
public class RecipeWidgetContract implements BaseColumns {

    private static final String WIDGET_SCHEMA = "content://";
    public static final String WIDGET_AUTHORITY = "com.rosemeire.deconti.bestmeal";
    private static final Uri WIDGET_BASE_CONTENT_URI = Uri.parse(WIDGET_SCHEMA + WIDGET_AUTHORITY);

    public static final String WIDGET_PATH_PURCHASE = "purchase";
    public static final Uri WIDGET_PATH_PURCHASE_URI = WIDGET_BASE_CONTENT_URI.buildUpon().appendPath(WIDGET_PATH_PURCHASE).build();

}

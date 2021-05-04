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

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.rosemeire.deconti.bestmeal.R;

/* ************************************************************************************************/
/* *** TREAT REMOTE VIEW SERVICE FACTORY
/* ************************************************************************************************/
public class RecipeWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final Context mContext;
    private Cursor mCursor;

    /* ********************************************************************************************/
    /* *** RECEIVE DATA
    /* ********************************************************************************************/
    public RecipeWidgetRemoteViewsFactory(Context applicationContext) {

        mContext = applicationContext;

    }

    /* ********************************************************************************************/
    /* *** TREAT ON CREATE
    /* ********************************************************************************************/
    @Override
    public void onCreate() {

    }

    /* ********************************************************************************************/
    /* *** TREAT ON DATA SET CHANGED
    /* ********************************************************************************************/
    @Override
    public void onDataSetChanged() {

        if (mCursor != null) {
            mCursor.close();
        }

        final long identityToken = Binder.clearCallingIdentity();

        Uri uri = RecipeWidgetContract.WIDGET_PATH_PURCHASE_URI;

        mCursor = mContext.getContentResolver().query(uri,
                null,
                null,
                null,
                RecipeWidgetContract._ID + " DESC");

        Binder.restoreCallingIdentity(identityToken);

    }

    /* ********************************************************************************************/
    /* *** TREAT ON DESTROY
    /* ********************************************************************************************/
    @Override
    public void onDestroy() {

        if (mCursor != null) {
            mCursor.close();
        }

    }

    /* ********************************************************************************************/
    /* *** TREAT GET COUNT
    /* ********************************************************************************************/
    @Override
    public int getCount() {

        return mCursor == null ? 0 : mCursor.getCount();

    }

    /* ********************************************************************************************/
    /* *** TREAT REMOTE VIEWS
    /* ********************************************************************************************/
    @Override
    public RemoteViews getViewAt(int position) {

        if (position == AdapterView.INVALID_POSITION || mCursor == null || !mCursor.moveToPosition(position)) {

            return null;

        }

        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.recipe_widget_collection_item);
        remoteViews.setTextViewText(R.id.widgetItemTaskNameLabel, mCursor.getString(1));

        Intent fillInIntent = new Intent();
        fillInIntent.putExtra(RecipeWidgetAppWidgetProvider.EXTRA_LABEL, mCursor.getString(1));
        remoteViews.setOnClickFillInIntent(R.id.widgetItemContainer, fillInIntent);

        return remoteViews;
    }

    /* ********************************************************************************************/
    /* *** TREAT LOADING VIEW
    /* ********************************************************************************************/
    @Override
    public RemoteViews getLoadingView() {

        return null;

    }

    /* ********************************************************************************************/
    /* *** GET VIEW TYPE COUNT
    /* ********************************************************************************************/
    @Override
    public int getViewTypeCount() {

        return 1;

    }

    /* ********************************************************************************************/
    /* *** TREAT GET ITEM ID
    /* ********************************************************************************************/
    @Override
    public long getItemId(int position) {

        return mCursor.moveToPosition(position) ? mCursor.getLong(0) : position;

    }

    /* ********************************************************************************************/
    /* *** TREAT HAS STABLE IDS
    /* ********************************************************************************************/
    @Override
    public boolean hasStableIds() {

        return true;

    }
}

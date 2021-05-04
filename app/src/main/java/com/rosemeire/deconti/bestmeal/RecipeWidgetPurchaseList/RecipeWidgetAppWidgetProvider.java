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

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;
import android.widget.RemoteViews;

import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;

import java.util.Objects;

/* ************************************************************************************************/
/* *** TREAT EVENTS AND REFRESHES
/* ************************************************************************************************/
public class RecipeWidgetAppWidgetProvider extends AppWidgetProvider {

    public static final String EXTRA_LABEL = "PURCHASE_TEXT";

    /* ********************************************************************************************/
    /* *** TREAT CLICK EVENTS
    /* ********************************************************************************************/
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {

            RemoteViews remoteViews = new RemoteViews(

                    context.getPackageName(),
                    R.layout.recipe_widget_collection_list

            );

            // ................................................................ Treat click on title
            Intent titleIntent = new Intent(context, RecipeFirstNavigationCaptainActivity.class);
            PendingIntent titlePendingIntent = PendingIntent.getActivity(context, 0, titleIntent, 0);
            remoteViews.setOnClickPendingIntent(R.id.widgetTitleLabel, titlePendingIntent);

            Intent intent = new Intent(context, RecipeWidgetRemoteViewsService.class);

            remoteViews.setRemoteAdapter(R.id.widgetListView, intent);

            // ................................................................. Treat click on item
            Intent clickIntentTemplate = new Intent(context, RecipeFirstNavigationCaptainActivity.class);

            PendingIntent clickPendingIntentTemplate = TaskStackBuilder.create(context)
                    .addNextIntentWithParentStack(clickIntentTemplate)
                    .getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

            remoteViews.setPendingIntentTemplate(R.id.widgetListView, clickPendingIntentTemplate);

            appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
        }
    }

    /* ********************************************************************************************/
    /* *** TREAT REFRESH BROADCAST
    /* ********************************************************************************************/
    public static void sendRefreshBroadcast(Context context) {

        Intent intent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        intent.setComponent(new ComponentName(context, RecipeWidgetAppWidgetProvider.class));
        context.sendBroadcast(intent);

    }

    /* ********************************************************************************************/
    /* *** TREAT REFRESH WIDGETS
    /* ********************************************************************************************/
    @Override
    public void onReceive(final Context context, Intent intent) {

        final String action = intent.getAction();

        if (Objects.equals(action, AppWidgetManager.ACTION_APPWIDGET_UPDATE)) {

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context, RecipeWidgetAppWidgetProvider.class);
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetManager.getAppWidgetIds(componentName), R.id.widgetListView);

        }

        super.onReceive(context, intent);
    }
}

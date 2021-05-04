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

import android.content.Context;
import android.content.Intent;

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingExceptionError;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableComments;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableHeader;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableIngredients;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableInstructions;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableNutritional;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTablePurchase;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableTips;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableHeader;
import com.rosemeire.deconti.bestmeal.R;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_SHARED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sDesiredRecipeStatus;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromSQLite;

/* ************************************************************************************************/
/* *** RECIPE TOOL: SHARE
/* ************************************************************************************************/
public class RecipeToolShare {

    public RecipeToolShare(Context context) {

        sDesiredRecipeStatus = RECIPE_TREAT_SHARED;

        try {

            RecipeDatabaseFirebaseTableHeader.RecipeHeaderUpdateStatistics();
            RecipeDatabaseSQLiteTableHeader.RecipeHeaderCheckLocalStorageByNumber(context);

            if (sRecipeKeyFromSQLite == 0) {

                RecipeDatabaseFirebaseTableHeader.RecipeHeaderTransferFromFirebase(context);
                RecipeDatabaseFirebaseTableIngredients.RecipeIngredientsTransferFromFirebase(context);
                RecipeDatabaseFirebaseTableInstructions.RecipeInstructionsTransferFromFirebase(context);
                RecipeDatabaseFirebaseTableNutritional.RecipeNutritionalTransferFromFirebase(context);
                RecipeDatabaseFirebaseTableTips.RecipeTipsTransferFromFirebase(context);
                RecipeDatabaseFirebaseTableComments.RecipeCommentsTransferFromFirebase(context);
                RecipeDatabaseFirebaseTablePurchase.RecipePurchaseTransferFromFirebase(context);

            }

        } catch (Exception error) {

            String ClassName = String.class.getName();
            new SupportHandlingExceptionError(ClassName, error, context);

        }

        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

        sharingIntent.setType(context.getString(R.string.label_share_type));
        String shareBody = context.getString(R.string.label_share_contents);
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, R.string.label_share_title);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

        context.startActivity(Intent.createChooser(sharingIntent, context.getString(R.string.label_share_through)));

    }
}

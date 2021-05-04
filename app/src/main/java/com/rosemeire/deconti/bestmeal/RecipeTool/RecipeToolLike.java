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

import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportHandlingExceptionError;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableComments;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableHeader;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableIngredients;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableInstructions;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableNutritional;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTablePurchase;
import com.rosemeire.deconti.bestmeal.DatabaseFirebase.RecipeDatabaseFirebaseTableTips;
import com.rosemeire.deconti.bestmeal.DatabaseSQLite.RecipeDatabaseSQLiteTableHeader;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.RECIPE_TREAT_LIKED;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sDesiredRecipeStatus;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sRecipeKeyFromSQLite;

/* ************************************************************************************************/
/* *** RECIPE TOOL: LIKE
/* ************************************************************************************************/
public class RecipeToolLike {

    public RecipeToolLike(Context context) {

        sDesiredRecipeStatus = RECIPE_TREAT_LIKED;

        try {

            RecipeDatabaseFirebaseTableHeader.RecipeHeaderUpdateStatistics();
            RecipeDatabaseSQLiteTableHeader.RecipeHeaderCheckLocalStorageByNumber(context);

            if (sRecipeKeyFromSQLite != 0) {

                RecipeDatabaseSQLiteTableHeader.RecipeHeaderSQLiteUpdateStatus(context);

            } else {

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
    }
}

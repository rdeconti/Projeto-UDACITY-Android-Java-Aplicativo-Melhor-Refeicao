package com.rosemeire.deconti.bestmeal.DatabaseModel;

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

import android.os.Parcel;
import android.os.Parcelable;

public class RecipePurchaseModel implements Parcelable {

    public static final Creator<RecipePurchaseModel>
            CREATOR = new Creator<RecipePurchaseModel>() {

        @Override
        public RecipePurchaseModel createFromParcel(Parcel in) {
            return new RecipePurchaseModel(in);
        }

        @Override
        public RecipePurchaseModel[] newArray(int size) {
            return new RecipePurchaseModel[size];
        }
    };
    
    private String recipe_purchase_ingredients_recipe_name;
    private String recipe_purchase_ingredients_recipe_number;
    private String recipe_purchase_ingredients_name;
    private String recipe_purchase_ingredients_number;
    private String recipe_purchase_ingredients_amount;
    private String recipe_purchase_ingredients_unit;

    public RecipePurchaseModel() {

    }

    public RecipePurchaseModel(

            String recipe_purchase_ingredients_recipe_name,
            String recipe_purchase_ingredients_recipe_number,
            String recipe_purchase_ingredients_name,
            String recipe_purchase_ingredients_number,
            String recipe_purchase_ingredients_amount,
            String recipe_purchase_ingredients_unit) {

        this.recipe_purchase_ingredients_recipe_name = recipe_purchase_ingredients_recipe_name;
        this.recipe_purchase_ingredients_recipe_number = recipe_purchase_ingredients_recipe_number;
        this.recipe_purchase_ingredients_name = recipe_purchase_ingredients_name;
        this.recipe_purchase_ingredients_number = recipe_purchase_ingredients_number;
        this.recipe_purchase_ingredients_amount = recipe_purchase_ingredients_amount;
        this.recipe_purchase_ingredients_unit = recipe_purchase_ingredients_unit;

    }

    protected RecipePurchaseModel(Parcel in) {

        recipe_purchase_ingredients_recipe_name = in.readString();
        recipe_purchase_ingredients_recipe_number = in.readString();
        recipe_purchase_ingredients_name = in.readString();
        recipe_purchase_ingredients_number = in.readString();
        recipe_purchase_ingredients_amount = in.readString();
        recipe_purchase_ingredients_unit = in.readString();

    }

    public String getRecipe_purchase_ingredients_recipe_name() {
        return recipe_purchase_ingredients_recipe_name;
    }

    public void setRecipe_purchase_ingredients_recipe_name(String recipe_purchase_ingredients_recipe_name) {
        this.recipe_purchase_ingredients_recipe_name = recipe_purchase_ingredients_recipe_name;
    }

    public String getRecipe_purchase_ingredients_recipe_number() {
        return recipe_purchase_ingredients_recipe_number;
    }

    public void setRecipe_purchase_ingredients_recipe_number(String recipe_purchase_ingredients_recipe_number) {
        this.recipe_purchase_ingredients_recipe_number = recipe_purchase_ingredients_recipe_number;
    }

    public String getRecipe_purchase_ingredients_name() {
        return recipe_purchase_ingredients_name;
    }

    public void setRecipe_purchase_ingredients_name(String recipe_purchase_ingredients_name) {
        this.recipe_purchase_ingredients_name = recipe_purchase_ingredients_name;
    }

    public String getRecipe_purchase_ingredients_number() {
        return recipe_purchase_ingredients_number;
    }

    public void setRecipe_purchase_ingredients_number(String recipe_purchase_ingredients_number) {
        this.recipe_purchase_ingredients_number = recipe_purchase_ingredients_number;
    }

    public String getRecipe_purchase_ingredients_amount() {
        return recipe_purchase_ingredients_amount;
    }

    public void setRecipe_purchase_ingredients_amount(String recipe_purchase_ingredients_amount) {
        this.recipe_purchase_ingredients_amount = recipe_purchase_ingredients_amount;
    }

    public String getRecipe_purchase_ingredients_unit() {
        return recipe_purchase_ingredients_unit;
    }

    public void setRecipe_purchase_ingredients_unit(String recipe_purchase_ingredients_unit) {
        this.recipe_purchase_ingredients_unit = recipe_purchase_ingredients_unit;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(recipe_purchase_ingredients_recipe_name);
        dest.writeString(recipe_purchase_ingredients_recipe_number);
        dest.writeString(recipe_purchase_ingredients_name);
        dest.writeString(recipe_purchase_ingredients_number);
        dest.writeString(recipe_purchase_ingredients_amount);
        dest.writeString(recipe_purchase_ingredients_unit);

    }

    @Override
    public int describeContents() {
        return 0;
    }
}




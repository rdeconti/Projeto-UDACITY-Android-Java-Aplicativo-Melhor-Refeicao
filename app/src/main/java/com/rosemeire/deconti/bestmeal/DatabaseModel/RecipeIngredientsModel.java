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

public class RecipeIngredientsModel implements Parcelable {

    public static final Creator<RecipeIngredientsModel>
            CREATOR = new Creator<RecipeIngredientsModel>() {

        @Override
        public RecipeIngredientsModel createFromParcel(Parcel in) {
            return new RecipeIngredientsModel(in);
        }

        @Override
        public RecipeIngredientsModel[] newArray(int size) {
            return new RecipeIngredientsModel[size];
        }
    };

    private String recipe_ingredients_recipe;
    private String recipe_ingredients_number;
    private String recipe_ingredients_amount;
    private String recipe_ingredients_unit;
    private String recipe_ingredients_name;
    private String recipe_ingredients_photo;

    public RecipeIngredientsModel() {

    }

    public RecipeIngredientsModel(

            String recipe_ingredients_recipe,
            String recipe_ingredients_number,
            String recipe_ingredients_amount,
            String recipe_ingredients_unit,
            String recipe_ingredients_name,
            String recipe_ingredients_photo) {

        this.recipe_ingredients_recipe = recipe_ingredients_recipe;
        this.recipe_ingredients_number = recipe_ingredients_number;
        this.recipe_ingredients_amount = recipe_ingredients_amount;
        this.recipe_ingredients_unit = recipe_ingredients_unit;
        this.recipe_ingredients_name = recipe_ingredients_name;
        this.recipe_ingredients_photo = recipe_ingredients_photo;

    }

    protected RecipeIngredientsModel(Parcel in) {

        recipe_ingredients_recipe = in.readString();
        recipe_ingredients_number = in.readString();
        recipe_ingredients_amount = in.readString();
        recipe_ingredients_unit = in.readString();
        recipe_ingredients_name = in.readString();
        recipe_ingredients_photo = in.readString();

    }

    public String getRecipe_ingredients_recipe() {
        return recipe_ingredients_recipe;
    }

    public void setRecipe_ingredients_recipe(String recipe_ingredients_recipe) {
        this.recipe_ingredients_recipe = recipe_ingredients_recipe;
    }

    public String getRecipe_ingredients_number() {
        return recipe_ingredients_number;
    }

    public void setRecipe_ingredients_number(String recipe_ingredients_number) {
        this.recipe_ingredients_number = recipe_ingredients_number;
    }

    public String getRecipe_ingredients_amount() {
        return recipe_ingredients_amount;
    }

    public void setRecipe_ingredients_amount(String recipe_ingredients_amount) {
        this.recipe_ingredients_amount = recipe_ingredients_amount;
    }

    public String getRecipe_ingredients_unit() {
        return recipe_ingredients_unit;
    }

    public void setRecipe_ingredients_unit(String recipe_ingredients_unit) {
        this.recipe_ingredients_unit = recipe_ingredients_unit;
    }

    public String getRecipe_ingredients_name() {
        return recipe_ingredients_name;
    }

    public void setRecipe_ingredients_name(String recipe_ingredients_name) {
        this.recipe_ingredients_name = recipe_ingredients_name;
    }

    public String getRecipe_ingredients_photo() {
        return recipe_ingredients_photo;
    }

    public void setRecipe_ingredients_photo(String recipe_ingredients_photo) {
        this.recipe_ingredients_photo = recipe_ingredients_photo;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(recipe_ingredients_recipe);
        dest.writeString(recipe_ingredients_number);
        dest.writeString(recipe_ingredients_amount);
        dest.writeString(recipe_ingredients_unit);
        dest.writeString(recipe_ingredients_name);
        dest.writeString(recipe_ingredients_photo);

    }

    @Override
    public int describeContents() {
        return 0;
    }
}




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

public class RecipeInstructionsModel implements Parcelable {

    public static final Creator<RecipeInstructionsModel>
            CREATOR = new Creator<RecipeInstructionsModel>() {

        @Override
        public RecipeInstructionsModel createFromParcel(Parcel in) {
            return new RecipeInstructionsModel(in);
        }

        @Override
        public RecipeInstructionsModel[] newArray(int size) {
            return new RecipeInstructionsModel[size];
        }
    };
    private String recipe_instructions_recipe;
    private String recipe_instructions_number;
    private String recipe_instructions_text;
    private String recipe_instructions_photo;

    public RecipeInstructionsModel() {

    }

    public RecipeInstructionsModel(

            String recipe_instructions_recipe,
            String recipe_instructions_number,
            String recipe_instructions_text,
            String recipe_instructions_photo) {

        this.recipe_instructions_recipe = recipe_instructions_recipe;
        this.recipe_instructions_number = recipe_instructions_number;
        this.recipe_instructions_text = recipe_instructions_text;
        this.recipe_instructions_photo = recipe_instructions_photo;

    }

    protected RecipeInstructionsModel(Parcel in) {

        recipe_instructions_recipe = in.readString();
        recipe_instructions_number = in.readString();
        recipe_instructions_text = in.readString();
        recipe_instructions_photo = in.readString();

    }

    public String getRecipe_instructions_recipe() {
        return recipe_instructions_recipe;
    }

    public void setRecipe_instructions_recipe(String recipe_instructions_recipe) {
        this.recipe_instructions_recipe = recipe_instructions_recipe;
    }

    public String getRecipe_instructions_number() {
        return recipe_instructions_number;
    }

    public void setRecipe_instructions_number(String recipe_instructions_number) {
        this.recipe_instructions_number = recipe_instructions_number;
    }

    public String getRecipe_instructions_text() {
        return recipe_instructions_text;
    }

    public void setRecipe_instructions_text(String recipe_instructions_text) {
        this.recipe_instructions_text = recipe_instructions_text;
    }

    public String getRecipe_instructions_photo() {
        return recipe_instructions_photo;
    }

    public void setRecipe_instructions_photo(String recipe_instructions_photo) {
        this.recipe_instructions_photo = recipe_instructions_photo;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(recipe_instructions_recipe);
        dest.writeString(recipe_instructions_number);
        dest.writeString(recipe_instructions_text);
        dest.writeString(recipe_instructions_photo);

    }

    @Override
    public int describeContents() {
        return 0;
    }
}




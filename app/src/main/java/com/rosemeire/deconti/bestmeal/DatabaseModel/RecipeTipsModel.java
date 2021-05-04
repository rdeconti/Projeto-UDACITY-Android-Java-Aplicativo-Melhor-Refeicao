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

public class RecipeTipsModel implements Parcelable {

    public static final Creator<RecipeTipsModel>
            CREATOR = new Creator<RecipeTipsModel>() {

        @Override
        public RecipeTipsModel createFromParcel(Parcel in) {
            return new RecipeTipsModel(in);
        }

        @Override
        public RecipeTipsModel[] newArray(int size) {
            return new RecipeTipsModel[size];
        }
    };
    private String recipe_tips_recipe;
    private String recipe_tips_number;
    private String recipe_tips_text;

    public RecipeTipsModel() {

    }

    public RecipeTipsModel(

            String recipe_tips_recipe,
            String recipe_tips_number,
            String recipe_tips_text) {

        this.recipe_tips_recipe = recipe_tips_recipe;
        this.recipe_tips_number = recipe_tips_number;
        this.recipe_tips_text = recipe_tips_text;

    }

    protected RecipeTipsModel(Parcel in) {

        recipe_tips_recipe = in.readString();
        recipe_tips_number = in.readString();
        recipe_tips_text = in.readString();

    }

    public String getRecipe_tips_recipe() {
        return recipe_tips_recipe;
    }

    public void setRecipe_tips_recipe(String recipe_tips_recipe) {
        this.recipe_tips_recipe = recipe_tips_recipe;
    }

    public String getRecipe_tips_number() {
        return recipe_tips_number;
    }

    public void setRecipe_tips_number(String recipe_tips_number) {
        this.recipe_tips_number = recipe_tips_number;
    }

    public String getRecipe_tips_text() {
        return recipe_tips_text;
    }

    public void setRecipe_tips_text(String recipe_tips_text) {
        this.recipe_tips_text = recipe_tips_text;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(recipe_tips_recipe);
        dest.writeString(recipe_tips_number);
        dest.writeString(recipe_tips_text);

    }

    @Override
    public int describeContents() {
        return 0;
    }
}




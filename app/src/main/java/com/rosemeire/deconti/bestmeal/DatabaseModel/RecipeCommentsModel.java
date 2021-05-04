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

public class RecipeCommentsModel implements Parcelable {

    public static final Creator<RecipeCommentsModel>
            CREATOR = new Creator<RecipeCommentsModel>() {

        @Override
        public RecipeCommentsModel createFromParcel(Parcel in) {
            return new RecipeCommentsModel(in);
        }

        @Override
        public RecipeCommentsModel[] newArray(int size) {
            return new RecipeCommentsModel[size];
        }
    };
    private String recipe_comments_recipe;
    private String recipe_comments_number;
    private String recipe_comments_user;
    private String recipe_comments_text;

    public RecipeCommentsModel() {

    }

    public RecipeCommentsModel(

            String recipe_comments_recipe,
            String recipe_comments_number,
            String recipe_comments_user,
            String recipe_comments_text) {

        this.recipe_comments_recipe = recipe_comments_recipe;
        this.recipe_comments_number = recipe_comments_number;
        this.recipe_comments_user = recipe_comments_user;
        this.recipe_comments_text = recipe_comments_text;

    }

    protected RecipeCommentsModel(Parcel in) {

        recipe_comments_recipe = in.readString();
        recipe_comments_number = in.readString();
        recipe_comments_user = in.readString();
        recipe_comments_text = in.readString();

    }

    public String getRecipe_comments_recipe() {
        return recipe_comments_recipe;
    }

    public void setRecipe_comments_recipe(String recipe_comments_recipe) {
        this.recipe_comments_recipe = recipe_comments_recipe;
    }

    public String getRecipe_comments_number() {
        return recipe_comments_number;
    }

    public void setRecipe_comments_number(String recipe_comments_number) {
        this.recipe_comments_number = recipe_comments_number;
    }

    public String getRecipe_comments_user() {
        return recipe_comments_user;
    }

    public void setRecipe_comments_user(String recipe_comments_user) {
        this.recipe_comments_user = recipe_comments_user;
    }

    public String getRecipe_comments_text() {
        return recipe_comments_text;
    }

    public void setRecipe_comments_text(String recipe_comments_text) {
        this.recipe_comments_text = recipe_comments_text;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(recipe_comments_recipe);
        dest.writeString(recipe_comments_number);
        dest.writeString(recipe_comments_user);
        dest.writeString(recipe_comments_text);

    }

    @Override
    public int describeContents() {
        return 0;
    }
}




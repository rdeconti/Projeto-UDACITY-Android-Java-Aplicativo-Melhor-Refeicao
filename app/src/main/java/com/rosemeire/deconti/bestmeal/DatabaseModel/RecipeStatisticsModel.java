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

public class RecipeStatisticsModel implements Parcelable {

    public static final Creator<RecipeStatisticsModel>
            CREATOR = new Creator<RecipeStatisticsModel>() {

        @Override
        public RecipeStatisticsModel createFromParcel(Parcel in) {
            return new RecipeStatisticsModel(in);
        }

        @Override
        public RecipeStatisticsModel[] newArray(int size) {
            return new RecipeStatisticsModel[size];
        }
    };

    private String recipe_header_number;
    private String recipe_header_name;
    private String recipe_header_author;
    private String recipe_header_update;
    private String recipe_header_photo;
    private String recipe_header_status_approved;
    private String recipe_header_status_reproved;
    private String recipe_header_status_liked;
    private String recipe_header_status_disliked;
    private String recipe_header_status_commented;
    private String recipe_header_status_printed;
    private String recipe_header_status_shared;
    private String recipe_header_status_visualized;
    private String recipe_header_language;
    private String recipe_header_likes;
    private String recipe_header_dislikes;
    private String recipe_header_approvals;
    private String recipe_header_reprovals;
    private String recipe_header_visualizations;
    private String recipe_header_printings;
    private String recipe_header_comments;
    private String recipe_header_sharings;
    private String recipe_header_rating_value;
    private String recipe_header_rating_display;
    private String recipe_header_diet;
    private String recipe_header_calories;
    private String recipe_header_cuisenes;
    private String recipe_header_health;
    private String recipe_header_occasion;
    private String recipe_header_servings;
    private String recipe_header_price;
    private String recipe_header_time;
    private String recipe_header_difficult;

    public RecipeStatisticsModel() {

    }

    public RecipeStatisticsModel(
            String recipe_header_number,
            String recipe_header_name,
            String recipe_header_author,
            String recipe_header_update,
            String recipe_header_photo,
            String recipe_header_status_approved,
            String recipe_header_status_reproved,
            String recipe_header_status_liked,
            String recipe_header_status_disliked,
            String recipe_header_status_commented,
            String recipe_header_status_shared,
            String recipe_header_status_visualized,
            String recipe_header_status_printed,
            String recipe_header_language,
            String recipe_header_likes,
            String recipe_header_dislikes,
            String recipe_header_approvals,
            String recipe_header_reprovals,
            String recipe_header_visualizations,
            String recipe_header_printings,
            String recipe_header_comments,
            String recipe_header_sharings,
            String recipe_header_rating_value,
            String recipe_header_rating_display,
            String recipe_header_diet,
            String recipe_header_calories,
            String recipe_header_cuisenes,
            String recipe_header_health,
            String recipe_header_occasion,
            String recipe_header_servings,
            String recipe_header_price,
            String recipe_header_time,
            String recipe_header_difficult) {

        this.recipe_header_number = recipe_header_number;
        this.recipe_header_name = recipe_header_name;
        this.recipe_header_author = recipe_header_author;
        this.recipe_header_update = recipe_header_update;
        this.recipe_header_photo = recipe_header_photo;
        this.recipe_header_status_approved = recipe_header_status_approved;
        this.recipe_header_status_reproved = recipe_header_status_reproved;
        this.recipe_header_status_liked = recipe_header_status_liked;
        this.recipe_header_status_disliked = recipe_header_status_disliked;
        this.recipe_header_status_printed = recipe_header_status_printed;
        this.recipe_header_status_commented = recipe_header_status_commented;
        this.recipe_header_status_shared = recipe_header_status_shared;
        this.recipe_header_status_visualized = recipe_header_status_visualized;
        this.recipe_header_language = recipe_header_language;
        this.recipe_header_likes = recipe_header_likes;
        this.recipe_header_dislikes = recipe_header_dislikes;
        this.recipe_header_approvals = recipe_header_approvals;
        this.recipe_header_reprovals = recipe_header_reprovals;
        this.recipe_header_visualizations = recipe_header_visualizations;
        this.recipe_header_printings = recipe_header_printings;
        this.recipe_header_comments = recipe_header_comments;
        this.recipe_header_sharings = recipe_header_sharings;
        this.recipe_header_rating_value = recipe_header_rating_value;
        this.recipe_header_rating_display = recipe_header_rating_display;
        this.recipe_header_diet = recipe_header_diet;
        this.recipe_header_calories = recipe_header_calories;
        this.recipe_header_cuisenes = recipe_header_cuisenes;
        this.recipe_header_health = recipe_header_health;
        this.recipe_header_occasion = recipe_header_occasion;
        this.recipe_header_servings = recipe_header_servings;
        this.recipe_header_price = recipe_header_price;
        this.recipe_header_time = recipe_header_time;
        this.recipe_header_difficult = recipe_header_difficult;
    }

    public RecipeStatisticsModel(Parcel in) {

        recipe_header_number = in.readString();
        recipe_header_name = in.readString();
        recipe_header_author = in.readString();
        recipe_header_update = in.readString();
        recipe_header_photo = in.readString();
        recipe_header_status_approved = in.readString();
        recipe_header_status_reproved = in.readString();
        recipe_header_status_liked = in.readString();
        recipe_header_status_disliked = in.readString();
        recipe_header_status_visualized = in.readString();
        recipe_header_status_printed = in.readString();
        recipe_header_status_shared = in.readString();
        recipe_header_status_commented = in.readString();
        recipe_header_language = in.readString();
        recipe_header_likes = in.readString();
        recipe_header_dislikes = in.readString();
        recipe_header_approvals = in.readString();
        recipe_header_reprovals = in.readString();
        recipe_header_visualizations = in.readString();
        recipe_header_printings = in.readString();
        recipe_header_comments = in.readString();
        recipe_header_sharings = in.readString();
        recipe_header_rating_value = in.readString();
        recipe_header_rating_display = in.readString();
        recipe_header_diet = in.readString();
        recipe_header_calories = in.readString();
        recipe_header_cuisenes = in.readString();
        recipe_header_health = in.readString();
        recipe_header_occasion = in.readString();
        recipe_header_servings = in.readString();
        recipe_header_price = in.readString();
        recipe_header_time = in.readString();
        recipe_header_difficult = in.readString();

    }

    public String getRecipe_header_number() {
        return recipe_header_number;
    }

    public void setRecipe_header_number(String recipe_header_number) {
        this.recipe_header_number = recipe_header_number;
    }

    public String getRecipe_header_name() {
        return recipe_header_name;
    }

    public void setRecipe_header_name(String recipe_header_name) {
        this.recipe_header_name = recipe_header_name;
    }

    public String getRecipe_header_status_approved() {
        return recipe_header_status_approved;
    }

    public void setRecipe_header_status_approved(String recipe_header_status_approved) {
        this.recipe_header_status_approved = recipe_header_status_approved;
    }

    public String getRecipe_header_status_reproved() {
        return recipe_header_status_reproved;
    }

    public void setRecipe_header_status_reproved(String recipe_header_status_reproved) {
        this.recipe_header_status_reproved = recipe_header_status_reproved;
    }

    public String getRecipe_header_status_liked() {
        return recipe_header_status_liked;
    }

    public void setRecipe_header_status_liked(String recipe_header_status_liked) {
        this.recipe_header_status_liked = recipe_header_status_liked;
    }

    public String getRecipe_header_status_disliked() {
        return recipe_header_status_disliked;
    }

    public void setRecipe_header_status_disliked(String recipe_header_status_disliked) {
        this.recipe_header_status_disliked = recipe_header_status_disliked;
    }

    public String getRecipe_header_status_commented() {
        return recipe_header_status_commented;
    }

    public void setRecipe_header_status_commented(String recipe_header_status_commented) {
        this.recipe_header_status_commented = recipe_header_status_commented;
    }

    public String getRecipe_header_status_printed() {
        return recipe_header_status_printed;
    }

    public void setRecipe_header_status_printed(String recipe_header_status_printed) {
        this.recipe_header_status_printed = recipe_header_status_printed;
    }

    public String getRecipe_header_status_shared() {
        return recipe_header_status_shared;
    }

    public void setRecipe_header_status_shared(String recipe_header_status_shared) {
        this.recipe_header_status_shared = recipe_header_status_shared;
    }

    public String getRecipe_header_status_visualized() {
        return recipe_header_status_visualized;
    }

    public void setRecipe_header_status_visualized(String recipe_header_status_visualized) {
        this.recipe_header_status_visualized = recipe_header_status_visualized;
    }

    public String getRecipe_header_author() {
        return recipe_header_author;
    }

    public void setRecipe_header_author(String recipe_header_author) {
        this.recipe_header_author = recipe_header_author;
    }

    public String getRecipe_header_update() {
        return recipe_header_update;
    }

    public void setRecipe_header_update(String recipe_header_update) {
        this.recipe_header_update = recipe_header_update;
    }

    public String getRecipe_header_photo() {
        return recipe_header_photo;
    }

    public void setRecipe_header_photo(String recipe_header_photo) {
        this.recipe_header_photo = recipe_header_photo;
    }

    public String getRecipe_header_language() {
        return recipe_header_language;
    }

    public void setRecipe_header_language(String recipe_header_language) {
        this.recipe_header_language = recipe_header_language;
    }

    public String getRecipe_header_likes() {
        return recipe_header_likes;
    }

    public void setRecipe_header_likes(String recipe_header_likes) {
        this.recipe_header_likes = recipe_header_likes;
    }

    public String getRecipe_header_dislikes() {
        return recipe_header_dislikes;
    }

    public void setRecipe_header_dislikes(String recipe_header_dislikes) {
        this.recipe_header_dislikes = recipe_header_dislikes;
    }

    public String getRecipe_header_approvals() {
        return recipe_header_approvals;
    }

    public void setRecipe_header_approvals(String recipe_header_approvals) {
        this.recipe_header_approvals = recipe_header_approvals;
    }

    public String getRecipe_header_reprovals() {
        return recipe_header_reprovals;
    }

    public void setRecipe_header_reprovals(String recipe_header_reprovals) {
        this.recipe_header_reprovals = recipe_header_reprovals;
    }

    public String getRecipe_header_visualizations() {
        return recipe_header_visualizations;
    }

    public void setRecipe_header_visualizations(String recipe_header_visualizations) {
        this.recipe_header_visualizations = recipe_header_visualizations;
    }

    public String getRecipe_header_printings() {
        return recipe_header_printings;
    }

    public void setRecipe_header_printings(String recipe_header_printings) {
        this.recipe_header_printings = recipe_header_printings;
    }

    public String getRecipe_header_comments() {
        return recipe_header_comments;
    }

    public void setRecipe_header_comments(String recipe_header_comments) {
        this.recipe_header_comments = recipe_header_comments;
    }

    public String getRecipe_header_sharings() {
        return recipe_header_sharings;
    }

    public void setRecipe_header_sharings(String recipe_header_sharings) {
        this.recipe_header_sharings = recipe_header_sharings;
    }

    public String getRecipe_header_rating_value() {
        return recipe_header_rating_value;
    }

    public void setRecipe_header_rating_value(String recipe_header_rating_value) {
        this.recipe_header_rating_value = recipe_header_rating_value;
    }

    public String getRecipe_header_rating_display() {
        return recipe_header_rating_display;
    }

    public void setRecipe_header_rating_display(String recipe_header_rating_display) {
        this.recipe_header_rating_display = recipe_header_rating_display;
    }

    public String getRecipe_header_diet() {
        return recipe_header_diet;
    }

    public void setRecipe_header_diet(String recipe_header_diet) {
        this.recipe_header_diet = recipe_header_diet;
    }

    public String getRecipe_header_calories() {
        return recipe_header_calories;
    }

    public void setRecipe_header_calories(String recipe_header_calories) {
        this.recipe_header_calories = recipe_header_calories;
    }

    public String getRecipe_header_cuisenes() {
        return recipe_header_cuisenes;
    }

    public void setRecipe_header_cuisenes(String recipe_header_cuisenes) {
        this.recipe_header_cuisenes = recipe_header_cuisenes;
    }

    public String getRecipe_header_health() {
        return recipe_header_health;
    }

    public void setRecipe_header_health(String recipe_header_health) {
        this.recipe_header_health = recipe_header_health;
    }

    public String getRecipe_header_occasion() {
        return recipe_header_occasion;
    }

    public void setRecipe_header_occasion(String recipe_header_occasion) {
        this.recipe_header_occasion = recipe_header_occasion;
    }

    public String getRecipe_header_servings() {
        return recipe_header_servings;
    }

    public void setRecipe_header_servings(String recipe_header_servings) {
        this.recipe_header_servings = recipe_header_servings;
    }

    public String getRecipe_header_price() {
        return recipe_header_price;
    }

    public void setRecipe_header_price(String recipe_header_price) {
        this.recipe_header_price = recipe_header_price;
    }

    public String getRecipe_header_time() {
        return recipe_header_time;
    }

    public void setRecipe_header_time(String recipe_header_time) {
        this.recipe_header_time = recipe_header_time;
    }

    public String getRecipe_header_difficult() {
        return recipe_header_difficult;
    }

    public void setRecipe_header_difficult(String recipe_header_difficult) {
        this.recipe_header_difficult = recipe_header_difficult;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(recipe_header_number);
        dest.writeString(recipe_header_name);
        dest.writeString(recipe_header_author);
        dest.writeString(recipe_header_update);
        dest.writeString(recipe_header_photo);
        dest.writeString(recipe_header_status_approved);
        dest.writeString(recipe_header_status_reproved);
        dest.writeString(recipe_header_status_liked);
        dest.writeString(recipe_header_status_disliked);
        dest.writeString(recipe_header_status_printed);
        dest.writeString(recipe_header_status_shared);
        dest.writeString(recipe_header_status_commented);
        dest.writeString(recipe_header_status_visualized);
        dest.writeString(recipe_header_language);
        dest.writeString(recipe_header_likes);
        dest.writeString(recipe_header_dislikes);
        dest.writeString(recipe_header_approvals);
        dest.writeString(recipe_header_reprovals);
        dest.writeString(recipe_header_visualizations);
        dest.writeString(recipe_header_printings);
        dest.writeString(recipe_header_comments);
        dest.writeString(recipe_header_sharings);
        dest.writeString(recipe_header_rating_value);
        dest.writeString(recipe_header_rating_display);
        dest.writeString(recipe_header_diet);
        dest.writeString(recipe_header_calories);
        dest.writeString(recipe_header_cuisenes);
        dest.writeString(recipe_header_health);
        dest.writeString(recipe_header_occasion);
        dest.writeString(recipe_header_servings);
        dest.writeString(recipe_header_price);
        dest.writeString(recipe_header_time);
        dest.writeString(recipe_header_difficult);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}




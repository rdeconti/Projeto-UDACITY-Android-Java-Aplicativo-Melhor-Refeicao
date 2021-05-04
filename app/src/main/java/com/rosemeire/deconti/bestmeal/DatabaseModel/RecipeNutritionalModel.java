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

public class RecipeNutritionalModel implements Parcelable {

    public static final Creator<RecipeNutritionalModel>
            CREATOR = new Creator<RecipeNutritionalModel>() {

        @Override
        public RecipeNutritionalModel createFromParcel(Parcel in) {
            return new RecipeNutritionalModel(in);
        }

        @Override
        public RecipeNutritionalModel[] newArray(int size) {
            return new RecipeNutritionalModel[size];
        }
    };
    private String recipe_nutritional_recipe;
    private String recipe_nutritional_calcium;
    private String recipe_nutritional_carbs;
    private String recipe_nutritional_cholesterol;
    private String recipe_nutritional_monounsaturated;
    private String recipe_nutritional_polyunsaturated;
    private String recipe_nutritional_saturated;
    private String recipe_nutritional_fat;
    private String recipe_nutritional_trans;
    private String recipe_nutritional_iron;
    private String recipe_nutritional_fiber;
    private String recipe_nutritional_folate;
    private String recipe_nutritional_potassium;
    private String recipe_nutritional_magnesium;
    private String recipe_nutritional_sodium;
    private String recipe_nutritional_energy;
    private String recipe_nutritional_niacin;
    private String recipe_nutritional_phosphorus;
    private String recipe_nutritional_protein;
    private String recipe_nutritional_riboflavin;
    private String recipe_nutritional_sugars;
    private String recipe_nutritional_thiamin;
    private String recipe_nutritional_vitamin_e;
    private String recipe_nutritional_vitamin_a;
    private String recipe_nutritional_vitamin_b12;
    private String recipe_nutritional_vitamin_b6;
    private String recipe_nutritional_vitamin_c;
    private String recipe_nutritional_vitamin_d;
    private String recipe_nutritional_vitamin_k;

    public RecipeNutritionalModel() {

    }

    public RecipeNutritionalModel(
            String recipe_nutritional_recipe,
            String recipe_nutritional_calcium,
            String recipe_nutritional_carbs,
            String recipe_nutritional_cholesterol,
            String recipe_nutritional_monounsaturated,
            String recipe_nutritional_polyunsaturated,
            String recipe_nutritional_saturated,
            String recipe_nutritional_fat,
            String recipe_nutritional_trans,
            String recipe_nutritional_iron,
            String recipe_nutritional_fiber,
            String recipe_nutritional_folate,
            String recipe_nutritional_potassium,
            String recipe_nutritional_magnesium,
            String recipe_nutritional_sodium,
            String recipe_nutritional_energy,
            String recipe_nutritional_niacin,
            String recipe_nutritional_phosphorus,
            String recipe_nutritional_protein,
            String recipe_nutritional_riboflavin,
            String recipe_nutritional_sugars,
            String recipe_nutritional_thiamin,
            String recipe_nutritional_vitamin_e,
            String recipe_nutritional_vitamin_a,
            String recipe_nutritional_vitamin_b12,
            String recipe_nutritional_vitamin_b6,
            String recipe_nutritional_vitamin_c,
            String recipe_nutritional_vitamin_d,
            String recipe_nutritional_vitamin_k) {

        this.recipe_nutritional_recipe = recipe_nutritional_recipe;
        this.recipe_nutritional_calcium = recipe_nutritional_calcium;
        this.recipe_nutritional_carbs = recipe_nutritional_carbs;
        this.recipe_nutritional_cholesterol = recipe_nutritional_cholesterol;
        this.recipe_nutritional_monounsaturated = recipe_nutritional_monounsaturated;
        this.recipe_nutritional_polyunsaturated = recipe_nutritional_polyunsaturated;
        this.recipe_nutritional_saturated = recipe_nutritional_saturated;
        this.recipe_nutritional_fat = recipe_nutritional_fat;
        this.recipe_nutritional_trans = recipe_nutritional_trans;
        this.recipe_nutritional_iron = recipe_nutritional_iron;
        this.recipe_nutritional_fiber = recipe_nutritional_fiber;
        this.recipe_nutritional_folate = recipe_nutritional_folate;
        this.recipe_nutritional_potassium = recipe_nutritional_potassium;
        this.recipe_nutritional_magnesium = recipe_nutritional_magnesium;
        this.recipe_nutritional_sodium = recipe_nutritional_sodium;
        this.recipe_nutritional_energy = recipe_nutritional_energy;
        this.recipe_nutritional_niacin = recipe_nutritional_niacin;
        this.recipe_nutritional_phosphorus = recipe_nutritional_phosphorus;
        this.recipe_nutritional_protein = recipe_nutritional_protein;
        this.recipe_nutritional_riboflavin = recipe_nutritional_riboflavin;
        this.recipe_nutritional_sugars = recipe_nutritional_sugars;
        this.recipe_nutritional_thiamin = recipe_nutritional_thiamin;
        this.recipe_nutritional_vitamin_e = recipe_nutritional_vitamin_e;
        this.recipe_nutritional_vitamin_a = recipe_nutritional_vitamin_a;
        this.recipe_nutritional_vitamin_b12 = recipe_nutritional_vitamin_b12;
        this.recipe_nutritional_vitamin_b6 = recipe_nutritional_vitamin_b6;
        this.recipe_nutritional_vitamin_c = recipe_nutritional_vitamin_c;
        this.recipe_nutritional_vitamin_d = recipe_nutritional_vitamin_d;
        this.recipe_nutritional_vitamin_k = recipe_nutritional_vitamin_k;

    }

    protected RecipeNutritionalModel(Parcel in) {

        recipe_nutritional_recipe = in.readString();
        recipe_nutritional_calcium = in.readString();
        recipe_nutritional_carbs = in.readString();
        recipe_nutritional_cholesterol = in.readString();
        recipe_nutritional_monounsaturated = in.readString();
        recipe_nutritional_polyunsaturated = in.readString();
        recipe_nutritional_saturated = in.readString();
        recipe_nutritional_fat = in.readString();
        recipe_nutritional_trans = in.readString();
        recipe_nutritional_iron = in.readString();
        recipe_nutritional_fiber = in.readString();
        recipe_nutritional_folate = in.readString();
        recipe_nutritional_potassium = in.readString();
        recipe_nutritional_magnesium = in.readString();
        recipe_nutritional_sodium = in.readString();
        recipe_nutritional_energy = in.readString();
        recipe_nutritional_niacin = in.readString();
        recipe_nutritional_phosphorus = in.readString();
        recipe_nutritional_protein = in.readString();
        recipe_nutritional_riboflavin = in.readString();
        recipe_nutritional_sugars = in.readString();
        recipe_nutritional_thiamin = in.readString();
        recipe_nutritional_vitamin_e = in.readString();
        recipe_nutritional_vitamin_a = in.readString();
        recipe_nutritional_vitamin_b12 = in.readString();
        recipe_nutritional_vitamin_b6 = in.readString();
        recipe_nutritional_vitamin_c = in.readString();
        recipe_nutritional_vitamin_d = in.readString();
        recipe_nutritional_vitamin_k = in.readString();

    }

    public String getRecipe_nutritional_recipe() {
        return recipe_nutritional_recipe;
    }

    public void setRecipe_nutritional_recipe(String recipe_nutritional_recipe) {
        this.recipe_nutritional_recipe = recipe_nutritional_recipe;
    }

    public String getRecipe_nutritional_calcium() {
        return recipe_nutritional_calcium;
    }

    public void setRecipe_nutritional_calcium(String recipe_nutritional_calcium) {
        this.recipe_nutritional_calcium = recipe_nutritional_calcium;
    }

    public String getRecipe_nutritional_carbs() {
        return recipe_nutritional_carbs;
    }

    public void setRecipe_nutritional_carbs(String recipe_nutritional_carbs) {
        this.recipe_nutritional_carbs = recipe_nutritional_carbs;
    }

    public String getRecipe_nutritional_cholesterol() {
        return recipe_nutritional_cholesterol;
    }

    public void setRecipe_nutritional_cholesterol(String recipe_nutritional_cholesterol) {
        this.recipe_nutritional_cholesterol = recipe_nutritional_cholesterol;
    }

    public String getRecipe_nutritional_monounsaturated() {
        return recipe_nutritional_monounsaturated;
    }

    public void setRecipe_nutritional_monounsaturated(String recipe_nutritional_monounsaturated) {
        this.recipe_nutritional_monounsaturated = recipe_nutritional_monounsaturated;
    }

    public String getRecipe_nutritional_polyunsaturated() {
        return recipe_nutritional_polyunsaturated;
    }

    public void setRecipe_nutritional_polyunsaturated(String recipe_nutritional_polyunsaturated) {
        this.recipe_nutritional_polyunsaturated = recipe_nutritional_polyunsaturated;
    }

    public String getRecipe_nutritional_saturated() {
        return recipe_nutritional_saturated;
    }

    public void setRecipe_nutritional_saturated(String recipe_nutritional_saturated) {
        this.recipe_nutritional_saturated = recipe_nutritional_saturated;
    }

    public String getRecipe_nutritional_fat() {
        return recipe_nutritional_fat;
    }

    public void setRecipe_nutritional_fat(String recipe_nutritional_fat) {
        this.recipe_nutritional_fat = recipe_nutritional_fat;
    }

    public String getRecipe_nutritional_trans() {
        return recipe_nutritional_trans;
    }

    public void setRecipe_nutritional_trans(String recipe_nutritional_trans) {
        this.recipe_nutritional_trans = recipe_nutritional_trans;
    }

    public String getRecipe_nutritional_iron() {
        return recipe_nutritional_iron;
    }

    public void setRecipe_nutritional_iron(String recipe_nutritional_iron) {
        this.recipe_nutritional_iron = recipe_nutritional_iron;
    }

    public String getRecipe_nutritional_fiber() {
        return recipe_nutritional_fiber;
    }

    public void setRecipe_nutritional_fiber(String recipe_nutritional_fiber) {
        this.recipe_nutritional_fiber = recipe_nutritional_fiber;
    }

    public String getRecipe_nutritional_folate() {
        return recipe_nutritional_folate;
    }

    public void setRecipe_nutritional_folate(String recipe_nutritional_folate) {
        this.recipe_nutritional_folate = recipe_nutritional_folate;
    }

    public String getRecipe_nutritional_potassium() {
        return recipe_nutritional_potassium;
    }

    public void setRecipe_nutritional_potassium(String recipe_nutritional_potassium) {
        this.recipe_nutritional_potassium = recipe_nutritional_potassium;
    }

    public String getRecipe_nutritional_magnesium() {
        return recipe_nutritional_magnesium;
    }

    public void setRecipe_nutritional_magnesium(String recipe_nutritional_magnesium) {
        this.recipe_nutritional_magnesium = recipe_nutritional_magnesium;
    }

    public String getRecipe_nutritional_sodium() {
        return recipe_nutritional_sodium;
    }

    public void setRecipe_nutritional_sodium(String recipe_nutritional_sodium) {
        this.recipe_nutritional_sodium = recipe_nutritional_sodium;
    }

    public String getRecipe_nutritional_energy() {
        return recipe_nutritional_energy;
    }

    public void setRecipe_nutritional_energy(String recipe_nutritional_energy) {
        this.recipe_nutritional_energy = recipe_nutritional_energy;
    }

    public String getRecipe_nutritional_niacin() {
        return recipe_nutritional_niacin;
    }

    public void setRecipe_nutritional_niacin(String recipe_nutritional_niacin) {
        this.recipe_nutritional_niacin = recipe_nutritional_niacin;
    }

    public String getRecipe_nutritional_phosphorus() {
        return recipe_nutritional_phosphorus;
    }

    public void setRecipe_nutritional_phosphorus(String recipe_nutritional_phosphorus) {
        this.recipe_nutritional_phosphorus = recipe_nutritional_phosphorus;
    }

    public String getRecipe_nutritional_protein() {
        return recipe_nutritional_protein;
    }

    public void setRecipe_nutritional_protein(String recipe_nutritional_protein) {
        this.recipe_nutritional_protein = recipe_nutritional_protein;
    }

    public String getRecipe_nutritional_riboflavin() {
        return recipe_nutritional_riboflavin;
    }

    public void setRecipe_nutritional_riboflavin(String recipe_nutritional_riboflavin) {
        this.recipe_nutritional_riboflavin = recipe_nutritional_riboflavin;
    }

    public String getRecipe_nutritional_sugars() {
        return recipe_nutritional_sugars;
    }

    public void setRecipe_nutritional_sugars(String recipe_nutritional_sugars) {
        this.recipe_nutritional_sugars = recipe_nutritional_sugars;
    }

    public String getRecipe_nutritional_thiamin() {
        return recipe_nutritional_thiamin;
    }

    public void setRecipe_nutritional_thiamin(String recipe_nutritional_thiamin) {
        this.recipe_nutritional_thiamin = recipe_nutritional_thiamin;
    }

    public String getRecipe_nutritional_vitamin_e() {
        return recipe_nutritional_vitamin_e;
    }

    public void setRecipe_nutritional_vitamin_e(String recipe_nutritional_vitamin_e) {
        this.recipe_nutritional_vitamin_e = recipe_nutritional_vitamin_e;
    }

    public String getRecipe_nutritional_vitamin_a() {
        return recipe_nutritional_vitamin_a;
    }

    public void setRecipe_nutritional_vitamin_a(String recipe_nutritional_vitamin_a) {
        this.recipe_nutritional_vitamin_a = recipe_nutritional_vitamin_a;
    }

    public String getRecipe_nutritional_vitamin_b12() {
        return recipe_nutritional_vitamin_b12;
    }

    public void setRecipe_nutritional_vitamin_b12(String recipe_nutritional_vitamin_b12) {
        this.recipe_nutritional_vitamin_b12 = recipe_nutritional_vitamin_b12;
    }

    public String getRecipe_nutritional_vitamin_b6() {
        return recipe_nutritional_vitamin_b6;
    }

    public void setRecipe_nutritional_vitamin_b6(String recipe_nutritional_vitamin_b6) {
        this.recipe_nutritional_vitamin_b6 = recipe_nutritional_vitamin_b6;
    }

    public String getRecipe_nutritional_vitamin_c() {
        return recipe_nutritional_vitamin_c;
    }

    public void setRecipe_nutritional_vitamin_c(String recipe_nutritional_vitamin_c) {
        this.recipe_nutritional_vitamin_c = recipe_nutritional_vitamin_c;
    }

    public String getRecipe_nutritional_vitamin_d() {
        return recipe_nutritional_vitamin_d;
    }

    public void setRecipe_nutritional_vitamin_d(String recipe_nutritional_vitamin_d) {
        this.recipe_nutritional_vitamin_d = recipe_nutritional_vitamin_d;
    }

    public String getRecipe_nutritional_vitamin_k() {
        return recipe_nutritional_vitamin_k;
    }

    public void setRecipe_nutritional_vitamin_k(String recipe_nutritional_vitamin_k) {
        this.recipe_nutritional_vitamin_k = recipe_nutritional_vitamin_k;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(recipe_nutritional_recipe);
        dest.writeString(recipe_nutritional_calcium);
        dest.writeString(recipe_nutritional_carbs);
        dest.writeString(recipe_nutritional_cholesterol);
        dest.writeString(recipe_nutritional_monounsaturated);
        dest.writeString(recipe_nutritional_polyunsaturated);
        dest.writeString(recipe_nutritional_saturated);
        dest.writeString(recipe_nutritional_fat);
        dest.writeString(recipe_nutritional_trans);
        dest.writeString(recipe_nutritional_iron);
        dest.writeString(recipe_nutritional_fiber);
        dest.writeString(recipe_nutritional_folate);
        dest.writeString(recipe_nutritional_potassium);
        dest.writeString(recipe_nutritional_magnesium);
        dest.writeString(recipe_nutritional_sodium);
        dest.writeString(recipe_nutritional_energy);
        dest.writeString(recipe_nutritional_niacin);
        dest.writeString(recipe_nutritional_phosphorus);
        dest.writeString(recipe_nutritional_protein);
        dest.writeString(recipe_nutritional_riboflavin);
        dest.writeString(recipe_nutritional_sugars);
        dest.writeString(recipe_nutritional_thiamin);
        dest.writeString(recipe_nutritional_vitamin_e);
        dest.writeString(recipe_nutritional_vitamin_a);
        dest.writeString(recipe_nutritional_vitamin_b12);
        dest.writeString(recipe_nutritional_vitamin_b6);
        dest.writeString(recipe_nutritional_vitamin_c);
        dest.writeString(recipe_nutritional_vitamin_d);
        dest.writeString(recipe_nutritional_vitamin_k);

    }

    @Override
    public int describeContents() {
        return 0;
    }
}




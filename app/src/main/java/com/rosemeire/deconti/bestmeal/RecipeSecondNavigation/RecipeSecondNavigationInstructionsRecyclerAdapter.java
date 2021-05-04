package com.rosemeire.deconti.bestmeal.RecipeSecondNavigation;

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
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportCheckNetworkAvailability;
import com.rosemeire.deconti.bestmeal.ApplicationSupport.SupportMessageToast;
import com.rosemeire.deconti.bestmeal.DatabaseModel.RecipeInstructionsModel;
import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolMaintenanceInstructionsActivity;
import com.rosemeire.deconti.bestmeal.RecipeTool.RecipeToolTalkerActivity;

import java.util.List;
import java.util.Locale;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.CRUD_TYPE_U;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.FILE_TYPE;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.PATH_INSTRUCTIONS_1;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationDatabaseDefinitions.sTypeCRUD;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeAuthor;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionNumber;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionPhoto;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeInstructionText;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentUserFirebaseUid;
import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sReturnCode;

/* ************************************************************************************************/
/* *** Treat recycler view
/* ************************************************************************************************/
public class RecipeSecondNavigationInstructionsRecyclerAdapter extends RecyclerView.Adapter<RecipeSecondNavigationInstructionsRecyclerAdapter.ViewHolder> {

    private final List<RecipeInstructionsModel> mValues;
    private final Context mContext;
    private Intent intent;
    private TextToSpeech textToSpeech;

    /* ********************************************************************************************/
    /* *** Load data to update recycler view
    /* ********************************************************************************************/
    RecipeSecondNavigationInstructionsRecyclerAdapter(List<RecipeInstructionsModel> items, RecipeSecondNavigationInstructionsFragment.OnListFragmentInteractionListener listener, Context context) {

        mValues = items;
        @SuppressWarnings("UnnecessaryLocalVariable") RecipeSecondNavigationInstructionsFragment.OnListFragmentInteractionListener mListener = listener;
        mContext = context;
    }

    /* ********************************************************************************************/
    /* *** Recycler view OnCreate
    /* ********************************************************************************************/
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_second_navigation_fragment_instructions_item, parent, false);

        textToSpeech = new TextToSpeech(parent.getContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != TextToSpeech.ERROR) {
                    textToSpeech.setLanguage(Locale.US);
                }
            }
        });

        return new ViewHolder(view);

    }

    /* ********************************************************************************************/
    /* *** Update recycler view
    /* ********************************************************************************************/
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        // ................................................................. Get value from position
        RecipeInstructionsModel model = mValues.get(position);

        // .................................................................... Set data from holder
        holder.recipeDetailInstructionsModelItem = model;

        holder.textView_InstructionsText.setText(model.getRecipe_instructions_text());
        holder.textView_InstructionNumber.setText(model.getRecipe_instructions_number());

        // ................................................................ Obtain photo from recipe
        obtainRecipePhoto(holder, mContext);

        sCurrentRecipeInstructionNumber = holder.textView_InstructionNumber.getText().toString().trim();

        sCurrentRecipeInstructionPhoto = model.getRecipe_instructions_photo();
        obtainRecipePhoto(holder, mContext);

        // ........................................................ Save data to maintenance classes
        sCurrentRecipeInstructionText = holder.textView_InstructionsText.getText().toString().trim();
        sCurrentRecipeInstructionNumber = holder.textView_InstructionNumber.getText().toString().trim();

        /* ****************************************************************************************/
        /* *** Treat click on button HANDS OFF
        /* ****************************************************************************************/
        holder.imageButton_handsOff.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sCurrentRecipeInstructionText = holder.textView_InstructionsText.getText().toString();

                intent = new Intent(view.getContext(), RecipeToolTalkerActivity.class);
                mContext.startActivity(intent);

            }
        });

        /* ****************************************************************************************/
        /* *** Treat click on button EDIT
        /* ****************************************************************************************/
        holder.imageButton_edit.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {

                sReturnCode = SupportCheckNetworkAvailability.isNetworkAvailable(mContext);

                if (!sReturnCode) {

                    new SupportMessageToast(mContext, mContext.getString(R.string.message_network_not_available));

                }else {

                    if (sCurrentRecipeAuthor.equals(sCurrentUserFirebaseUid)) {

                        sTypeCRUD = CRUD_TYPE_U;
                        new RecipeToolMaintenanceInstructionsActivity();

                    } else {

                        new SupportMessageToast(mContext, mContext.getString(R.string.message_recipe_not_yours));

                    }
                }
            }
        });
        /* ****************************************************************************************/
        /* *** Treat click on ITEM
        /* ****************************************************************************************/
        holder.mView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

            }
        });
    }

    /* ********************************************************************************************/
    /* *** Update recipe photo
    /* ********************************************************************************************/
    private void obtainRecipePhoto(final ViewHolder holder, final Context context) {

        StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(PATH_INSTRUCTIONS_1).child(sCurrentRecipeInstructionPhoto + FILE_TYPE);

        storageReference.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {

                if (task.isSuccessful()) {
                    Glide.with(context)
                            .load(task.getResult())
                            .apply(RequestOptions.centerCropTransform())
                            .into(holder.imageView_photo);
                }
            }
        });

    }

    /* ********************************************************************************************/
    /* *** Get Recycler View Size
    /* ********************************************************************************************/
    @Override
    public int getItemCount() {

        return mValues.size();
    }

    /* ********************************************************************************************/
    /* *** A ViewHolder describes an item view and metadata about its place within the RecyclerView
    /* ********************************************************************************************/
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        public final ImageView imageView_photo;

        public final ImageButton imageButton_edit;
        final ImageButton imageButton_handsOff;

        final TextView textView_InstructionsText;
        final TextView textView_InstructionNumber;

        RecipeInstructionsModel recipeDetailInstructionsModelItem;

        public ViewHolder(View view) {
            super(view);

            mView = view;

            imageView_photo = view.findViewById(R.id.imageView_InstructionsPhoto);
            imageButton_handsOff = view.findViewById(R.id.button_talker);
            imageButton_edit = view.findViewById(R.id.imageButton_edit);
            textView_InstructionsText = view.findViewById(R.id.textView_InstructionsText);
            textView_InstructionNumber = view.findViewById(R.id.textView_IngredientNumber);
        }
    }
}

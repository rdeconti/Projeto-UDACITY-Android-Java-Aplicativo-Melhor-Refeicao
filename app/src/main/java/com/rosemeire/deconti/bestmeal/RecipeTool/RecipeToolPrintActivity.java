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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.print.pdf.PrintedPdfDocument;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rosemeire.deconti.bestmeal.R;
import com.rosemeire.deconti.bestmeal.RecipeFirstNavigation.RecipeFirstNavigationCaptainActivity;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Objects;

import static com.rosemeire.deconti.bestmeal.ApplicationDefinitions.ApplicationGeneralDefinitions.sCurrentRecipeName;

/* ************************************************************************************************/
/* *** PRINT RECIPE
/* ************************************************************************************************/
public class RecipeToolPrintActivity extends AppCompatActivity {

    /* ********************************************************************************************/
    /* *** TREAT ON CREATE
    /* ********************************************************************************************/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // .............................................................................. Set Layout
        setContentView(R.layout.recipe_tool_print_activity);

        // ............................................................. Set Toolbar with back arrow
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.app_name);
        getSupportActionBar().setSubtitle(R.string.label_print);
    }

    /* ********************************************************************************************/
    /* *** PRINT DOCUMENT
    /* ********************************************************************************************/
    @SuppressLint("ObsoleteSdkInt")
    public void printDocument(View view) {

        PrintManager printManager = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {

            printManager = (PrintManager) this.getSystemService(Context.PRINT_SERVICE);
        }

        String jobName = this.getString(R.string.app_name) + getString(R.string.print_document);

        assert printManager != null;
        printManager.print(jobName, new MyPrintDocumentAdapter(this), null);
    }

    /* ********************************************************************************************/
    /* *** TREAT PRINT ADAPTER
    /* ********************************************************************************************/
    @SuppressWarnings("ConstantConditions")
    public class MyPrintDocumentAdapter extends PrintDocumentAdapter {

        PdfDocument myPdfDocument;
        final int totalpages = 4;
        final Context context;
        private int pageHeight;
        private int pageWidth;

        MyPrintDocumentAdapter(Context context) {
            this.context = context;
        }

        @Override
        public void onLayout(PrintAttributes oldAttributes,
                             PrintAttributes newAttributes,
                             CancellationSignal cancellationSignal,
                             LayoutResultCallback callback,
                             Bundle metadata) {

            myPdfDocument = new PrintedPdfDocument(context, newAttributes);

            pageHeight = Objects.requireNonNull(newAttributes.getMediaSize()).getHeightMils() / 1000 * 72;
            pageWidth = newAttributes.getMediaSize().getWidthMils() / 1000 * 72;

            if (cancellationSignal.isCanceled()) {
                callback.onLayoutCancelled();
                return;
            }

            //noinspection ConstantConditions
            if (totalpages > 0) {

                PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                         .Builder(sCurrentRecipeName + getString(R.string.label_print_pdf))
                        .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                        .setPageCount(totalpages);

                PrintDocumentInfo info = builder.build();

                callback.onLayoutFinished(info, true);

            } else {

                callback.onLayoutFailed(getString(R.string.print_page_count_is_zero));

            }
        }

        /* ********************************************************************************************/
        /* *** TREAT ON WRITE
        /* ********************************************************************************************/
        @Override
        public void onWrite(final PageRange[] pageRanges,
                            final ParcelFileDescriptor destination,
                            final CancellationSignal cancellationSignal,
                            final WriteResultCallback callback) {

            for (int i = 0; i < totalpages; i++) {

                if (pageInRange(pageRanges, i)) {

                    PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth, pageHeight, i).create();
                    PdfDocument.Page page = myPdfDocument.startPage(newPage);

                    if (cancellationSignal.isCanceled()) {
                        callback.onWriteCancelled();
                        myPdfDocument.close();
                        myPdfDocument = null;
                        return;
                    }

                    drawPage(page, i);
                    myPdfDocument.finishPage(page);
                }
            }

            try {

                myPdfDocument.writeTo(new FileOutputStream (destination.getFileDescriptor()));

            } catch (IOException e) {

                callback.onWriteFailed(e.toString());
                return;

            } finally {

                myPdfDocument.close();
                myPdfDocument = null;

            }

            callback.onWriteFinished(pageRanges);
        }

        /* ********************************************************************************************/
        /* *** TREAT PAGE
        /* ********************************************************************************************/
        private boolean pageInRange(PageRange[] pageRanges, int page) {

            for (PageRange pageRange : pageRanges) {

                if ((page >= pageRange.getStart()) && (page <= pageRange.getEnd()))

                    return true;

            }

            return false;
        }

        /* ********************************************************************************************/
        /* *** TREAT DRAW PAGE
        /* ********************************************************************************************/
        private void drawPage(PdfDocument.Page page, int pagenumber) {

            Canvas canvas = page.getCanvas();
            pagenumber++;
            int titleBaseLine = 72;
            int leftMargin = 54;
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(40);

            canvas.drawText(
                    "Test Print Document Page " + pagenumber,
                    leftMargin,
                    titleBaseLine,
                    paint);

            paint.setTextSize(14);

            canvas.drawText(getString(R.string.print_check),
                    leftMargin, titleBaseLine + 35, paint);

            if (pagenumber % 2 == 0)

                paint.setColor(Color.RED);

            else

                paint.setColor(Color.GREEN);

            PdfDocument.PageInfo pageInfo = page.getInfo();

            //noinspection IntegerDivisionInFloatingPointContext,IntegerDivisionInFloatingPointContext
            canvas.drawCircle(pageInfo.getPageWidth() / 2,pageInfo.getPageHeight() / 2,150, paint);
        }
    }

    /* ********************************************************************************************/
    /* *** TREAT CLICK ON TOP RIGHT ARROW
    /* ********************************************************************************************/
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    /* ********************************************************************************************/
    /* *** TREAT CLICK ON TOP RIGHT ARROW
    /* ********************************************************************************************/
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /* ********************************************************************************************/
    /* *** Inflate menu: this adds items to the action bar if it is present.
    /* ********************************************************************************************/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.home_navigation_menu_activity, menu);
        return true;
    }

    /* ********************************************************************************************/
    /* *** Handle action bar item clicks here. The action bar will automatically handle clicks on
    /* *** the Home/Up button, so long as you specify a parent activity in AndroidManifest.xml.
    /* ********************************************************************************************/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int menuItem = item.getItemId();

        switch (menuItem) {

            case R.id.home:

                Intent intent = new Intent(getApplicationContext(), RecipeFirstNavigationCaptainActivity.class);
                startActivity(intent);
                break;

        }

        return super.onOptionsItemSelected(item);

    }
}

package com.progress;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.R;


public class ProgressDialog extends Dialog {


    private ProgressLoader mLoaderView;
    private PrConfiguration mConfiguration;
    private LinearLayout mLayout;
    private TextView mLoadingText;
    private boolean showWindow = true;

    // =============================================================================================
    // BHARAT
    // =============================================================================================

    public ProgressDialog(Context context) {
        super(context);
    }

    public ProgressDialog(Context context, PrConfiguration configuration) {
        super(context);
        mConfiguration = configuration;
    }

    public ProgressDialog(Context context, int themeResId, PrConfiguration configuration) {
        super(context, themeResId);
        mConfiguration = configuration;
    }

    public ProgressDialog(Context context, boolean cancelable, OnCancelListener cancelListener, PrConfiguration configuration) {
        super(context, cancelable, cancelListener);
        mConfiguration = configuration;
    }

    // =============================================================================================
    // METHODS
    // =============================================================================================

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        setContentView(R.layout.loader_layout);

        mLoaderView = findViewById(R.id.loader);
        mLoadingText = findViewById(R.id.loadertext);
        mLayout = findViewById(R.id.window);
        mLoadingText.setTextColor(Color.BLACK);

        if (mConfiguration != null) {
            mLoaderView.refreshArcLoaderDrawable(mConfiguration);
            updateLoadingText(mConfiguration);
        }

        if (showWindow) {
            GradientDrawable gd = new GradientDrawable();
            gd.setColor(Color.WHITE);
            gd.setCornerRadius(10);
            gd.setStroke(2, Color.WHITE);
            mLayout.setBackgroundDrawable(gd);

            if (mConfiguration != null && mConfiguration.getTextColor() == Color.WHITE)
                mLoadingText.setTextColor(Color.BLACK);
        }
    }

    private void updateLoadingText(PrConfiguration configuration) {
        String text = configuration.getText();

        if (text.trim().length() == 0) {
            mLoadingText.setVisibility(View.GONE);
        } else {
            mLoadingText.setText(configuration.getText());
        }


        Typeface typeface = configuration.getTypeFace();
        if (typeface != null)
            mLoadingText.setTypeface(typeface);

        int textSize = configuration.getTextSize();

        if (textSize > 0)
            mLoadingText.setTextSize(textSize);


        mLoadingText.setTextColor(mConfiguration.getTextColor());
    }

    public LinearLayout getLayout() {
        return mLayout;
    }

    public TextView getLoadingTextView() {
        return mLoadingText;
    }

    public void setConfiguration(PrConfiguration configuration) {
        mConfiguration = configuration;
    }

    public void showWindow(boolean state) {
        showWindow = true;
    }
}
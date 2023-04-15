package com.smoothie.monetcolors;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.WindowInsets;

import androidx.annotation.NonNull;

public class BottomInsetAwareDialog extends Dialog {

    public BottomInsetAwareDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        View rootView = findViewById(R.id.dialog_root_view);

        int bottomInset;
        WindowInsets insets = getWindow().getDecorView().getRootWindowInsets();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R)
            bottomInset = insets.getInsetsIgnoringVisibility(WindowInsets.Type.systemBars()).bottom;
        else
            bottomInset = insets.getStableInsetBottom();

        rootView.setPadding(0, 0, 0, bottomInset);
    }

}

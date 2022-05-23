package com.smoothie.monetcolors;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CopyEntryView extends LinearLayout {

    int icon;
    String name = "Copy as what?";

    public CopyEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BuildView(context, attrs);
    }

    public CopyEntryView(Context context) {
        super(context);
        BuildView(context, null);
    }

    void BuildView(Context context, AttributeSet attrs){
        inflate(context, R.layout.dialog_variants_entry, this);
        if (attrs != null) {
            TypedArray obtainedAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CopyEntryView, 0, 0);
            icon = obtainedAttributes.getResourceId(R.styleable.CopyEntryView_entry_icon, R.drawable.ic_launcher_foreground);
            name = obtainedAttributes.getString(R.styleable.CopyEntryView_entry_name);
        }
        ((ImageView) findViewById(R.id.icon)).setImageResource(icon);
        ((TextView) findViewById(R.id.text)).setText(name);
    }
}

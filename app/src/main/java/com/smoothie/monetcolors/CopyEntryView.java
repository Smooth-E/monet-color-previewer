package com.smoothie.monetcolors;

import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CopyEntryView extends LinearLayout {

    int icon;
    String name = "Copy as what?";
    String fileStart = "defaultTemplate {\n";
    String entryStart = "    \"";
    String entryMiddle = "\": \"";
    String entryEnd = "\";\n";
    String fileEnd = "}";

    public CopyEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BuildView(context, attrs);
    }

    public CopyEntryView(Context context) {
        super(context);
        BuildView(context, null);
    }

    private void onClick() {
        Log.d("TAG", "onClick: clicked!");

        Dialog dialog = new Dialog(getContext(), R.style.SimpleDialog);
        dialog.setContentView(R.layout.dialog_copy_preview);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setGravity(Gravity.BOTTOM);

        MainActivity.Entry[] colors = MainActivity.getColors();
        StringBuilder templatePreview = new StringBuilder(fileStart)
                .append(entryStart).append(colors[0].getShortName())
                .append(entryMiddle).append(colors[0].getHEX(getContext()))
                .append(entryEnd)
                .append(entryStart).append(colors[1].getShortName())
                .append(entryMiddle).append(colors[1].getHEX(getContext()))
                .append(entryEnd)
                .append("...\n").append(fileEnd);
        ((TextView) dialog.findViewById(R.id.template_preview)).setText(templatePreview);

        Log.d("TAG", "onClick: clicked!");

        dialog.show();
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
        findViewById(R.id.clickable).setOnClickListener((View v) -> onClick());
    }
}

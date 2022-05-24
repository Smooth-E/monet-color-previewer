package com.smoothie.monetcolors;

import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CopyEntryView extends LinearLayout {

    int icon;
    String name = "Copy as what?";
    String fileStart = "defaultTemplate {\n";
    String entryStart = "    \"";
    String entryMiddle = "\": \"";
    String entryEnd = "\";\n";
    String fileEnd = "}";
    boolean rgbMode;

    public CopyEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BuildView(context, attrs);
    }

    public CopyEntryView(Context context) {
        super(context);
        BuildView(context, null);
    }

    private String getStringForEntry(int entryID) {
        MainActivity.Entry color = MainActivity.getColors()[entryID];
        StringBuilder stringBuilder = new StringBuilder(entryStart)
                .append(color.getShortName())
                .append(entryMiddle);
        if (rgbMode) {
            stringBuilder.append(Color.red(color.getColor()));
            stringBuilder.append(", ");
            stringBuilder.append(Color.green(color.getColor()));
            stringBuilder.append(", ");
            stringBuilder.append(Color.blue(color.getColor()));
        }
        else stringBuilder.append(color.getHEX(getContext()));
        stringBuilder.append(entryEnd);
        return stringBuilder.toString();
    }
    
    private void onTemplateClick() {
        Context context = getContext();
        StringBuilder stringBuilder = new StringBuilder(fileStart);
        for (int i = 0; i < MainActivity.getColors().length; i++) stringBuilder.append(getStringForEntry(i));
        stringBuilder.append(fileEnd);
        
        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Monet Color Value", stringBuilder.toString());
        clipboard.setPrimaryClip(clip);
        Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show();
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
                .append(getStringForEntry(0))
                .append(getStringForEntry(1))
                .append("   ...\n").append(fileEnd);
        ((TextView) dialog.findViewById(R.id.template_preview)).setText(templatePreview);

        dialog.findViewById(R.id.button_copy).setOnClickListener((View v) -> onTemplateClick());

        dialog.show();
    }

    private String getString(TypedArray attributeSet, int resourceID, String defaultValue) {
        String string = attributeSet.getString(resourceID);
        return string == null ? defaultValue : string;
    }

    void BuildView(Context context, AttributeSet attrs) {
        inflate(context, R.layout.dialog_variants_entry, this);
        if (attrs != null) {
            TypedArray obtainedAttributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.CopyEntryView, 0, 0);
            icon = obtainedAttributes.getResourceId(R.styleable.CopyEntryView_entry_icon, R.drawable.ic_launcher_foreground);
            name = getString(obtainedAttributes, R.styleable.CopyEntryView_entry_name, name);
            fileStart = getString(obtainedAttributes, R.styleable.CopyEntryView_entry_file_start, fileStart);
            entryStart = getString(obtainedAttributes, R.styleable.CopyEntryView_entry_entry_start, entryStart);
            entryMiddle = getString(obtainedAttributes, R.styleable.CopyEntryView_entry_entry_middle, entryMiddle);
            entryEnd = getString(obtainedAttributes, R.styleable.CopyEntryView_entry_entry_end, entryEnd);
            fileEnd = getString(obtainedAttributes, R.styleable.CopyEntryView_entry_file_end, fileEnd);
            rgbMode = obtainedAttributes.getBoolean(R.styleable.CopyEntryView_use_rgb_pattern, false);
        }
        ((ImageView) findViewById(R.id.icon)).setImageResource(icon);
        ((TextView) findViewById(R.id.text)).setText(name);
        findViewById(R.id.clickable).setOnClickListener((View v) -> onClick());
    }
}

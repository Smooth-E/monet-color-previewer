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

    private int icon;
    private String name = "Copy as what?";
    private String fileStart = "defaultTemplate {\n";
    private String entryStart = "    \"";
    private String entryMiddle = "\": \"";
    private String entryEnd = "\";\n";
    private String fileEnd = "}";
    private boolean rgbMode;
    private Dialog parentDialog;

    private static class OnCopyClickListener implements View.OnClickListener {

        private final CopyEntryView instance;
        private final Dialog listDialog;
        private final Dialog copyDialog;

        public OnCopyClickListener(CopyEntryView instance, Dialog listDialog, Dialog copyDialog) {
            this.instance = instance;
            this.listDialog = listDialog;
            this.copyDialog = copyDialog;
        }

        @Override
        public void onClick(View view) {
            Context context = instance.getContext();
            StringBuilder stringBuilder = new StringBuilder(instance.fileStart);
            for (int i = 0; i < MainActivity.getColors().length; i++) stringBuilder.append(instance.getStringForEntry(i));
            stringBuilder.append(instance.fileEnd);

            ClipboardManager clipboard = (ClipboardManager) instance.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Monet Color Value", stringBuilder.toString());
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Copied!", Toast.LENGTH_SHORT).show();
            copyDialog.hide();
            listDialog.hide();
        }
    }

    public CopyEntryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        BuildView(context, attrs);
    }

    public CopyEntryView(Context context) {
        super(context);
        BuildView(context, null);
    }

    public void setParentDialog(Dialog dialog) {
        this.parentDialog = dialog;
    }

    private String getStringForEntry(int entryID) {
        MainActivity.Entry colorEntry = MainActivity.getColors()[entryID];
        StringBuilder stringBuilder = new StringBuilder(entryStart)
                .append(colorEntry.getShortName())
                .append(entryMiddle);
        if (rgbMode) {
            int color = colorEntry.getColor(getContext());
            stringBuilder.append(Color.red(color));
            stringBuilder.append(", ");
            stringBuilder.append(Color.green(color));
            stringBuilder.append(", ");
            stringBuilder.append(Color.blue(color));
        }
        else stringBuilder.append(colorEntry.getHEX(getContext()));
        stringBuilder.append(entryEnd);
        return stringBuilder.toString();
    }
    
    private void onTemplateClick() {
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

        dialog.findViewById(R.id.button_copy).setOnClickListener(new OnCopyClickListener(this, parentDialog, dialog));

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

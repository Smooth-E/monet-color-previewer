package com.smoothie.monetcolors;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Debug;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class MainActivity extends AppCompatActivity {

    public static class Entry {
        int color;
        String name;

        public Entry (int id, String name) {
            this.color = id;
            this.name = name;
        }
    }

    private  static Entry[] colors = new Entry[] {
            new Entry(R.color.accent1_0,    "@android:color/system_accent1_0"   ),
            new Entry(R.color.accent1_10,   "@android:color/system_accent1_10"  ),
            new Entry(R.color.accent1_50,   "@android:color/system_accent1_50"  ),
            new Entry(R.color.accent1_100,  "@android:color/system_accent1_100" ),
            new Entry(R.color.accent1_200,  "@android:color/system_accent1_200" ),
            new Entry(R.color.accent1_300,  "@android:color/system_accent1_300" ),
            new Entry(R.color.accent1_400,  "@android:color/system_accent1_400" ),
            new Entry(R.color.accent1_500,  "@android:color/system_accent1_500" ),
            new Entry(R.color.accent1_600,  "@android:color/system_accent1_600" ),
            new Entry(R.color.accent1_700,  "@android:color/system_accent1_700" ),
            new Entry(R.color.accent1_800,  "@android:color/system_accent1_800" ),
            new Entry(R.color.accent1_900,  "@android:color/system_accent1_900" ),
            new Entry(R.color.accent1_1000, "@android:color/system_accent1_1000"),

            new Entry(R.color.accent2_0,    "@android:color/system_accent2_0"   ),
            new Entry(R.color.accent2_10,   "@android:color/system_accent2_10"  ),
            new Entry(R.color.accent2_50,   "@android:color/system_accent2_50"  ),
            new Entry(R.color.accent2_100,  "@android:color/system_accent2_100" ),
            new Entry(R.color.accent2_200,  "@android:color/system_accent2_200" ),
            new Entry(R.color.accent2_300,  "@android:color/system_accent2_300" ),
            new Entry(R.color.accent2_400,  "@android:color/system_accent2_400" ),
            new Entry(R.color.accent2_500,  "@android:color/system_accent2_500" ),
            new Entry(R.color.accent2_600,  "@android:color/system_accent2_600" ),
            new Entry(R.color.accent2_700,  "@android:color/system_accent2_700" ),
            new Entry(R.color.accent2_800,  "@android:color/system_accent2_800" ),
            new Entry(R.color.accent2_900,  "@android:color/system_accent2_900" ),
            new Entry(R.color.accent2_1000, "@android:color/system_accent2_1000"),

            new Entry(R.color.accent3_0,    "@android:color/system_accent3_0"   ),
            new Entry(R.color.accent3_10,   "@android:color/system_accent3_10"  ),
            new Entry(R.color.accent3_50,   "@android:color/system_accent3_50"  ),
            new Entry(R.color.accent3_100,  "@android:color/system_accent3_100" ),
            new Entry(R.color.accent3_200,  "@android:color/system_accent3_200" ),
            new Entry(R.color.accent3_300,  "@android:color/system_accent3_300" ),
            new Entry(R.color.accent3_400,  "@android:color/system_accent3_400" ),
            new Entry(R.color.accent3_500,  "@android:color/system_accent3_500" ),
            new Entry(R.color.accent3_600,  "@android:color/system_accent3_600" ),
            new Entry(R.color.accent3_700,  "@android:color/system_accent3_700" ),
            new Entry(R.color.accent3_800,  "@android:color/system_accent3_800" ),
            new Entry(R.color.accent3_900,  "@android:color/system_accent3_900" ),
            new Entry(R.color.accent3_1000, "@android:color/system_accent3_1000"),

            new Entry(R.color.neutral1_0,    "@android:color/system_neutral1_0"   ),
            new Entry(R.color.neutral1_10,   "@android:color/system_neutral1_10"  ),
            new Entry(R.color.neutral1_50,   "@android:color/system_neutral1_50"  ),
            new Entry(R.color.neutral1_100,  "@android:color/system_neutral1_100" ),
            new Entry(R.color.neutral1_200,  "@android:color/system_neutral1_200" ),
            new Entry(R.color.neutral1_300,  "@android:color/system_neutral1_300" ),
            new Entry(R.color.neutral1_400,  "@android:color/system_neutral1_400" ),
            new Entry(R.color.neutral1_500,  "@android:color/system_neutral1_500" ),
            new Entry(R.color.neutral1_600,  "@android:color/system_neutral1_600" ),
            new Entry(R.color.neutral1_700,  "@android:color/system_neutral1_700" ),
            new Entry(R.color.neutral1_800,  "@android:color/system_neutral1_800" ),
            new Entry(R.color.neutral1_900,  "@android:color/system_neutral1_900" ),
            new Entry(R.color.neutral1_1000, "@android:color/system_neutral1_1000"),

            new Entry(R.color.neutral2_0,    "@android:color/system_neutral2_0"   ),
            new Entry(R.color.neutral2_10,   "@android:color/system_neutral2_10"  ),
            new Entry(R.color.neutral2_50,   "@android:color/system_neutral2_50"  ),
            new Entry(R.color.neutral2_100,  "@android:color/system_neutral2_100" ),
            new Entry(R.color.neutral2_200,  "@android:color/system_neutral2_200" ),
            new Entry(R.color.neutral2_300,  "@android:color/system_neutral2_300" ),
            new Entry(R.color.neutral2_400,  "@android:color/system_neutral2_400" ),
            new Entry(R.color.neutral2_500,  "@android:color/system_neutral2_500" ),
            new Entry(R.color.neutral2_600,  "@android:color/system_neutral2_600" ),
            new Entry(R.color.neutral2_700,  "@android:color/system_neutral2_700" ),
            new Entry(R.color.neutral2_800,  "@android:color/system_neutral2_800" ),
            new Entry(R.color.neutral2_900,  "@android:color/system_neutral2_900" ),
            new Entry(R.color.neutral2_1000, "@android:color/system_neutral2_1000"),
    };

    private FrameLayout colorDroplet;
    private TextView colorName;
    private View backgroundView;
    private int selectedColorPosition;

    private void colorOnClick (int position) {
        Entry entry = colors[position];
        backgroundView.setBackgroundTintList(ColorStateList.valueOf(getColor(entry.color)));
        colorDroplet.setBackgroundTintList(ColorStateList.valueOf(getColor(entry.color)));
        colorName.setText(entry.name);
        selectedColorPosition = position;
    }

    private void copyColor() {
        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        Entry entry = colors[selectedColorPosition];
        String colorCode = String.format("#%06X", (0xFFFFFF & getColor(entry.color)));
        String text = entry.name + "\n" + colorCode;
        ClipData clip = ClipData.newPlainText("Monet Color Value", text);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(this, "Copied! " + colorCode, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorDroplet = findViewById(R.id.color_droplet);
        colorName = findViewById(R.id.color_name_text_view);
        backgroundView = findViewById(R.id.background);

        LayoutInflater inflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        LinearLayout parent = (LinearLayout) findViewById(R.id.color_list);
        for (int y = 0; y < 13; y++) {
            LinearLayout rowLayout = (LinearLayout) inflater.inflate(R.layout.layout_color_row, parent, false);
            for (int x = 0; x < 5; x++) {
                FrameLayout layout = (FrameLayout) inflater.inflate(R.layout.layout_color_entry, rowLayout, false);
                int position = x * 13 + y;
                layout.setBackgroundTintList(ColorStateList.valueOf(getColor(colors[position].color)));
                layout.setOnClickListener((View v) -> colorOnClick(position));
                rowLayout.addView(layout);
            }
            parent.addView(rowLayout);
        }
        colorOnClick(4);

        findViewById(R.id.button_copy_clickable).setOnClickListener((View v) -> copyColor());
    }
}
package com.smoothie.monetcolors;

import android.content.Context;

public class ColorEntry {

    public final int colorID;
    public final String name;
    public final String shortName;

    public int getColor(Context context) {
        return context.getColor(colorID);
    }

    public String getHEX(Context context) {
        return String.format("#%06X", (0xFFFFFF & context.getColor(colorID)));
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public ColorEntry(int id, String name) {
        this.colorID = id;
        this.name = name;
        this.shortName = name.substring("@android:color/system_".length());
    }

}

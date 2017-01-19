package com.ho23a.flood_it;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;

/**
 * Created by demouser on 1/17/17.
 */

public class Settings {
    private ColorScheme colorScheme;
    private Level level;

    private Settings(){}

    private static Settings instance;

    public static Settings getIntsance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

    // Settings.getInstance().getLevel();

    public void setColorScheme(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public ColorScheme getColorScheme() { return colorScheme; }

    public Level getLevel() { return level; }
}

enum Level {
    EASY ,
    MEDIUM,
    HARD,
    DEFAULT
}

enum ColorScheme {
    DEFAULT
}

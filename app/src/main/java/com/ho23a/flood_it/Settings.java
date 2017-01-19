package com.ho23a.flood_it;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.content.ContextCompat;

/**
 * Created by demouser on 1/17/17.
 */

public class Settings implements Parcelable {
    private ColorScheme colorScheme;
    private Level level;
//    private int[] buttonColors;
    private String colorSchemeLabel;
    private String levelLabel;

    public Settings(String colorSchemeLabel, String levelLabel) {
        this.colorSchemeLabel = colorSchemeLabel;
        this.levelLabel = levelLabel;
        init();
    }

    private void init() {
        switch(colorSchemeLabel) {
            case "":
                colorScheme = ColorScheme.DEFAULT;
                break;
            default:
                break;
        }

        switch(levelLabel) {
            case "Select Level":
                level = Level.DEFAULT;
                break;
            case "Easy":
                level = Level.EASY;
                break;
            case "Medium":
                level = Level.MEDIUM;
                break;
            case "Hard":
                level = Level.HARD;
                break;
            default:
                break;
        }
    }

    public void setColorScheme(ColorScheme colorScheme) {
        this.colorScheme = colorScheme;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

//    public void setButtonColors(int[] buttonColors) {
//        this.buttonColors = buttonColors;
//    }

    public ColorScheme getColorScheme() { return colorScheme; }

    public Level getLevel() { return level; }

//    public int[] getButtonColors() { return buttonColors; }

    //write object values to parcel for storage
    public void writeToParcel(Parcel out, int flags){
        //write all properties to the parcle
        out.writeString(colorSchemeLabel);
        out.writeString(levelLabel);
//        out.writeIntArray(buttonColors);
    }

    //constructor used for parcel
    public Settings(Parcel parcel){
        //read and set saved values from parcel
        colorSchemeLabel = parcel.readString();
        levelLabel = parcel.readString();
//        buttonColors = parcel.createIntArray();
        init();
    }

    //creator - used when un-parceling our parcle (creating the object)
    public static final Parcelable.Creator<Settings> CREATOR = new Parcelable.Creator<Settings>(){

        @Override
        public Settings createFromParcel(Parcel parcel) {
            return new Settings(parcel);
        }

        @Override
        public Settings[] newArray(int size) {
            return new Settings[0];
        }
    };

    //return hashcode of object
    public int describeContents() {
        return hashCode();
    }
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

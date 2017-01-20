package com.ho23a.flood_it;

public class Settings {
    private ColorScheme colorScheme;
    private Level level;

    private Settings(){
        level = Level.DEFAULT;
    }

    private static Settings instance;

    public static Settings getIntsance() {
        if (instance == null) {
            instance = new Settings();
        }
        return instance;
    }

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

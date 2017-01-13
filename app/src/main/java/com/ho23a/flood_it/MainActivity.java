package com.ho23a.flood_it;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private Level level = Level.MEDIUM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setLevel(String selectedLevel) {
        switch (selectedLevel) {
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
                level = Level.MEDIUM;
                break;
        }
    }
}

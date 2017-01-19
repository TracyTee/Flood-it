package com.ho23a.flood_it;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private TextView stepsText;
    private GridView boardView;

    private ColorScheme colorScheme;
    private Level level;
    private Board board;
    private HashMap<Level, Integer> levelToSizeMap = new HashMap<>();
//    private int[] buttonColors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Settings settings = intent.getParcelableExtra(SettingsActivity.SETTINGS_LABEL);

        colorScheme = settings.getColorScheme();
        level = settings.getLevel();
        board = new Board();

        // set steps to stepstext
        stepsText = ((TextView) findViewById(R.id.stepsText));
        boardView = ((GridView) findViewById(R.id.boardView));

        levelToSizeMap.put(Level.DEFAULT, 2);
        levelToSizeMap.put(Level.EASY, 2);
        levelToSizeMap.put(Level.MEDIUM, 4);
        levelToSizeMap.put(Level.HARD, 8);

        createBoardView();
        createButtons();
    }

    private void createBoardView() {
        int size = levelToSizeMap.get(level);

//        board.createBoard(level);
    }

    private void createButtons() {
    }

}

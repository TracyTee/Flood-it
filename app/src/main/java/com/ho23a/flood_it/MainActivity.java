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

import static com.ho23a.flood_it.Level.DEFAULT;
import static com.ho23a.flood_it.Level.EASY;
import static com.ho23a.flood_it.Level.HARD;
import static com.ho23a.flood_it.Level.MEDIUM;

public class MainActivity extends AppCompatActivity {
    private TextView stepsText;
    private GridView boardView;

    private ColorScheme colorScheme;
    private Level level;
    private Board board;
    private HashMap<Level, Integer> levelToSizeMap = new HashMap<>();
//    private int[] buttonColors;

    private HashMap<Level, Integer> levelToNumSteps = new HashMap<>();
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

        levelToSizeMap.put(DEFAULT, 2);
        levelToSizeMap.put(EASY, 2);
        levelToSizeMap.put(Level.MEDIUM, 4);
        levelToSizeMap.put(Level.HARD, 8);

        createBoardView();
        createButtons();
        setNumSteps();
    }

    private void createBoardView() {
        int size = levelToSizeMap.get(level);

//        board.createBoard(level);
    }

    private void createButtons() {
    }

    
    private void setNumSteps(){
        levelToNumSteps.put(DEFAULT,4);
        levelToNumSteps.put(EASY,4);
        levelToNumSteps.put(MEDIUM,12);
        levelToNumSteps.put(HARD,16);


    }
}

package com.ho23a.flood_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView stepsText;
    private BoardView boardView;

    private ColorScheme colorScheme;
    private Level level = Level.DEFAULT;
    private Board board;
    private HashMap<Level, Integer> levelToSizeMap = new HashMap<>();

    private HashMap<Level, Integer> levelToNumSteps = new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Settings settings = intent.getParcelableExtra(SettingsActivity.SETTINGS_LABEL);
        colorScheme = settings.getColorScheme();
        level = settings.getLevel();

        setBoardSize();
        board = new Board(levelToSizeMap.get(level));

        setNumSteps();

        // set steps to stepstext
        stepsText = ((TextView) findViewById(R.id.stepsText));
        boardView = (BoardView) findViewById(R.id.board_view);
        resetBoardView();

    }

    private void resetBoardView() {
        boardView.reset(board);
    }

    private void setBoardSize() {
        levelToSizeMap.put(Level.DEFAULT, 2);
        levelToSizeMap.put(Level.EASY, 2);
        levelToSizeMap.put(Level.MEDIUM, 4);
        levelToSizeMap.put(Level.HARD, 8);
    }

    private void setNumSteps() {
        levelToNumSteps.put(Level.DEFAULT,4);
        levelToNumSteps.put(Level.EASY,4);
        levelToNumSteps.put(Level.MEDIUM,12);
        levelToNumSteps.put(Level.HARD,16);


    }
}

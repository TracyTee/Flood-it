package com.ho23a.flood_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.HashMap;

import static com.ho23a.flood_it.Level.DEFAULT;
import static com.ho23a.flood_it.Level.EASY;
import static com.ho23a.flood_it.Level.HARD;
import static com.ho23a.flood_it.Level.MEDIUM;

public class MainActivity extends AppCompatActivity {
    private TextView stepsText;
    private BoardView boardView;

    private ColorScheme colorScheme;
    private Level level = Level.DEFAULT;
    private Board board;
    private HashMap<Level, Integer> levelToSizeMap = new HashMap<>();

    private HashMap<Level, Integer> levelToNumSteps = new HashMap<>();
    private int numSteps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Settings settings = intent.getParcelableExtra(SettingsActivity.SETTINGS_LABEL);
        colorScheme = settings.getColorScheme();
        level = settings.getLevel();

        setup();
        board = new Board(levelToSizeMap.get(level));

        // set steps to stepstext
        stepsText = ((TextView) findViewById(R.id.stepsText));
        boardView = (BoardView) findViewById(R.id.board_view);
        resetBoardView();

    }

    private void resetBoardView() {
        boardView.reset(board);
    }


    private void setup() {

        //initialize level to grid size HM
        levelToSizeMap.put(Level.DEFAULT, 2);
        levelToSizeMap.put(Level.EASY, 2);
        levelToSizeMap.put(Level.MEDIUM, 4);
        levelToSizeMap.put(Level.HARD, 8);



        //initialize level to numSteps HM
        levelToNumSteps.put(DEFAULT, 4);
        levelToNumSteps.put(EASY, 4);
        levelToNumSteps.put(MEDIUM, 12);
        levelToNumSteps.put(HARD, 16);

        //Initialize label
        numSteps = levelToNumSteps.get(level);
      //  stepsText.setText(numSteps);
    }


    /**
     * update number of steps on click
     */
    private void updateNumSteps(){

    }

}

package com.ho23a.flood_it;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private TextView stepsText;
    private BoardView boardView;

    private ColorScheme colorScheme;
    private Level level = Level.DEFAULT;
    private Board board;
    private int numSteps;
    private int initialNumSteps;
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

        setup();
        board = new Board(levelToSizeMap.get(level));

        initialNumSteps = levelToNumSteps.get(level);
        numSteps = initialNumSteps;
        stepsText = ((TextView) findViewById(R.id.stepsText));
        stepsText.setText(String.format("Steps: %d/%d", initialNumSteps, initialNumSteps));

        boardView = (BoardView) findViewById(R.id.board_view);
        boardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    float screenX = event.getX();
                    float screenY = event.getY();
                    float viewX = screenX - v.getLeft();
                    float viewY = screenY - v.getTop();
                    System.out.println("Touch coordinates : " +
                            String.valueOf(viewX) + "x" + String.valueOf(viewY));
                    updateGame((int) viewX, (int) viewY);
                }
                return true;
            }
        });

        resetBoardView();

    }

    private void updateGame(int clickedX, int clickedY) {
        boolean updated = updateBoard(clickedX, clickedY);
        boolean isFilled = board.checkWon();

        if (updated) {
            // continue game, update boardView and stepsText
            updateStepsText();
            resetBoardView();
        }

        // check if won
        if (numSteps >= 0  & isFilled) {
            // won
            startActivity(new Intent(this, WinActivity.class));
        } else if (numSteps == 0 && !isFilled) {
            // lose
            startActivity(new Intent(this, LoseActivity.class));
        }
    }

    private boolean updateBoard(int clickedX, int clickedY) {
        Tile clickedTile = board.getClickedTile(clickedX, clickedY);

        if (clickedTile != null) {
            return board.updateBoard(clickedTile.getColor());
        }
        // did not update board
        return false;
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
        levelToNumSteps.put(Level.DEFAULT, 4);
        levelToNumSteps.put(Level.EASY, 4);
        levelToNumSteps.put(Level.MEDIUM, 12);
        levelToNumSteps.put(Level.HARD, 16);

        //Initialize label
        numSteps = levelToNumSteps.get(level);
      //  stepsText.setText(numSteps);
    }


    /**
     * update number of steps on click
     */
    private void updateStepsText(){
        numSteps -= 1;
        stepsText.setText(String.format("Steps: %d/%d", numSteps, initialNumSteps));
    }

}

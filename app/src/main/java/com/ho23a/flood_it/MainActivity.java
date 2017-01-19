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
        reset();
    }

    private void reset() {
        // set values
        initialNumSteps = levelToNumSteps.get(level);
        numSteps = initialNumSteps;

        // set UI
        stepsText = ((TextView) findViewById(R.id.stepsText));
        stepsText.setText(String.format(getString(R.string.steps_text), initialNumSteps, initialNumSteps));

        board = new Board(levelToSizeMap.get(level));
        boardView = (BoardView) findViewById(R.id.board_view);
        boardView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    float screenX = event.getX();
                    float screenY = event.getY();
                    int viewX = (int) (screenX - v.getLeft());
                    int viewY = (int) (screenY - v.getTop());
                    System.out.println("Touch coordinates : " +
                            String.valueOf(viewX) + "x" + String.valueOf(viewY));
                    updateGame(viewX, viewY);
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

        checkForWin(isFilled);
    }

    public static final String WIN_NUM_STEPS = "com.ho23a.flood_it.WIN_NUM_STEPS";
    public static final String GAME_WON = "com.ho23a.flood_it.GAME_WON";

    private void checkForWin(boolean isFilled) {
        if (numSteps >= 0  & isFilled) {
            // won
            int stepsUsed = initialNumSteps - numSteps;
            String winNumSteps = String.format("%d/%d", stepsUsed, initialNumSteps);

            Intent intent = new Intent (this, GameOverActivity.class);
            intent.putExtra(WIN_NUM_STEPS, winNumSteps);
            intent.putExtra(GAME_WON, true);
            startActivity(intent);

        } else if (numSteps == 0) {
            // lose
            Intent intent = new Intent (this, GameOverActivity.class);
            intent.putExtra(GAME_WON, false);
            startActivity(intent);
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
    }

    /**
     * Update number of steps on click
     */
    private void updateStepsText() {
        numSteps -= 1;
        stepsText.setText(String.format(getString(R.string.steps_text), numSteps, initialNumSteps));
    }
}

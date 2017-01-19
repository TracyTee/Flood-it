package com.ho23a.flood_it;

import android.graphics.Color;

import java.util.Random;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static com.ho23a.flood_it.Level.EASY;
import static com.ho23a.flood_it.Level.HARD;
import static com.ho23a.flood_it.Level.MEDIUM;

/**
 * Created by demouser on 1/17/17.
 */

public class Board {
    private int size;
    /* private enum Level{
         EASY,
         MEDIUM,
         HARD };*/
    private Tile board[][];
    private int numColors;
    private Random random;


    /* constructor */
    public Board(int size) {
        this.size = size;
        createBoard(size);
    }

    public Board() {}

    /**
     * initializes board and its components
     */
    public void createBoard(int size) {

        //determine number of colors based on size
        numColors = size;
        board = new Tile[size][size];
        floodFill();
    }

    /**
     * fill board with random colors
     */
    private void floodFill() {
        int colors[];
        switch (numColors) {
            case 2:
                colors = new int[]{Color.RED, Color.BLUE};
                break;
            case 4:
                colors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
                break;
            case 8:
                colors = new int[]{Color.RED, Color.BLUE, Color.GREEN, Color.BLACK, Color.YELLOW, Color.MAGENTA, Color.DKGRAY, Color.LTGRAY};
                break;
            default:
                colors = new int[]{Color.RED, Color.BLUE};
                break;
        }

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                board[r][c] = new Tile();
                random = new Random();
                int randomNum = random.nextInt(numColors);
                board[r][c].setColor(colors[randomNum]);

            }
        }
    }

    /**
     * Prints board with the color of each tile
     *
     * @return
     */
    public int[][] printBoard() {
        int[][] array = new int[size][size];
        for (int r = 0; r < array.length; r++) {
            for (int c = 0; c < array[r].length; c++) {
                //System.out.println(array[r][c] = (board[r][c].getColor()));
               if (board[r][c].getColor() == Color.BLUE) {
                    System.out.print("B ");
                } else {
                    System.out.print("R ");
                }
            }
            System.out.println();
        }
        return array;

    }

    public boolean checkWon() {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[0][0].getColor() != board[r][c].getColor()) {
                    return false;
                }
            }
        }
        return true;

    }


    public Tile[][] getBoard() {
        return board;
    }

    public Tile getClickedTile(int x, int y) {

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {

                Tile tile = board[r][c];
                int xBound = tile.getX() + size;
                int yBound = tile.getY() + size;

                //if point clicked is within tile bounds
                if ((x <= xBound && y <= yBound) && (x >= tile.getX() && y >= tile.getY())) {
                    return tile;
                }
            }
        }
        return null;
    }

    public boolean updateBoard(int newColor) {

        int oldColor = board[0][0].getColor();

        for (int r = 0; r < board.length; r++) {

            for (int c = 0; c < board[r].length; c++) {
                Tile tile = board[r][c];


                if(newColor == oldColor || board[r][c].getColor() != oldColor)
                   return false;

                updateBoard(oldColor, newColor, r, c);
                //check adjacent squares and change colors
                //  if(tile.getColor() == originalColor )
                //      tile.setColor(newColor);
                // if(checkBounds(r-1, c )){
                //    swapColor(r-1,c,originalColor,newColor);
            }


        }
        return true;
    }




    private void updateBoard(int oldColor,int newColor,int x,int y){
        if ( board[x][y].getColor() != oldColor)

           return ;

        board[x][y].setColor(newColor); //change the color of the current box

        //Make the recursive call for any neighboring boxes:
        if (x > 0)
            updateBoard( oldColor, newColor, x - 1, y) ; //on box to the left
        if (x < board.length)
            updateBoard(oldColor, newColor, x + 1, y);  //on box to the right
        if(y > 0)
            updateBoard(oldColor, newColor, x, y - 1) ; //box to up
        if (y < board.length)
            updateBoard(oldColor, newColor, x, y + 1);  //box to down

    }

    public void setXY(Tile t,int x,int y, int size){
        t.setX(x);
        t.setY(y);
        t.setSize(size);

    }

    private boolean checkBounds(int x, int y){
        if((x >= 0 && x< board.length)
            && (y >= 0 && y < board.length)){
            return true;
        }
        return false;
    }

    private void swapColor(int x, int y, int originalColor , int newColor){
        Tile tile = board[x][y];

        if(tile.getColor() == originalColor )
            tile.setColor(newColor);
    }
}

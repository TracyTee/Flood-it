package com.ho23a.flood_it;

import android.graphics.Color;

import java.util.Random;


public class Board {
    private int size;
    private Tile board[][];
    private int numColors;
    private Random random = new Random();

    /* constructor */
    public Board(int size) {
        this.size = size;
        createBoard();
    }


    /**
     * initializes board and its components
     */
    private void createBoard() {
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
                int randomNum = random.nextInt(numColors);
                board[r][c].setColor(colors[randomNum]);
            }
        }
    }

    /**
     * Prints board with the coordinates of each tile
     *
     * @return
     */
    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.println(String.format("%d, %d, %d", board[i][j].getX(), board[i][j].getY(), board[i][j].getSize()));
            }
        }

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

    /**
     *
     * @param x coordinate
     * @param y
     * @return
     */
    public Tile getClickedTile(int x, int y) {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                Tile tile = board[r][c];
                int left = tile.getX();
                int right = left + tile.getSize();
                int top = tile.getY();
                int bottom = top + tile.getSize();
                if (x <= right && x >= left && y <= bottom && y >= top) {
                    return tile;
                }
            }
        }
        return null;
    }

    public boolean updateBoard(int newColor) {
        int oldColor = board[0][0].getColor();
        if(newColor == oldColor) {
            return false;
        }
        updateBoard(oldColor, newColor, 0, 0);
        return true;
    }

    private void updateBoard(int oldColor, int newColor, int x, int y){
        if ( x < 0 || x >= size || y < 0 || y >= size || board[x][y].getColor() != oldColor) {
            return;
        }

        board[x][y].setColor(newColor); //change the color of the current box

        //Make the recursive call for any neighboring boxes:
        updateBoard(oldColor, newColor, x - 1, y) ; //on box to the left
        updateBoard(oldColor, newColor, x + 1, y);  //on box to the right
        updateBoard(oldColor, newColor, x, y - 1) ; //box to up
        updateBoard(oldColor, newColor, x, y + 1);  //box to down
    }

    public void setXY(int i, int j, int x, int y, int size){
        board[i][j].setX(x);
        board[i][j].setY(y);
        board[i][j].setSize(size);
    }

    public Tile[][] getBoard() {
        return board;
    }
}

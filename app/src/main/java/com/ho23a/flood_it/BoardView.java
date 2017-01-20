package com.ho23a.flood_it;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;


public class BoardView extends View {
    private ShapeDrawable[][] mDrawable;
    private Tile[][] board;
    private int boardSize;
    private int tileSize;

    public BoardView(Context context) { super(context); }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
        mDrawable = new ShapeDrawable[boardSize][boardSize];
    }

    public void setTileSize(int tileSize) {
        this.tileSize = tileSize;
    }

    public void reset(Tile[][] board) {
        System.out.println("boardView.reset()");
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                Tile tile = board[i][j];
                System.out.println(String.format("%d, %d, %d", tile.getX(), tile.getY(), tile.getSize()));
            }
        }
        this.board = board;
        postInvalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (widthMeasureSpec == 0 || heightMeasureSpec == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
        int size = Math.max(widthMeasureSpec, heightMeasureSpec);
        super.onMeasure(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < boardSize; i++) {
            int x = 0;
            int y = tileSize * i;
            int bottomBound = tileSize * (i+1);
            for (int j = 0; j < boardSize; j++) {
//                System.out.println(String.format("%d, %d, %d, %d", x, y, tileSize * (j + 1), bottomBound));
                mDrawable[i][j] = new ShapeDrawable();
                mDrawable[i][j].getPaint().setColor(board[i][j].getColor());
                mDrawable[i][j].setBounds(x + tileSize * j, y, tileSize * (j + 1), bottomBound);
                mDrawable[i][j].draw(canvas);
            }
        }
    }
}

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
    private Board board;

    public BoardView(Context context) {
        super(context);
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void reset(Board board) {
        this.board = board;
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
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        Tile[][] tiles = board.getBoard();
        int boardSize = tiles.length;
        int tileSize = Math.min(screenWidth, screenHeight)/ boardSize;

//        board.printBoard();

        mDrawable = new ShapeDrawable[boardSize][boardSize];
        for (int i = 0; i < boardSize; i++) {
            int x = 0;
            int y = tileSize * i;
            int bottomBound = tileSize * (i+1);
            for (int j = 0; j < boardSize; j++) {
//                System.out.println(String.format("%d, %d, %d, %d", x, y, tileSize * (j + 1), bottomBound));
                mDrawable[i][j] = new ShapeDrawable();
                mDrawable[i][j].getPaint().setColor(tiles[i][j].getColor());
                mDrawable[i][j].setBounds(x + tileSize * j, y, tileSize * (j + 1), bottomBound);
                mDrawable[i][j].draw(canvas);
            }
        }
    }
}

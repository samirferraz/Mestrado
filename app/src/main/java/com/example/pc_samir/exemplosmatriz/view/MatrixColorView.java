package com.example.pc_samir.exemplosmatriz.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by Pc-Samir on 30/06/2016.
 */
public class MatrixColorView extends View {

    private int[][] colors;
    private Paint p = new Paint();
    private Rect r = new Rect();

    public MatrixColorView(Context context) {
        super(context);
    }

    public MatrixColorView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MatrixColorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (colors != null) {

            int h = getHeight() / colors[0].length;
            int w = getWidth() / colors.length;

            for (int i = 0; i < colors.length; i++) {
                for (int j = 0; j < colors[i].length; j++) {
                    p.setColor(colors[i][j]);
                    r.set(w * i, h * j, w * (i + 1), h * (j + 1));
                    canvas.drawRect(r, p);
                    Log.d("RECT(" + i + "," + j + ")",r.flattenToString());
                }
            }
        }
    }

    public int[][] getColors() {
        return colors;
    }

    public void setColors(int[][] colors) {
        this.colors = colors;
        invalidate();
    }
}
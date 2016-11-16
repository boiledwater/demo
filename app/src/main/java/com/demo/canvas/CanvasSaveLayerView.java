package com.demo.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by HuLiZhong on 2016/11/16.
 */
public class CanvasSaveLayerView extends View {
    public CanvasSaveLayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CanvasSaveLayerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private static final int LAYER_FLAGS = Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
            | Canvas.HAS_ALPHA_LAYER_SAVE_FLAG | Canvas.FULL_COLOR_LAYER_SAVE_FLAG
            | Canvas.CLIP_TO_LAYER_SAVE_FLAG;

    private Paint mPaint;

    public CanvasSaveLayerView(Context context) {
        super(context);
        init();
    }

    private void init() {
        setFocusable(true);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        canvas.translate(101, 101);
        mPaint.setColor(Color.RED);
        canvas.drawCircle(75, 75, 75, mPaint);
        canvas.saveLayerAlpha(0, 0, 200, 200, 0x88, LAYER_FLAGS);
        mPaint.setColor(Color.BLUE);
        canvas.drawCircle(125, 125, 75, mPaint);
        canvas.restore();
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 75, mPaint);
//        canvas.drawCircle(125, 125, 75, mPaint);
    }
}

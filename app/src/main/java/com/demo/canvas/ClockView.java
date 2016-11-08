package com.demo.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.R;

/**
 * Created by HuLiZhong on 2016/11/8.
 */
public class ClockView extends View {
    public ClockView(Context context) {
        super(context);
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx, cy, radius;
        cx = (float) (getWidth() / 2.0);
        cy = (float) (getHeight() / 2.0);
        radius = (float) (getWidth() / 2.0 - 0.5);
        Paint circlePaint = new Paint();
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setAntiAlias(true);
        circlePaint.setColor(getResources().getColor(R.color.appoint));
        circlePaint.setStrokeWidth(1);
        canvas.drawCircle(cx, cy, radius, circlePaint);

        canvas.translate(cx, cy);
        for (int i = 0; i < 24; i++) {
            if (i % 6 == 0) {
                Paint boldLinePaint = new Paint();
                boldLinePaint.setColor(Color.parseColor("#000000"));

                Paint fontPaint = new Paint();
                fontPaint.setTextSize(getResources().getDimension(R.dimen.sp14));
                fontPaint.setColor(Color.parseColor("#000000"));
                float startX, startY, stopX, stopY;
                float offset = 15;
                if (i == 0) {
                    startX = 0;
                    startY = -(radius - offset);
                    stopX = 0;
                    stopY = -radius;
                    canvas.drawLine(startX, startY, stopX, stopY, boldLinePaint);
                    canvas.save();
                    canvas.translate(startX, startY);
                    canvas.drawText(i + "", -fontPaint.measureText(i + "") / 2, 10, fontPaint);
                    canvas.restore();
                } else if (i == 6) {
                    startX = (radius - offset);
                    startY = 0;
                    stopX = radius;
                    stopY = 0;
                    canvas.drawLine(startX, startY, stopX, stopY, boldLinePaint);
                    canvas.save();
                    canvas.translate(startX, startY);
                    canvas.drawText(i + "", -10, -fontPaint.measureText(i + "") / 2, fontPaint);
                    canvas.restore();
                }
            }
        }
    }
}

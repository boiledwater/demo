package com.demo.gesturelock;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.demo.DisplayUtil;
import com.demo.R;

import java.util.HashMap;

/**
 * Created by HuLiZhong on 2017/2/14.
 */
public class GesturePreView extends View {
    private HashMap<Integer, Boolean> gesture = new HashMap<>();
    private int mW;
    private int mH;
    private int radius;

    public GesturePreView(Context context) {
        super(context);
    }

    public GesturePreView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GesturePreView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        gesture.put(2, true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mW = w;
        mH = h;
        radius = (mW - DisplayUtil.dip2px(getContext(), 19.4f)) / 6;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(DisplayUtil.dip2px(getContext(), 50), MeasureSpec.EXACTLY);
        }
        if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(DisplayUtil.dip2px(getContext(), 50), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float cx;
        float cy;
        Paint paint = new Paint();

        for (int i = 0; i < 9; i++) {
            int line = i % 3;
            int row = i / 3;
            cx = (line + 1) * this.radius + line * DisplayUtil.dip2px(getContext(), 9.7f);
            cy = (row + 1) * this.radius + row * DisplayUtil.dip2px(getContext(), 9.7f);

            paint.setColor(getResources().getColor(R.color.sa_green));
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(cx, cy, radius, paint);
            Boolean heightLight = gesture.get(i);
            if (heightLight == null || !heightLight) {
                paint.setColor(getResources().getColor(R.color.cycle_bg));
//                canvas.drawCircle(cx, cy, radius - DisplayUtil.dip2px(getContext(), 1), paint);
            }
        }
    }

    public void set(HashMap<Integer, Boolean> map) {
        this.gesture = map;
        invalidate();
    }
}

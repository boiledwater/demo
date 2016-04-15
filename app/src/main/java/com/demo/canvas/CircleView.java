package com.demo.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.demo.DisplayUtil;
import com.demo.R;

import java.util.ArrayList;

/**
 * Created by hulizhong on 2016/4/15.
 */
public class CircleView extends View {
    private ArrayList<Item> data = new ArrayList<>();

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }
        data.add(new Item("09:00", 1));
        data.add(new Item("10:00", 0));
        data.add(new Item("11:00", 1));
        data.add(new Item("12:00", 0));
        data.add(new Item("13:00", 1));
        data.add(new Item("14:00", 0));
        data.add(new Item("15:00", 1));
        data.add(new Item("16:00", 1));
        data.add(new Item("17:00", 1));
        data.add(new Item("18:00", 0));
        data.add(new Item("19:00", 1));
        data.add(new Item("20:00", 1));
    }
    //    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        widthMeasureSpec = MeasureSpec.makeMeasureSpec(150, MeasureSpec.EXACTLY);
//        heightMeasureSpec = MeasureSpec.makeMeasureSpec(150, MeasureSpec.EXACTLY);
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int sw = DisplayUtil.getScreenWidth(getContext());
        float cx = sw / 2;
        float sh = DisplayUtil.getScreenHeight(getContext());
        float cy = sh / 2;
        float radius = (float) (sw * 0.3);

        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.theme_gray));
        System.err.println("------------------------------------");
        float left = (sw - radius*2) / 2;
        float top = (sh - radius*2) / 2;
        RectF rectF = new RectF(left, top, left + radius, top + radius);// 设置个新的长方形，扫描测量
        int angle = 30;
        for (int i = 0, size = data.size(); i < size; i++) {
            Item item = data.get(i);
            if (item.status == 1) {
                paint.setColor(getResources().getColor(R.color.theme_gray));
            } else {
                paint.setColor(getResources().getColor(R.color.appoint));
            }
            float startAngle = i * angle;
            float sweepAngle = (i + 1) * angle;
            sweepAngle = angle;
            System.err.println("startAngle:" + startAngle + ",sweepAngle:" + sweepAngle);
//            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
            int x;
            int y;
            if (i < 6) {
                y = (int) (top + radius * i / 12);
                x = (int) (left + radius + Math.cos(i * angle) * radius);
            } else {
                y = (int) (top + radius * (i - 6) / 12);
                x = (int) (left - radius * i / 12);
            }
            paint.setColor(getResources().getColor(R.color.colorPrimary));
            paint.setTextSize(30);
            canvas.drawText(item.time, x, y, paint);
        }
        paint.setColor(getResources().getColor(R.color.white));
//        canvas.drawCircle(cx, cy, radius / 3, paint);
    }
}

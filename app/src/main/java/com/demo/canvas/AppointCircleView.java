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
public class AppointCircleView extends View {
    private ArrayList<AppointItem> data = new ArrayList<>();

    public AppointCircleView(Context context) {
        super(context);
    }

    public AppointCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AppointCircleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (isInEditMode()) {
            return;
        }

        data.add(new AppointItem("09:00", 0));
        data.add(new AppointItem("09:30", 1));
        data.add(new AppointItem("10:00", 0));
        data.add(new AppointItem("10:30", 1));
        data.add(new AppointItem("11:00", 0));
        data.add(new AppointItem("11:30", 1));

        data.add(new AppointItem("12:00", 0));
        data.add(new AppointItem("12:30", 1));
        data.add(new AppointItem("13:00", 0));
        data.add(new AppointItem("13:30", 1));
        data.add(new AppointItem("14:00", 0));
        data.add(new AppointItem("14:30", 1));
        data.add(new AppointItem("15:00", 0));
        data.add(new AppointItem("15:30", 1));
        data.add(new AppointItem("16:00", 0));
        data.add(new AppointItem("16:30", 1));
        data.add(new AppointItem("17:00", 0));
        data.add(new AppointItem("17:30", 1));
        data.add(new AppointItem("18:00", 0));
        data.add(new AppointItem("18:30", 1));
        data.add(new AppointItem("19:00", 0));
        data.add(new AppointItem("19:30", 1));
        data.add(new AppointItem("20:00", 0));
        data.add(new AppointItem("20:30", 1));
        data.add(new AppointItem("21:00", 0));


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int sw = DisplayUtil.getScreenWidth(getContext());
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(sw, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(sw, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int sw = DisplayUtil.getScreenWidth(getContext());
        float cx = sw / 2;
        float sh = DisplayUtil.getScreenHeight(getContext());
        float cy = cx;
        float radius = (float) (sw * 0.31);


        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(getResources().getColor(R.color.theme_gray));
        System.err.println("------------------------------------");
        float left = (sw - radius * 2) / 2;
        float top = (sw - radius * 2) / 2;
        float right, bottom;
//        left = getLeft();
//        top = getTop();
//        right = getRight();
//        bottom = getBottom();
        RectF rectF = new RectF(left, top, left + radius * 2, top + radius * 2);// 设置个新的长方形，扫描测量
//        RectF rectF = new RectF(left, top, right, bottom);
        float angle = (float) 14.4;
        for (int i = 0, size = data.size(); i < size; i++) {
            AppointItem item = data.get(i);
            if (item.status == 1) {
                paint.setColor(getResources().getColor(R.color.theme_gray));
            } else {
                paint.setColor(getResources().getColor(R.color.appoint));
            }
            float startAngle = i * angle;
            float sweepAngle = (i + 1) * angle;
            sweepAngle = angle;
            System.err.println("startAngle:" + startAngle + ",sweepAngle:" + sweepAngle);
            canvas.drawArc(rectF, startAngle + 270, (float) (sweepAngle + 0.5), true, paint);
//            int x;
//            int y;
//            if (i < 6) {
//                y = (int) (top + radius * i / 12);
//                x = (int) (left + radius + Math.cos(i * angle) * radius);
//            } else {
//                y = (int) (top + radius * (i - 6) / 12);
//                x = (int) (left - radius * i / 12);
//            }
//            paint.setColor(getResources().getColor(R.color.colorPrimary));
//            paint.setTextSize(30);
//            canvas.drawText(item.time, x, y, paint);
        }
        paint.setColor(getResources().getColor(R.color.colorPrimary));
        paint.setTextSize(DisplayUtil.sp2px(getContext(), 12));
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        //draw text
        for (int i = 0, size = data.size(); i < size; i++) {
            AppointItem item = data.get(i);
            float d = angle * (i) + 270;
            float x = (float) (radius * Math.cos(Math.toRadians(d + angle / 2)));
            float y = (float) (radius * Math.sin(Math.toRadians(d + angle / 2)));

            System.err.println("d:" + d + ",i:" + i + ",time" + item.time + ",x:" + x + ",y:" + y);
            int i1 = Integer.parseInt(item.time.split(":")[0]);
            if (Integer.parseInt(item.time.split(":")[1]) != 0) {
                continue;
            }
//            if (i1 >= 9 && i1 < 12) {
//                x = (float) (radius * Math.sin(d));
//                y = (float) (radius * Math.cos(d));
//            }

            if (i1 >= 9 && i1 <= 15) {
                x = Math.abs(x);
            } else {
                x = -Math.abs(x);
            }
            if (i1 >= 12 && i1 <= 18) {
                y = Math.abs(y);
            } else {
                y = -Math.abs(y);
            }
//            if (y < 0) {
//                y += 9;
//            } else
            if (y > 0) {
                y += 21;
            }
            if (x < 0) {
                x -= 81;
//                y -= 12;
            }

// else if (x > 0) {
//               x += 15;
//            }
            canvas.drawText(item.time, x, y, paint);
        }
        canvas.restore();
        paint.setColor(getResources().getColor(R.color.white));
        canvas.drawCircle(cx, cy, (int) (radius * 0.45), paint);
    }
}

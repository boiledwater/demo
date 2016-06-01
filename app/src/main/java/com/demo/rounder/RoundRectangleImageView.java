package com.demo.rounder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;


public class RoundRectangleImageView extends ImageView {
    public float radius = 15f;
    public float scale = 1f;

    public RoundRectangleImageView(Context context) {
        super(context);
    }

    public RoundRectangleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    protected void init(Context context, AttributeSet attrs) {
//        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundRectangleImageView);
//        try {
//            radius = a.getFloat(R.styleable.RoundRectangleImageView_radius, DisplayUtil.dip2px(context, 5f));
//            scale = a.getFloat(R.styleable.ScaleImage_scale, 1);
//        } finally {
//            a.recycle();
//        }
    }

    public RoundRectangleImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (scale != 1) {
            heightMeasureSpec = (int) (getMeasuredWidth() * scale);
            super.onMeasure(widthMeasureSpec, MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY));
        }
    }

    int borderWidth = 3;

    @Override
    protected void onDraw(Canvas canvas) {
        Path clipPath = new Path();
        RectF rect = new RectF(0, 0, this.getWidth(), this.getHeight());

        Paint paint = new Paint();
//        paint.setColor(Color.parseColor("#d9d9d9"));
        paint.setColor(getResources().getColor(android.R.color.holo_red_dark));
//        paint.setStrokeWidth(3);
        canvas.drawRoundRect(rect, radius, radius, paint);
        rect = new RectF(borderWidth, borderWidth, this.getWidth() - borderWidth, this.getHeight() - borderWidth);
        clipPath.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(clipPath);
        super.onDraw(canvas);

        // drawRoundRect(@NonNull RectF rect, float rx, float ry, @NonNull Paint paint)
        //(float left, float top, float right, float bottom, float rx, float ry,
//        @NonNull Paint paint)
    }
}
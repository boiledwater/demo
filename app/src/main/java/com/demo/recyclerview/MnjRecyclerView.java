package com.demo.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;

/**
 * Created by user on 2016/3/21.
 */
public class MnjRecyclerView extends RecyclerView {
    public MnjRecyclerView(Context context) {
        super(context);
    }

    public MnjRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MnjRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return super.generateDefaultLayoutParams();
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return super.generateLayoutParams(attrs);
    }

    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p) {
        return super.checkLayoutParams(p);
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);
//        System.err.println("mode:" + MeasureSpec.getMode(heightSpec));
//        System.err.println("size:" + MeasureSpec.getSize(heightSpec));
//        System.err.println("height:" + getMeasuredHeight());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        boolean b = super.onInterceptTouchEvent(e);
//        System.err.println("b:" + b);
        return b;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
//        System.err.println("t:" + t + ",oldt:" + oldt);
    }
    public void onScrolled(int dx, int dy) {
        // Do nothing
//        System.err.println("dx:" + dx + ",dy:" + dy);
    }
}

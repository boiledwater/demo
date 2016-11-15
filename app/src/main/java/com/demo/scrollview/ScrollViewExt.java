package com.demo.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Created by HuLiZhong on 2016/6/8.
 */
public class ScrollViewExt extends ScrollView {
    public ScrollViewExt(Context context) {
        super(context);
    }

    public ScrollViewExt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewExt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.err.println("---------------------------------------");
        System.err.println(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY);
        System.err.println(MeasureSpec.getSize(heightMeasureSpec));
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
        System.err.println("scrollTo:" + x + "," + y);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        System.err.println("onLayout:" + t + "," + b);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public boolean startNestedScroll(int axes) {
        System.err.println("startNestedScroll:" + axes);
        return super.startNestedScroll(axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        System.err.println("onStartNestedScroll1:" + child);
        System.err.println("onStartNestedScroll2:" + target);
        System.err.println("onStartNestedScroll3:" + nestedScrollAxes);
        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        System.err.println("onInterceptTouchEvent:" + ev);
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        System.err.println("onTouchEvent:" + ev);
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean dispatchNestedPreScroll(int dx, int dy, int[] consumed, int[] offsetInWindow) {
        System.err.println("dispatchNestedPreScroll:" + dx + "," + dy);
        return super.dispatchNestedPreScroll(dx, dy, consumed, offsetInWindow);
    }

    @Override
    protected void onOverScrolled(int scrollX, int scrollY, boolean clampedX, boolean clampedY) {
        System.err.println("onOverScrolled:" + scrollY + "," + clampedY);
        super.onOverScrolled(scrollX, scrollY, clampedX, clampedY);
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX, int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        System.err.println("overScrollBy:" + scrollY + "," + scrollY + "," + scrollRangeY + "," + maxOverScrollY);
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }
}

package com.demo.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import java.util.ArrayList;

/**
 * Created by HuLiZhong on 2016/12/8.
 */
public class MyScrollView extends ViewGroup {

    private int mMaxHeight;
    private int mMaxWidth;
    private float mLastX;
    private float mLastY;

    public MyScrollView(Context context) {
        super(context);
        mScroller = new Scroller(getContext());
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScroller = new Scroller(getContext());
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mScroller = new Scroller(getContext());
    }

    private ArrayList<Integer> mHeights = new ArrayList();

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int right = view.getRight();
            view.layout(l, mMaxHeight, right, mMaxHeight + view.getMeasuredHeight());
            mMaxHeight += view.getMeasuredHeight();
            mHeights.add(mMaxHeight);
            if (right > mMaxWidth) {
                mMaxWidth = right;
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            measureChild(view, widthMeasureSpec, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Scroller mScroller;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        int dx = 0;
        int dy = 0;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                dx = (int) (mLastX - x);
                dy = (int) (mLastY - y);
                if (dx < 0) {
                    if (dx + getScrollX() < 0) {
                        dx = -getScrollX();
                    }
                } else if (dx > 0) {
                    int maxW = (int) (mMaxWidth - getWidth());
                    if (dx > maxW - getScrollX()) {
                        dx = maxW - getScrollX();
                    }
                }
                if (dy < 0) {
                    if (getScaleY() + dy < 0) {
                        dy = -getScrollY();
                    }
                } else if (dy > 0) {
                    int maxH = mMaxHeight - getHeight();
                    if (maxH < dy + getScrollY()) {
                        dy = maxH - getScrollY();
                    }
                }
                scrollBy(dx, dy);
                mLastX = x;
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                if (mHeights.isEmpty()) {
                    return true;
                }
                int curry = getScrollY();
                int i = 0;
                for (; i < mHeights.size(); i++) {
                    if (mHeights.get(i) > curry) {
                        break;
                    }
                }
                View child = getChildAt(i);
                int height = child.getHeight() / 3;
                if (getScrollY() > child.getBottom() - height) {
                    child = getChildAt(i + 1);
                    int viewHeight = child.getHeight();
                    if (i + 1 == mHeights.size() - 1 && viewHeight < getHeight()) {
                        dy = child.getBottom() - getHeight() - getScrollY();
                    } else {
                        dy = child.getTop() - getScrollY();
                    }
                } else if (getScrollY() < child.getTop() + height) {
                    dy = child.getTop() - getScrollY();
                }
                mScroller.startScroll(getScrollX(), getScrollY(), 0, dy);
                invalidate();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}

package com.demo.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

import com.demo.DisplayUtil;

import java.util.ArrayList;

/**
 * Created by HuLiZhong on 2016/12/8.
 */
public class MyScrollView extends ViewGroup {

    private int mMaxHeight = 0;
    private int mMaxWidth;
    private float mLastX;
    private float mLastY;
    private boolean down;

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
        mMaxHeight = 0;
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int right = view.getMeasuredWidth();
            System.err.println("i=" + i + "-------------------");
            System.err.println(l + "," + mMaxHeight + "," + right + "," + (mMaxHeight + view.getMeasuredHeight()));
            view.layout(l, mMaxHeight, right, mMaxHeight + view.getMeasuredHeight());
            mMaxHeight += view.getMeasuredHeight();
            mHeights.add(mMaxHeight);
//            if (right > mMaxWidth) {
//                mMaxWidth = right;
//            }
        }
        System.err.println(mHeights.size());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            measureChild(view, DisplayUtil.getScreenWidth(getContext()), DisplayUtil.getScreenHeight(getContext()));
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Scroller mScroller;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        int dy = 0;
        System.err.println(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                mLastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                if (!mScroller.isFinished()) {
                    mScroller.abortAnimation();
                }
                dy = (int) (mLastY - y);
                if (dy < 0) {
                    down = true;
                    //下滑
//                    if (getScrollY() + dy < 0) {
//                        dy = -getScrollY();
//                    }
                } else if (dy > 0) {
                    //上滑
                    down = false;
//                    int maxH = mMaxHeight - getHeight();
//                    if (maxH < dy + getScrollY()) {
//                        dy = maxH - getScrollY();
//                    }
                }
                System.err.println(dy);
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
//                if (mHeights.isEmpty()) {
//                    return true;
//                }
//                int curry = getScrollY();
//                int i = 0;
//                for (; i < mHeights.size(); i++) {
//                    if (mHeights.get(i) >= curry) {
//                        break;
//                    }
//                }
//                View child = getChildAt(i);
//                int deltY = child.getHeight() / 2;
//                if (getScrollY() > deltY) {
//                    child = getChildAt(i + 1);
//                    int viewHeight = child.getHeight();
//                    if (i + 1 == mHeights.size() - 1 && viewHeight < getHeight()) {
//                        dy = child.getBottom() - getHeight() - getScrollY();
//                    } else {
//                        dy = child.getTop() - getScrollY();
//                    }
//                } else if (getScrollY() < child.getTop() + deltY) {
//                    dy = child.getTop() - getScrollY();
//                }
//                mScroller.startScroll(0, getScrollY(), 0, dy);
//                invalidate();
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

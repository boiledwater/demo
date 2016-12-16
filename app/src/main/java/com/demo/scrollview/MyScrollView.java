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
            view.layout(l, mMaxHeight, right, mMaxHeight + view.getMeasuredHeight());
            mMaxHeight += view.getMeasuredHeight();
            mHeights.add(mMaxHeight);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            int widthMeasureSpec1 = MeasureSpec.makeMeasureSpec(DisplayUtil.getScreenWidth(getContext()), MeasureSpec.EXACTLY);
            view.measure(widthMeasureSpec1, heightMeasureSpec);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private Scroller mScroller;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float y = event.getY();
        int dy = 0;
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
                    if (getScrollY() + dy < 0) {
                        //控制下拉的时候，第一个子VIEW顶部紧贴父VIEW
                        dy = getScrollY();
                    }
                } else if (dy > 0) {
                    //上滑
                    down = false;
                    int maxH = mMaxHeight - DisplayUtil.getScreenHeight(getContext());
                    if (maxH < getScrollY()) {
                        //控制上滑的时候，最后一个VIEW的底部紧贴父VIEW
                        dy = 0;
                    }
                }
                System.err.println(dy);
                scrollBy(0, dy);
                mLastY = y;
                break;
            case MotionEvent.ACTION_UP:
                int scrollY = Math.abs(getScrollY());
                View childAt = null;
                int height = 0;
                for (int i = 0, size = getChildCount(); i < size; i++) {
                    childAt = getChildAt(i);
                    height = childAt.getHeight();
                    if (scrollY >= height) {
                        scrollY -= height;
                    } else {
                        break;
                    }
                }
                if (childAt != null) {
                    if (scrollY >= height / 2) {
                        dy = (height - scrollY);
                    } else {
                        dy = -scrollY;
                    }
                    mScroller.startScroll(0, getScrollY(), 0, dy);
                    invalidate();
                }
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

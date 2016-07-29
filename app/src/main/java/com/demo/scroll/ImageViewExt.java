package com.demo.scroll;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by hulizhong on 2016/3/26.
 */
public class ImageViewExt extends ImageView {

    private Scroller scrolller;
    private GestureDetector mDetector;
    private ObjectAnimator animator;

    public ImageViewExt(Context context) {
        super(context);
    }

    public ImageViewExt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewExt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    protected void onFinishInflate() {
//        super.onFinishInflate();
//        scrolller = new Scroller(getContext());
//        mDetector = new GestureDetector(mListener);
////        setOnClickListener(new OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                System.err.println("---------------");
////                scrolller.startScroll(200, 200, 200, 200, 5000);
////            }
////        });
//    }
//
//    private GestureDetector.OnGestureListener mListener = new GestureDetector.SimpleOnGestureListener() {
//        @Override
//        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
//            scrollBy(0, (int) distanceY);
//            return true;
//        }
//    };

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        mDetector.onTouchEvent(event);
//        return true;
//    }

    float dX, dY;

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                dX = getX() - event.getRawX();
//                dY = getY() - event.getRawY();
//                break;
//            case MotionEvent.ACTION_MOVE:
//                animate()
////                        .x(event.getRawX() + dX)
//                        .y(event.getRawY() + dY)
//                        .setDuration(0)
//                        .start();
//                break;
//            case MotionEvent.ACTION_UP:
//                animator = ObjectAnimator
//                        .ofFloat(this, "y", getY(), 0)
//                        .setDuration(500);
//                animator.setInterpolator(new DecelerateInterpolator());
//                animator.start();
//                break;
//            default:
//        }
//        return super.onTouchEvent(event);
//    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        System.err.println(event);
//        if (event.getAction() == MotionEvent.ACTION_UP) {
//            smoothScrollTo(0, 0);
//        } else {
//            smoothScrollTo(0, (int) event.getY());
//        }
//        return true;
////        return super.onTouchEvent(event);
//    }

//    @Override
//    public void computeScroll() {
//        super.computeScroll();
//        if (scrolller.computeScrollOffset()) {
//            ((View) getParent()).scrollTo(scrolller.getCurrX(), scrolller.getCurrY());
//            invalidate();
//        }
//    }

    //    public void computeScroll() {
//        float sx = getScrollX();
//        System.err.println("----computeScroll----" + sx);
//        if (sx > 495) {
//            return;
//        }
//        postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                scrollBy(1, 1);
//            }
//        }, 100);
//    }
//     scroll x
//    public void smoothScrollTo(int destx, int desty) {
//        int scrollx = getScrollX();
//        int delta = destx - scrollx;
//        //startScroll(int startX, int startY, int dx, int dy, int duration)
//        System.err.println("destx:" + destx + ",desty:" + desty + ",scrollx:" + scrollx + ",delta:" + delta);
//        scrolller.startScroll(scrollx, 0, delta, 0, 1000);
//        invalidate();
//    }
    // scroll y
//    public void smoothScrollTo(int destx, int desty) {
//        int scrollY = getScrollY();
//        int delta = desty - scrollY;
//        //startScroll(int startX, int startY, int dx, int dy, int duration)
//        System.err.println("destx:" + destx + ",desty:" + desty + ",scrollY:" + scrollY + ",delta:" + delta);
//        scrolller.startScroll(0, scrollY, 0, delta, 1000);
//        invalidate();
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        System.err.println(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dX = getX() - event.getRawX();
                dY = getY() - event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                animate()
//                        .x(event.getRawX() + dX)
                        .y(event.getRawY() + dY)
                        .setDuration(0)
                        .start();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    float translationY = getTranslationY();

                }
                ObjectAnimator animator = ObjectAnimator
                        .ofFloat(this, "y", getY(), 0)
                        .setDuration(500);
                animator.setInterpolator(new DecelerateInterpolator());
                animator.start();
                break;
            default:
        }
        return super.onTouchEvent(event);
    }
}

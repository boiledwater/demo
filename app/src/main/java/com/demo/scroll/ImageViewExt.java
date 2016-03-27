package com.demo.scroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.Scroller;

/**
 * Created by hulizhong on 2016/3/26.
 */
public class ImageViewExt extends ImageView {

    private Scroller scrolller;

    public ImageViewExt(Context context) {
        super(context);
    }

    public ImageViewExt(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageViewExt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        scrolller = new Scroller(getContext());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                System.err.println("---------------");
                scrolller.startScroll(200, 200, 200, 200, 5000);
            }
        });
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if (scrolller.computeScrollOffset()) {
            ((View) getParent()).scrollTo(scrolller.getCurrX(), scrolller.getCurrY());
            invalidate();
        }
    }
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
}

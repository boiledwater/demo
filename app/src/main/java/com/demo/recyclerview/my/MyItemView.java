package com.demo.recyclerview.my;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by hulizhong on 2016/5/24.
 */
public class MyItemView extends LinearLayout {
    public MyItemView(Context context) {
        super(context);
    }

    public MyItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void setLayoutParams(ViewGroup.LayoutParams params) {
        super.setLayoutParams(params);
    }
}

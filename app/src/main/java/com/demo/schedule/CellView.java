package com.demo.schedule;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by hulizhong on 2016/3/24.
 */
public class CellView extends TextView {
    private Item item;

    public CellView(Context context) {
        super(context);
    }

    public CellView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CellView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        heightMeasureSpec = (int) (getMeasuredWidth() / 1.81);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightMeasureSpec, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void set(Item item) {
        this.item = item;
        setText(item.name);
        if (item.status == Item.status_appointed) {
            setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
            setTextColor(getResources().getColor(android.R.color.white));
        } else if (item.status == Item.status_can_not_appoint) {
            setBackgroundColor(Color.parseColor("#666666"));
            setTextColor(Color.parseColor("#333333"));
        } else {
            setBackgroundColor(getResources().getColor(android.R.color.white));
            setTextColor(Color.parseColor("#333333"));
        }
    }
}

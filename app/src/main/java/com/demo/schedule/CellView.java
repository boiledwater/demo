package com.demo.schedule;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.TextView;

import com.demo.R;

/**
 * Created by hulizhong on 2016/3/24.
 */
public class CellView extends TextView {
    public Item item;
    private int sp16;

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
        int status = item.status;
        if (status == Item.status_blank) {
            setBackgroundColor(Color.parseColor("#eeeeee"));
        } else if (status == Item.status_appointed) {
            setBackgroundColor(Color.parseColor("#ff5e45"));
            setTextColor(getResources().getColor(android.R.color.white));
        } else if (status == Item.status_can_not_appoint) {
            setBackgroundColor(Color.parseColor("#eeeeee"));
            setTextColor(Color.parseColor("#222222"));
        } else if (status == Item.status_select_all) {
            setTextColor(Color.parseColor("#222222"));
            if (item.selected) {
                setText("取消全选");
                setBackgroundColor(Color.parseColor("#eeeeee"));
//                setTextColor(getResources().getColor(android.R.color.white));
            } else {
                setText("全选");
                setBackgroundColor(getResources().getColor(android.R.color.white));
            }
//            setTextSize(R.dimen.sp16);
        } else {
            if (item.selected) {
                setBackgroundColor(Color.parseColor("#eeeeee"));
//                setTextColor(getResources().getColor(android.R.color.white));
            } else {
                setBackgroundColor(getResources().getColor(android.R.color.white));
            }
            setTextColor(Color.parseColor("#222222"));
        }
    }
}

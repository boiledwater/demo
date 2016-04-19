package com.demo.tablayout;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import com.demo.DisplayUtil;
import com.demo.R;

/**
 * Created by HuLiZhong on 2016/4/15.
 */
public class MyTabLayout extends android.support.design.widget.TabLayout {
    private int d80;

//    private View view;

    public MyTabLayout(Context context) {
        super(context);
    }

    public MyTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        d80 = DisplayUtil.dip2px(getContext(), 80);
        setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.err.println(tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                System.err.println(tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                System.err.println(tab.getText());
            }
        });
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(d80 * 14, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        System.err.println("w:" + getMeasuredWidth() + ",h:" + getMeasuredHeight());
    }

    public void addTab(@NonNull Tab tab) {
        super.addTab(tab);
    }

    @Override
    public void scrollBy(int x, int y) {
        super.scrollBy(x, y);
    }

    public void scrollTo(int x, int y) {
        System.err.println("x:" + x);
        super.scrollTo(x, y);
    }

    @NonNull
    @Override
    public Tab newTab() {
        Tab tab = super.newTab();
//        tab.setCustomView(R.layout.pull_to_refresh_load_more);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.tab, this, false);
        tab.setCustomView(view);
        return tab;
    }
}

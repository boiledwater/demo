package com.demo.tablayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.demo.DisplayUtil;
import com.demo.R;

/**
 * Created by user on 2016/3/22.
 */
public class TabLayoutActivity extends Activity {

    private TabLayout tab_layout;
    private ViewPager view_pager;
    private int d8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        d8 = DisplayUtil.dip2px(getBaseContext(), 80);
        setContentView(R.layout.activity_appointments_today2);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            int pageSelected;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                TabLayout.Tab tab = tab_layout.getTabAt(position);
//                System.err.println("----------------");
//                System.err.println(position);
//                View customView = tab.getCustomView();
//                float left = customView.getLeft();
//                System.err.println(left);
//                int measuredWidth = customView.getMeasuredWidth();
//                int[] location = new int[2];
//                customView.getLocationOnScreen(location);
//                System.err.println("location:" + location[0]);
//                if (location[0] > 1080) {
//                    if (position < pageSelected) {
//                        measuredWidth = -measuredWidth * (position);
//                    }
////                    else {
////                        measuredWidth = measuredWidth * (position);
////                    }
//                    ((View) customView.getParent().getParent()).scrollTo(measuredWidth, 0);
//                } else if (location[0] <= 0) {
//                    if (position < pageSelected) {
//                        measuredWidth = measuredWidth * (position);
//                    }
//                    ((View) customView.getParent().getParent()).scrollTo(measuredWidth, 0);
//                }
//                pageSelected = position;

//                (tab_layout).scrollTo(d8 * position, 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        view_pager.setAdapter(new Adapter());
        tab_layout.setupWithViewPager(view_pager);
    }

}

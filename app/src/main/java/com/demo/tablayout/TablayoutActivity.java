package com.demo.tablayout;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.widget.TableLayout;

import com.demo.R;

/**
 * Created by user on 2016/3/22.
 */
public class TablayoutActivity extends Activity {

    private TabLayout tab_layout;
    private ViewPager view_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointments_today2);
        tab_layout = (TabLayout) findViewById(R.id.tab_layout);
        view_pager = (ViewPager) findViewById(R.id.view_pager);
        view_pager.setAdapter(new Adapter());
        tab_layout.setupWithViewPager(view_pager);
    }

}

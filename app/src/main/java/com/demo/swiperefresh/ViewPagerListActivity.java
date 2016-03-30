package com.demo.swiperefresh;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;


import com.demo.R;

import java.util.ArrayList;


public abstract class ViewPagerListActivity extends Activity {
    //    protected TabLayout tabLayout;
    protected ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    protected int[] pageArray;
    private int index;
    private RecyclerView[] views;
    private MnjBaseRecycleAdapter[] recyclerAdapters;
    private SwipeRefreshLayout swipe_fresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_list);
        initDatas();
        findViews();
    }


    protected void findViews() {
        swipe_fresh = (SwipeRefreshLayout) findViewById(R.id.swipe_fresh);
//        swipe_fresh.setEnabled(false);
        swipe_fresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ViewPagerListActivity.this.onRefresh();
            }
        });
        swipe_fresh.setRefreshing(true);
//        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        swipe_fresh.setEnabled(false);
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_CANCEL:
                        swipe_fresh.setEnabled(true);
                        break;
                }
                return false;
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                index = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        viewPager.setAdapter(pagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showLoading();
                onRefresh();
            }
        });
    }

    protected MnjBaseRecycleAdapter getRecycleAdapter() {
        return recyclerAdapters[index];
    }

    public void showLoading() {
        if (swipe_fresh != null) {
            if (!swipe_fresh.isRefreshing()) {
                swipe_fresh.setRefreshing(true);
            }
        }
    }

    public void hideLoading() {
        if (swipe_fresh != null) {
            if (swipe_fresh.isRefreshing()) {
                swipe_fresh.setRefreshing(false);
            }
        }
    }

    public void onRefresh() {
//        super.onRefresh();
        pageArray[index] = 0;
        onRefresh(index);
    }

    public abstract void onRefresh(int index);

    //    @Override
    public void onLoadMore() {
//        super.onLoadMore();
        pageArray[index] = pageArray[index] + 1;
        onLoadMore(index);
    }

    public abstract void onLoadMore(int index);

    //    @Override
    protected void initDatas() {
//        super.initDatas();
        pageArray = new int[getPageTitle().size()];
        views = new RecyclerView[getPageTitle().size()];
        if (recyclerAdapters == null) {
            recyclerAdapters = getRecyclerAdapters();
        }
        pagerAdapter = getPagerAdapter();
    }

//    @Override
//    protected void setListeners() {
//
//    }

    protected PagerAdapter getPagerAdapter() {
        if (recyclerAdapters == null) {
            recyclerAdapters = getRecyclerAdapters();
        }
        return new RecyclerPagerAdapter(getPageTitle(), views, recyclerAdapters);
    }

    protected abstract ArrayList<String> getPageTitle();

    protected abstract MnjBaseRecycleAdapter[] getRecyclerAdapters();

//    @Override
//    public void onClick(View v) {
//
//    }
//
//    @Override
//    public boolean isShowTopBar() {
//        return true;
//    }

}

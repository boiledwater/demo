package com.demo.swiperefresh;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.demo.R;

import java.util.ArrayList;

/**
 * Created by user on 2016/3/22.
 */
public class RecyclerPagerAdapter extends PagerAdapter {
    private RecyclerView[] views;
    private MnjBaseRecycleAdapter[] adapters;

    public RecyclerPagerAdapter(ArrayList<String> pageTitle, RecyclerView[] views, MnjBaseRecycleAdapter[] adapters) {
        this.pageTitle = pageTitle;
        this.views = views;
        this.adapters = adapters;
    }

    private ArrayList<String> pageTitle = new ArrayList<>();

    @Override
    public int getCount() {
        return pageTitle.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    public Object instantiateItem(ViewGroup container, int position) {
        if (views[position] == null) {
            LayoutInflater mInflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            RecyclerView view = (RecyclerView) mInflater.inflate(R.layout.adapter_recycle_item, container, false);
            LinearLayoutManager layoutManager = new LinearLayoutManager(container.getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            view.setLayoutManager(layoutManager);
//            SpacesItemDecoration decoration = new SpacesItemDecoration(1);
//            view.addItemDecoration(decoration);
            container.addView(view);
            views[position] = view;
            views[position].setAdapter(adapters[position]);
        }
        return views[position];
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitle.get(position);
    }
}

package com.demo.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/3/21.
 */
public class MnjRecycleAdapter extends RecyclerView.Adapter {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_HEADER = 1;
    public static final int TYPE_FOOTER = 2;

    private final ArrayList mList = new ArrayList();

    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM) {
            return onCreateItemViewHolder(parent, viewType);
        }
        if (viewType == TYPE_HEADER) {
            return onCreateHeaderViewHolder(parent, viewType);
        }
        if (viewType == TYPE_FOOTER) {
            return onCreateFooteViewHolder(parent, viewType);
        }
        return onCreateItemViewHolder(parent, viewType);
    }

    public VHHeader onCreateHeaderViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public VHItem onCreateItemViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public VHFooter onCreateFooteViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == TYPE_ITEM) {
            onBindItemViewHolder(holder, position);
        } else if (viewType == TYPE_HEADER) {
            onBindHeaderViewHolder(holder, position);
        } else if (viewType == TYPE_FOOTER) {
            onBindFooterViewHolder(holder, position);
        }
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public Object getItem(int position) {
        try {
            return mList.get(position);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void add(Object e) {
        if (null == mList || null == e) {
            return;
        }

        mList.add(e);
        notifyDataSetChanged();
    }

    public void addAll(List list) {
        if (null == mList || null == list || list.size() == 0) {
            return;
        }

        mList.addAll(list);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        if (null == mList || position < 0 || position >= mList.size()) {
            return;
        }

        mList.remove(position);
        notifyDataSetChanged();
    }

    public void remove(Object e) {
        if (null == mList || null == e) {
            return;
        }

        mList.remove(e);
        notifyDataSetChanged();
    }

    public void removeAll() {
        if (null == mList) {
            return;
        }

        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = mList.get(position);
        if (obj instanceof Header) {
            return TYPE_HEADER;
        }
        if (obj instanceof Footer) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }
}

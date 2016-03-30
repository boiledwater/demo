package com.demo.swiperefresh;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.demo.R;
import com.demo.recyclerview.Footer;
import com.demo.recyclerview.Header;
import com.demo.recyclerview.VHFooter;
import com.demo.recyclerview.VHHeader;
import com.demo.recyclerview.VHItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/3/21.
 */
public abstract class MnjBaseRecycleAdapter extends RecyclerView.Adapter {
    public static final int TYPE_ITEM = 0;
    public static final int TYPE_HEADER = 1;
    public static final int TYPE_FOOTER = 2;

    private final ArrayList mList = new ArrayList();

    public MnjBaseRecycleAdapter(ArrayList data) {
        if (data != null) {
            mList.addAll(data);
        }
    }

    public MnjBaseRecycleAdapter() {

    }

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

    public abstract VHItem onCreateItemViewHolder(ViewGroup parent, int viewType);

    public VHFooter onCreateFooteViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pull_to_refresh_load_more, parent, false);
        return new VHFooter(view);
    }

    @Override
    public final void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        holder.itemView.setTag(R.id.data, getItem(position));
        int viewType = getItemViewType(position);
        if (viewType == TYPE_HEADER) {
            onBindHeaderViewHolder(holder, position);
        } else if (viewType == TYPE_FOOTER) {
            onBindFooterViewHolder(holder, position);
        } else {
            onBindItemViewHolder(holder, position);
        }
    }

    public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    public abstract void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position);

    public void onBindFooterViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public synchronized int getItemCount() {
        return mList.size();
    }

    public synchronized Object getItem(int position) {
        try {
            return mList.get(position);
        } catch (Exception e) {
        }
        return null;
    }

    public synchronized void add(Object e) {
        if (null == mList || null == e) {
            return;
        }

        mList.add(e);
        notifyDataSetChanged();
    }

    public synchronized void add(int index, Object e) {
        if (null == mList || null == e) {
            return;
        }

        mList.add(index, e);
        notifyDataSetChanged();
    }

    public synchronized void addAll(List list) {
        if (null == mList || null == list || list.size() == 0) {
            return;
        }

        mList.addAll(list);
        notifyDataSetChanged();
    }

    public synchronized void remove(int position) {
        if (null == mList || position < 0 || position >= mList.size()) {
            return;
        }

        mList.remove(position);
        notifyDataSetChanged();
    }

    public synchronized ArrayList getAll() {
        return mList;
    }

    public synchronized void remove(Object e) {
        if (null == mList || null == e) {
            return;
        }

        mList.remove(e);
        notifyDataSetChanged();
    }

    public synchronized void removeAll() {
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

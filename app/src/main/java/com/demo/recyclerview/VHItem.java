package com.demo.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 2016/3/21.
 */
public class VHItem extends RecyclerView.ViewHolder {
    public static int type = MnjRecycleAdapter.TYPE_ITEM;

    public VHItem(View itemView) {
        super(itemView);
    }
}

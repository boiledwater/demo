package com.demo.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by user on 2016/3/21.
 */
public class VHHeader extends RecyclerView.ViewHolder {
    public static int type = MnjRecycleAdapter.TYPE_HEADER;

    public VHHeader(View itemView) {
        super(itemView);
    }
}

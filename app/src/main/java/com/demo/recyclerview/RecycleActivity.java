package com.demo.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.demo.R;

/**
 * Created by user on 2016/3/21.
 */
public class RecycleActivity extends Activity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MnjRecycleAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                             @Override
                                             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                                 super.onScrollStateChanged(recyclerView, newState);
                                                 int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                                                 if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                                     System.err.println("--SCROLL_STATE_IDLE--");
                                                     if (lastVisibleItem == adapter.getItemCount() - 1) {
                                                         adapter.add(adapter.getItemCount());
                                                         recyclerView.post(new Runnable() {
                                                             @Override
                                                             public void run() {
                                                                 adapter.notifyDataSetChanged();
                                                             }
                                                         });
                                                     }
                                                 } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                                                     System.err.println("--SCROLL_STATE_DRAGGING--");
                                                 } else {
                                                     System.err.println("--SCROLL_STATE_SETTLING--");
                                                 }
                                             }

                                             @Override
                                             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                                 super.onScrolled(recyclerView, dx, dy);
                                                 System.err.println("--dx:" + dx + ",dy:" + dy);
                                             }
                                         }

        );
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MnjRecycleAdapter();
        recyclerView.setAdapter(adapter);
    }

}

package com.demo.recyclerview;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.demo.R;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

/**
 * Created by user on 2016/3/21.
 */
public class RecycleActivity extends Activity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private MnjRecycleAdapter adapter;
    private ArrayList data = new ArrayList();
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycleview);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyItemRemoved(0);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        recyclerView.addItemDecoration(new SpacesItemDecoration(12));
        //        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL_LIST));
        recyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this).build());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                                             @Override
                                             public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                                                 super.onScrollStateChanged(recyclerView, newState);
                                                 int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                                                 if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                                                     System.err.println("--SCROLL_STATE_IDLE--");
                                                     System.err.println("canScrollVertically:" + recyclerView.canScrollVertically(-1));
//                                                     if (lastVisibleItem == adapter.getItemCount() - 1) {
////                                                         adapter.add(adapter.getItemCount());
//                                                         recyclerView.post(new Runnable() {
//                                                             @Override
//                                                             public void run() {
//                                                                 adapter.notifyDataSetChanged();
//                                                             }
//                                                         });
//                                                     }
                                                 } else if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                                                     System.err.println("--SCROLL_STATE_DRAGGING--");
                                                 } else {
                                                     System.err.println("--SCROLL_STATE_SETTLING--");
                                                 }
                                             }

                                             @Override
                                             public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                                                 super.onScrolled(recyclerView, dx, dy);
//                                                 System.err.println("--dx:" + dx + ",dy:" + dy);
//                                                 System.err.println("canScrollVertically:" + recyclerView.canScrollVertically(-1));
                                             }
                                         }

        );
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MnjRecycleAdapter() {
            @Override
            public VHItem onCreateItemViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_view, null);
                System.err.println(view.getLayoutParams());
                return new VHItem(view);
            }

            public void onBindItemViewHolder(RecyclerView.ViewHolder holder, int position) {
                TextView textView = (TextView) holder.itemView.findViewById(R.id.text_tv);
                textView.setText(position + "");
            }
        };
        init();
        adapter.addAll(data);
        recyclerView.setAdapter(adapter);
    }

    private void init() {
        for (int i = 0; i < 40; i++) {
            data.add("" + i);
        }
    }
}

package com.demo.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.demo.R;

/**
 * Created by hulizhong on 2016/3/24.
 */
public class ScheduleManageActivity extends Activity {

    private ScheduleManageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_manage);
        GridView gridView = (GridView) findViewById(R.id.schedule_gv);
        adapter = new ScheduleManageAdapter();
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CellView cellView = (CellView) view;
                Item item = cellView.item;
                int status = item.status;
                if (status == Item.status_appointed || status == Item.status_can_not_appoint || status == Item.status_blank) {
                    return;
                }
                if (status == Item.status_available) {
                    item.selected = !item.selected;
                } else if (status == Item.status_select_all) {
                    item.selected = !item.selected;
                    select(item.selected);
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void select(boolean b) {
        for (int i = 0, size = adapter.getCount(); i < size; i++) {
            Item item = (Item) adapter.getItem(i);
            item.selected = b;
        }
    }
}

package com.demo;

import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by apple on 16/2/12.
 */
public class MyListActivity extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setListAdapter(new Adapter(this, getList(),
                R.layout.adapter_item, new String[]{"key", "value"}, new int[]{R.id.keyid, R.id.valueid}));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        System.err.println("position:" + position);
    }

    private List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("key", "key" + i);
            map.put("value", "value" + i);
            list.add(map);
        }
        return list;
    }

    class Adapter extends SimpleAdapter {
        public Adapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
//            return null;
        }

        @Override
        public boolean isEnabled(int position) {
            return position % 2 == 0;
        }
    }

}

package com.demo.schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.R;

import java.util.ArrayList;

/**
 * Created by hulizhong on 2016/3/24.
 */
public class ScheduleAdapter extends BaseAdapter {
    private ArrayList<Item> data = new ArrayList<>();

    public ScheduleAdapter() {
        Item object = new Item("09:00-09:30");
        object.status = Item.status_can_not_appoint;
        data.add(object);
        data.add(new Item("09:30-10:00"));
        data.add(new Item("10:00-10:30"));
        data.add(new Item("10:30-11:00"));
        data.add(new Item("11:00-11:30"));
        data.add(new Item("11:30-12:00"));
        data.add(new Item("12:00-12:30"));
        Item object1 = new Item("12:30-13:00");
        object1.status = Item.status_appointed;
        data.add(object1);
        data.add(new Item("13:00-13:30"));
        data.add(new Item("14:00-14:30"));
        data.add(new Item("14:30-15:00"));
        data.add(new Item("15:00-15:30"));
        data.add(new Item("15:30-16:00"));
        data.add(new Item("16:00-16:30"));
        data.add(new Item("16:30-17:00"));
        data.add(new Item("17:00-17:30"));
        data.add(new Item("17:30-18:00"));
        data.add(new Item("18:00-18:30"));
        data.add(new Item("19:00-19:30"));
        data.add(new Item("19:30-20:00"));
        data.add(new Item("20:00-20:30"));
        data.add(new Item("20:30-21:00"));
        data.add(new Item("21:00-21:30"));
        data.add(new Item("21:30-22:00"));
        data.add(new Item("22:00-22:30"));
        data.add(new Item("22:30-23:00"));
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.adapter_schedule_item, parent, false);
        }
        Item item = (Item) getItem(position);
        ((CellView) convertView).set(item);
        return convertView;
    }

}

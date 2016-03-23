package com.demo.schedule;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;

import com.demo.R;

/**
 * Created by hulizhong on 2016/3/24.
 */
public class BaseScheduleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        GridView gridView = (GridView) findViewById(R.id.schedule_gv);
        gridView.setAdapter(new ScheduleAdapter());
    }
}

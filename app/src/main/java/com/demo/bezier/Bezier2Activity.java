package com.demo.bezier;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.demo.R;

/**
 * Created by HuLiZhong on 2016/11/15.
 */
public class Bezier2Activity extends Activity {

    private Bezier2View bezier2View;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bezier2);
        bezier2View = (Bezier2View) findViewById(R.id.bv);
        findViewById(R.id.cb_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bezier2View.setMode(true);
            }
        });
        findViewById(R.id.cb_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bezier2View.setMode(false);
            }
        });
    }
}

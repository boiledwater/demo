package com.demo.anim.shared_element;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.demo.R;

/**
 * Created by HuLiZhong on 2016/9/8.
 */
public class SharedElement2Activity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_2);
    }

    public void onClick(View view) {
        finish();
    }
}

package com.demo.anim.shared_element;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.demo.R;

/**
 * Created by HuLiZhong on 2016/9/8.
 */
public class SharedElement1Activity extends Activity {

    private View firstSharedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_element_1);
        firstSharedView = findViewById(R.id.firstSharedView);
        firstSharedView.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SharedElement1Activity.this, SharedElement2Activity.class);
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(SharedElement1Activity.this, firstSharedView, "sharedView").toBundle());
            }
        });
    }
}

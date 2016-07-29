package com.demo.pull;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.demo.R;
import com.demo.scroll.ImageViewExt;

/**
 * Created by HuLiZhong on 2016/7/14.
 */
public class PullActivity extends Activity {

    private ImageViewExt pull;
    private View fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull);
        fl = findViewById(R.id.fl);
        pull = (ImageViewExt) findViewById(R.id.image);

//        pull.setOnTouchListener(new View.OnTouchListener() {
//            float lasty;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                System.err.println(event);
//                if (event.getAction() == MotionEvent.ACTION_UP) {
//                    pull.smoothScrollTo(0, 0);
//                } else {
//                    fl.scrollBy(0, -(int) Math.abs(event.getY() - lasty));
//                }
//                lasty = event.getY();
//                return true;
//            }
//        });
    }
}

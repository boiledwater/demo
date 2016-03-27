package com.demo.scroll;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.demo.R;

/**
 * Created by hulizhong on 2016/3/26.
 */
public class ScrollActivity extends Activity {

    private int d120;
    private int d60;
    private View view;
    private FrameLayout fl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);
        fl = (FrameLayout) findViewById(R.id.fl);
        view = findViewById(R.id.image);
        d120 = (int) getResources().getDimension(R.dimen.d120);
        d60 = (int) getResources().getDimension(R.dimen.d60);
//        fl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                System.err.println(v.getScrollX() + "," + v.getScrollY());
////                v.scrollTo(d120, d120);
////                view.postDelayed(new Runnable() {
////                    @Override
////                    public void run() {
////                        view.scrollBy(d60, d60);
////                        System.err.println(view.getScrollX() + "," + view.getScrollY());
////                    }
////                },1000);
////                System.err.println(v.getScrollX() + "," + v.getScrollY());
////                view.offsetLeftAndRight(d60);
//
//                System.err.println("---------------------");
//                System.err.println(v.getScrollX() + "," + v.getScrollY());
//                System.err.println(v.getLeft() + "," + v.getRight());
//            }
//        });
    }
}

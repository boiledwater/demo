package com.demo.scrollview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.demo.DisplayUtil;
import com.demo.R;

/**
 * Created by HuLiZhong on 2016/6/8.
 */
public class ScrollViewActivity extends Activity {

    private View flHeader;
    private int headY;
    private ScrollViewExt scrollViewRt;
    private View footerSl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrollview);
        flHeader = findViewById(R.id.fl_header);
        scrollViewRt = (ScrollViewExt) findViewById(R.id.scrollView_rt);
        footerSl = findViewById(R.id.footer_sl);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        int[] location = new int[2];
        flHeader.getLocationInWindow(location);
        headY = location[1];
        System.err.println("-------------------");
        System.err.println(location[0] + "," + headY);
        scrollViewRt.getLocationInWindow(location);
        System.err.println(location[1]);
        System.err.println(DisplayUtil.getScreenHeight(this) + "," + scrollViewRt.getHeight());
    }
}

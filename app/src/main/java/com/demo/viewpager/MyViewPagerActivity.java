package com.demo.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.demo.BaseActivity;
import com.demo.R;

/**
 * Created by HuLiZhong on 2016/5/20.
 */
public class MyViewPagerActivity extends BaseActivity {

    private MyViewPager viewPager;
    private TextView[] child = new TextView[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.err.println("----------onCreate");
        setContentView(R.layout.activity_view_pager);
        viewPager = (MyViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return object == view;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                int index = position % 3;
                if (child[index] == null) {
                    TextView textView = new TextView(getBaseContext());
                    textView.setGravity(Gravity.CENTER);
                    textView.setBackgroundColor(getResources().getColor(R.color.appoint));
                    ViewPager.LayoutParams lp = new ViewPager.LayoutParams();
                    textView.setLayoutParams(lp);
                    child[index] = textView;
                }
                child[index].setText("" + position);
                ViewGroup vp = (ViewGroup) child[index].getParent();
                if (vp != null) {
                    vp.removeView(child[index]);
                }
                container.addView(child[index]);
                return child[index];
            }

            public void destroyItem(ViewGroup container, int position, Object object) {
                System.err.println("destroyItem:" + position + "," + object);
//                ((ViewGroup) container).removeView((View) object);
            }
        });
    }
}

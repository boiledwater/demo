package com.demo.anim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import com.demo.DisplayUtil;
import com.demo.R;

/**
 * Created by user on 2016/3/14.
 */
public class AnimActivity extends Activity {

    private ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmi);
        imageview = (ImageView) findViewById(R.id.image);
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageview.setTranslationY(DisplayUtil.dip2px(getBaseContext(), 20));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        RotateAnimation anim = new RotateAnimation(0.0f, 180f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

//Setup anim with desired properties
        anim.setInterpolator(new LinearInterpolator());
//        anim.setRepeatCount(1); //Repeat animation indefinitely
        anim.setDuration(700); //Put desired duration per anim cycle here, in milliseconds
        anim.setFillAfter(true);
//        anim.setFillBefore(true);
        anim.setFillEnabled(true);
//Start animation
        imageview.startAnimation(anim);
    }
}

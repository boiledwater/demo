package com.demo.anim.plus;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

/**
 * Created by HuLiZhong on 2016/10/19.
 */
public class AnimationUtil {
    public static void curve(Activity context, final View targetView, View startView, View endView) {
        final ViewGroup rootView = (ViewGroup) context.getWindow().getDecorView();
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        targetView.setLayoutParams(lp);
        rootView.addView(targetView);

        int[] rootLocation = new int[2];
        rootView.getLocationOnScreen(rootLocation);

        int[] startLocation = new int[2];
        startView.getLocationInWindow(startLocation);

        int[] endLocation = new int[2];
        endView.getLocationInWindow(endLocation);


        TranslateAnimation translateAnimationX = new TranslateAnimation(startLocation[0], endLocation[0], 0, 0);
        translateAnimationX.setInterpolator(new LinearInterpolator());
        translateAnimationX.setFillAfter(true);

        TranslateAnimation translateAnimationY = new TranslateAnimation(0, 0, startLocation[1] - rootLocation[1], endLocation[1] - rootLocation[1]);
        translateAnimationY.setInterpolator(new AccelerateInterpolator());
//        translateAnimationY.setRepeatCount(Animation.INFINITE);
        translateAnimationY.setFillAfter(true);
        translateAnimationY.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rootView.removeView(targetView);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        AnimationSet set = new AnimationSet(false);
        set.setFillAfter(false);
        set.addAnimation(translateAnimationY);
        set.addAnimation(translateAnimationX);
        set.setDuration(1000);
        targetView.startAnimation(set);
    }

    public static void curve(Activity context, int targetViewId, View startView, View endView) {
        View targetView = View.inflate(context, targetViewId, null);
        curve(context, targetView, startView, endView);
    }
}

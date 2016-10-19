package com.demo.anim.plus;

import android.view.animation.LinearInterpolator;

/**
 * Created by HuLiZhong on 2016/10/19.
 */
public class BezierInterpolator extends LinearInterpolator {
    @Override
    public float getInterpolation(float input) {
        System.err.println(input);
        return super.getInterpolation(input);
    }
}

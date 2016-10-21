package com.demo.anim.plus;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.DisplayUtil;
import com.demo.R;

/**
 * Created by HuLiZhong on 2016/10/18.
 */
public class PlusView2 extends FrameLayout {

    private ImageView ivPlus;
    private TextView tvText;
    private ImageView ivDecrease;
    private boolean expand = false;
    private int maxRightMargin;
    public ValueChange valueChange;
    private View view;

    public PlusView2(Context context) {
        super(context);
    }

    public PlusView2(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PlusView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Animation shrinkAnim;

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view = View.inflate(getContext(), R.layout.plus_view2, this);
        maxRightMargin = DisplayUtil.dip2px(getContext(), 32);
        ivPlus = (ImageView) view.findViewById(R.id.iv_plus);
        ivPlus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expand) {
                    String s = tvText.getText().toString();
                    if (TextUtils.isEmpty(s)) {
                        tvText.setText("1");
                    } else {
                        tvText.setText((Integer.parseInt(s) + 1) + "");
                    }
                    tvText.setVisibility(VISIBLE);
                } else {
                    expand();
                }
                if (valueChange != null) {
                    valueChange.plus(1);
                }
            }
        });
        tvText = (TextView) view.findViewById(R.id.tv_text);
        ivDecrease = (ImageView) view.findViewById(R.id.iv_decrease);
        ivDecrease.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int value = Integer.parseInt(tvText.getText().toString());
                    if (value > 1) {
                        tvText.setText((value - 1) + "");
                        if (valueChange != null) {
                            valueChange.decrease(1);
                        }
                    } else {
                        shrink();
                    }
                } catch (Exception e) {
                    shrink();
                }
            }
        });
    }

    private void shrink() {
        expand = false;
        tvText.setVisibility(INVISIBLE);
        shrinkAnim = new TranslateAnimation(0, ivPlus.getLeft(), 0, 0);
        //ObjectAnimator.ofInt(new MarginLP((MarginLayoutParams) ivDecrease.getLayoutParams()), "rightMargin", maxRightMargin, 0);
        shrinkAnim.setDuration(1000);                  // Duration in milliseconds
        shrinkAnim.setInterpolator(new DecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
        shrinkAnim.setFillAfter(true);
        shrinkAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tvText.setVisibility(INVISIBLE);
                ivDecrease.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivDecrease.startAnimation(shrinkAnim);
    }

    private void expand() {
        expand = true;
        shrinkAnim = new TranslateAnimation(ivPlus.getLeft(), 0, 0, 0);
        shrinkAnim.setDuration(1000);                  // Duration in milliseconds
        shrinkAnim.setInterpolator(new DecelerateInterpolator());  // E.g. Linear, Accelerate, Decelerate
        shrinkAnim.setFillAfter(true);
        shrinkAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvText.setVisibility(VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        ivDecrease.startAnimation(shrinkAnim);
    }

    class MarginLP {
        private MarginLayoutParams lp;

        public MarginLP(MarginLayoutParams lp) {
            this.lp = lp;
        }

        private int rightMargin;

        public int getRightMargin() {
            return rightMargin;
        }

        public void setRightMargin(int rightMargin) {
            this.rightMargin = rightMargin;
            lp.rightMargin = rightMargin;
            ivDecrease.setLayoutParams(lp);
        }
    }

    interface ValueChange {
        void plus(int value);

        void decrease(int value);
    }
}

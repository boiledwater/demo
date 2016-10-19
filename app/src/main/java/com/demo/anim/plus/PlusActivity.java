package com.demo.anim.plus;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.demo.R;

/**
 * Created by HuLiZhong on 2016/10/18.
 */
public class PlusActivity extends Activity {

    private PlusView pvStart;
    private View ivEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus);
        pvStart = (PlusView) findViewById(R.id.pv_start);
        ivEnd = findViewById(R.id.iv_end);
        pvStart.valueChange = new PlusView.ValueChange() {
            @Override
            public void plus(int value) {
                AnimationUtil.curve(PlusActivity.this, R.layout.image_view, pvStart, ivEnd);
            }

            @Override
            public void decrease(int value) {
                AnimationUtil.curve(PlusActivity.this, R.layout.image_view, pvStart, ivEnd);
            }
        };
    }
}

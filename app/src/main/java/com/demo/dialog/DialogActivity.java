package com.demo.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.demo.R;

/**
 * Created by HuLiZhong on 2016/9/6.
 */
public class DialogActivity extends Activity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(DialogActivity.this, R.style.CustomDialog);
                dialog.setTitle("title");
                dialog.setContentView(R.layout.dialog);
                dialog.show();
            }
        });
    }

}

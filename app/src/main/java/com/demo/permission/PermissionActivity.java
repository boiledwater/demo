package com.demo.permission;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import com.demo.R;

/**
 * Created by HuLiZhong on 2016/12/1.
 */
public class PermissionActivity extends Activity {
    private TextView tv1;
    String[] permissions = new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checkPermission = PermissionUtil.checkPermission(PermissionActivity.this, permissions);
                if (checkPermission) {
                    tv1.setText("已授权");
                } else {
                    tv1.setText("未授权");
                }
                System.err.println("checkPermission:" + checkPermission);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(PermissionActivity.this, permissions, 101);
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean lackPermissions = PermissionUtil.lackPermissions(PermissionActivity.this, permissions);
                if (lackPermissions) {
                    tv1.setText("未授权");
                } else {
                    tv1.setText("已授权");
                }
                System.err.println("lackPermissions:" + lackPermissions);
            }
        });
        findViewById(R.id.btn4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean shouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this, permissions[0]);
                shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale && ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this, permissions[1]);
                if (shouldShowRequestPermissionRationale) {
                    tv1.setText("可提示授权");
                } else {
                    tv1.setText("不可提示授权");
                }
            }
        });
        tv1 = (TextView) findViewById(R.id.tv1);
    }
}

package com.demo.permission;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.demo.R;

/**
 * Created by HuLiZhong on 2016/12/1.
 */
public class PermissionActivity extends Activity {
    private TextView tv1;
    String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                AppOpsManager appOpsManager = (AppOpsManager) getSystemService(Context.APP_OPS_SERVICE);
                int checkOp = appOpsManager.checkOp(AppOpsManager.OPSTR_FINE_LOCATION, android.os.Process.myUid(), getPackageName());
                if (checkOp == AppOpsManager.MODE_IGNORED) {
                    // 权限被拒绝了 .
                    System.err.println("权限被拒绝了");
                }
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
                //请求授权
                ActivityCompat.requestPermissions(PermissionActivity.this, permissions, 225);
            }
        });
        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean lackPermissions = ContextCompat.checkSelfPermission(PermissionActivity.this, permissions[0]) == PackageManager.PERMISSION_DENIED;
                //PermissionUtil.lackPermissions(PermissionActivity.this, permissions);
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
//                shouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale && ActivityCompat.shouldShowRequestPermissionRationale(PermissionActivity.this, permissions[1]);
                if (shouldShowRequestPermissionRationale) {
                    tv1.setText("可提示授权");
                } else {
                    tv1.setText("不可提示授权");
                }
            }
        });
        tv1 = (TextView) findViewById(R.id.tv1);
    }

    @Override
    public void onRequestPermissionsResult(final int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        System.err.println("requestCode:" + requestCode);
        System.err.println("permissions:" + permissions.length + ";" + permissions[0]);
        System.err.println("grantResults:" + grantResults.length + ";" + grantResults[0]);
    }
}

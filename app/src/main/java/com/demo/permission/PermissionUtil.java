package com.demo.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by HuLiZhong on 2016/5/7.
 */
public class PermissionUtil {
    @TargetApi(Build.VERSION_CODES.M)
    public static void check(Activity activity, String permission, int request_code, ICall call) {
        check(activity, new String[]{permission}, request_code, call);
    }

    @TargetApi(Build.VERSION_CODES.M)
    public static void check(final Activity activity, final String[] permission, final int request_code, final ICall call) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    boolean lackPermission = lackPermissions(activity, permission);
                    boolean delayAllPermissions = delayAllPermissions(activity, permission);
                    System.err.println("lackPermission:" + lackPermission);
                    System.err.println("delayAllPermissions:" + delayAllPermissions);
                    if (lackPermission || delayAllPermissions) {
                        if (delayAllPermissions) {
                            //没有授权并没有拒绝授权的话，就申请权限
                            ActivityCompat.requestPermissions(activity, permission, request_code);
                            return;
                        }
                    }
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                if (call != null) {
                    call.call();
                }
            }
        });
    }


    @TargetApi(Build.VERSION_CODES.M)
    public static boolean checkPermission(Activity activity, String[] permissions) {
        return !lackPermissions(activity, permissions);
    }

    public static boolean checkPermission(Activity activity, String permissions) {
        try {
            return checkPermission(activity, new String[]{permissions});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean lackPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_DENIED) {
                return true;
            }
        }
        return false;
    }

    /***
     * 用户选择拒绝给APP授权
     */
    public static boolean delayAllPermissions(Activity activity, String... permissions) {
        int count = 0;
        for (String permission : permissions) {
            boolean shouldShowRequestPermissionRationale = ActivityCompat.shouldShowRequestPermissionRationale(activity, permission);
            boolean denied = ContextCompat.checkSelfPermission(activity, permission) == PackageManager.PERMISSION_DENIED;
            if (!shouldShowRequestPermissionRationale && denied) {
                count++;
            }
        }
        if (count == permissions.length) {
            return true;
        }
        return false;
    }

    public interface ICall {
        void call();
    }
}

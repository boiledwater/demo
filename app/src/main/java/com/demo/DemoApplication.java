package com.demo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by user on 2016/3/21.
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        CrashReport.initCrashReport(getApplicationContext(), "900022842", BuildConfig.DEBUG);
    }
}

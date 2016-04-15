package com.demo;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by user on 2016/3/21.
 */
public class DemoApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        CrashReport.initCrashReport(getApplicationContext(), "900022842", BuildConfig.DEBUG);
    }
}

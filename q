[1mdiff --git a/app/src/main/AndroidManifest.xml b/app/src/main/AndroidManifest.xml[m
[1mindex f5c5f5e..0b390b1 100644[m
[1m--- a/app/src/main/AndroidManifest.xml[m
[1m+++ b/app/src/main/AndroidManifest.xml[m
[36m@@ -145,10 +145,6 @@[m
             </intent-filter>[m
         </activity>[m
         <activity android:name=".transitionanimator.feed.FeedListActivity">[m
[31m-            <intent-filter>[m
[31m-                <action android:name="android.intent.action.MAIN" />[m
[31m-                <category android:name="android.intent.category.LAUNCHER" />[m
[31m-            </intent-filter>[m
         </activity>[m
         <activity android:name=".transitionanimator.feed.FeedDetailActivity"></activity>[m
     </application>[m
[1mdiff --git a/app/src/main/java/com/demo/gesturelock/GesturePreView.java b/app/src/main/java/com/demo/gesturelock/GesturePreView.java[m
[1mindex 0d8c469..58b6046 100644[m
[1m--- a/app/src/main/java/com/demo/gesturelock/GesturePreView.java[m
[1m+++ b/app/src/main/java/com/demo/gesturelock/GesturePreView.java[m
[36m@@ -1,7 +1,87 @@[m
 package com.demo.gesturelock;[m
 [m
[32m+[m[32mimport android.content.Context;[m
[32m+[m[32mimport android.graphics.Canvas;[m
[32m+[m[32mimport android.graphics.Paint;[m
[32m+[m[32mimport android.util.AttributeSet;[m
[32m+[m[32mimport android.view.View;[m
[32m+[m
[32m+[m[32mimport com.demo.DisplayUtil;[m
[32m+[m[32mimport com.demo.R;[m
[32m+[m
[32m+[m[32mimport java.util.HashMap;[m
[32m+[m
 /**[m
  * Created by HuLiZhong on 2017/2/14.[m
  */[m
[31m-public class GesturePreView {[m
[32m+[m[32mpublic class GesturePreView extends View {[m
[32m+[m[32m    private HashMap<Integer, Boolean> gesture = new HashMap<>();[m
[32m+[m[32m    private int mW;[m
[32m+[m[32m    private int mH;[m
[32m+[m[32m    private int radius;[m
[32m+[m
[32m+[m[32m    public GesturePreView(Context context) {[m
[32m+[m[32m        super(context);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public GesturePreView(Context context, AttributeSet attrs) {[m
[32m+[m[32m        super(context, attrs);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public GesturePreView(Context context, AttributeSet attrs, int defStyleAttr) {[m
[32m+[m[32m        super(context, attrs, defStyleAttr);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    protected void onFinishInflate() {[m
[32m+[m[32m        super.onFinishInflate();[m
[32m+[m[32m        gesture.put(2, true);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    protected void onSizeChanged(int w, int h, int oldw, int oldh) {[m
[32m+[m[32m        super.onSizeChanged(w, h, oldw, oldh);[m
[32m+[m[32m        mW = w;[m
[32m+[m[32m        mH = h;[m
[32m+[m[32m        radius = (mW - DisplayUtil.dip2px(getContext(), 19.4f)) / 6;[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {[m
[32m+[m[32m        if (MeasureSpec.getMode(widthMeasureSpec) != MeasureSpec.EXACTLY) {[m
[32m+[m[32m            widthMeasureSpec = MeasureSpec.makeMeasureSpec(DisplayUtil.dip2px(getContext(), 50), MeasureSpec.EXACTLY);[m
[32m+[m[32m        }[m
[32m+[m[32m        if (MeasureSpec.getMode(heightMeasureSpec) != MeasureSpec.EXACTLY) {[m
[32m+[m[32m            heightMeasureSpec = MeasureSpec.makeMeasureSpec(DisplayUtil.dip2px(getContext(), 50), MeasureSpec.EXACTLY);[m
[32m+[m[32m        }[m
[32m+[m[32m        super.onMeasure(widthMeasureSpec, heightMeasureSpec);[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    @Override[m
[32m+[m[32m    protected void onDraw(Canvas canvas) {[m
[32m+[m[32m        super.onDraw(canvas);[m
[32m+[m[32m        float cx;[m
[32m+[m[32m        float cy;[m
[32m+[m[32m        Paint paint = new Paint();[m
[32m+[m
[32m+[m[32m        for (int i = 0; i < 9; i++) {[m
[32m+[m[32m            int line = i % 3;[m
[32m+[m[32m            int row = i / 3;[m
[32m+[m[32m            cx = (line + 1) * this.radius + line * DisplayUtil.dip2px(getContext(), 9.7f);[m
[32m+[m[32m            cy = (row + 1) * this.radius + row * DisplayUtil.dip2px(getContext(), 9.7f);[m
[32m+[m
[32m+[m[32m            paint.setColor(getResources().getColor(R.color.cycle_border));[m
[32m+[m[32m            canvas.drawCircle(cx, cy, radius, paint);[m
[32m+[m[32m            Boolean heightLight = gesture.get(i);[m
[32m+[m[32m            if (heightLight == null || !heightLight) {[m
[32m+[m[32m                paint.setColor(getResources().getColor(R.color.cycle_bg));[m
[32m+[m[32m                canvas.drawCircle(cx, cy, radius - DisplayUtil.dip2px(getContext(), 1), paint);[m
[32m+[m[32m            }[m
[32m+[m[32m        }[m
[32m+[m[32m    }[m
[32m+[m
[32m+[m[32m    public void set(HashMap<Integer, Boolean> map) {[m
[32m+[m[32m        this.gesture = map;[m
[32m+[m[32m        invalidate();[m
[32m+[m[32m    }[m
 }[m
[1mdiff --git a/app/src/main/res/drawable-xxhdpi/line.xml b/app/src/main/res/drawable-xxhdpi/line.xml[m
[1mdeleted file mode 100644[m
[1mindex 3191ed1..0000000[m
[1m--- a/app/src/main/res/drawable-xxhdpi/line.xml[m
[1m+++ /dev/null[m
[36m@@ -1,5 +0,0 @@[m
[31m-<?xml version="1.0" encoding="utf-8"?>[m
[31m-<bitmap xmlns:android="http://schemas.android.com/apk/res/android"[m
[31m-    android:dither="true"[m
[31m-    android:src="@drawable/indicator_code_lock_point_area_green_holo"[m
[31m-    android:tileMode="disabled"></bitmap>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/layout/activity_lock.xml b/app/src/main/res/layout/activity_lock.xml[m
[1mindex 245b4a9..0167cf6 100644[m
[1m--- a/app/src/main/res/layout/activity_lock.xml[m
[1m+++ b/app/src/main/res/layout/activity_lock.xml[m
[36m@@ -1,8 +1,7 @@[m
 <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"[m
     xmlns:tools="http://schemas.android.com/tools"[m
     android:layout_width="match_parent"[m
[31m-    android:layout_height="match_parent"[m
[31m-    tools:context=".MainActivity" >[m
[32m+[m[32m    android:layout_height="match_parent">[m
 [m
     <TextView[m
         android:layout_width="match_parent"[m
[36m@@ -16,5 +15,4 @@[m
         android:id="@+id/lock_pattern"[m
         android:layout_width="match_parent"[m
         android:layout_height="match_parent" />[m
[31m-[m
 </RelativeLayout>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/layout/activity_my_transition_main.xml b/app/src/main/res/layout/activity_my_transition_main.xml[m
[1mindex 6ffdb4f..00e9853 100644[m
[1m--- a/app/src/main/res/layout/activity_my_transition_main.xml[m
[1m+++ b/app/src/main/res/layout/activity_my_transition_main.xml[m
[36m@@ -10,4 +10,9 @@[m
         android:id="@+id/list"[m
         android:layout_width="match_parent"[m
         android:layout_height="match_parent" />[m
[32m+[m
[32m+[m[32m    <com.demo.gesturelock.GesturePreView[m
[32m+[m[32m        android:layout_width="50dp"[m
[32m+[m[32m        android:layout_height="50dp"[m
[32m+[m[32m        android:layout_centerInParent="true" />[m
 </RelativeLayout>[m
\ No newline at end of file[m
[1mdiff --git a/app/src/main/res/layout/line.xml b/app/src/main/res/layout/line.xml[m
[1mindex 7fc91f8..bcbc38d 100644[m
[1m--- a/app/src/main/res/layout/line.xml[m
[1m+++ b/app/src/main/res/layout/line.xml[m
[36m@@ -6,8 +6,9 @@[m
 [m
     <TextView[m
         android:id="@+id/button"[m
[31m-        android:layout_width="wrap_content"[m
[31m-        android:layout_height="wrap_content"[m
[32m+[m[32m        android:layout_width="match_parent"[m
[32m+[m[32m        android:layout_height="50dp"[m
[32m+[m[32m        android:drawableBottom="@drawable/bottom_line"[m
         android:text="button" />[m
 [m
 </LinearLayout>[m
[1mdiff --git a/app/src/main/res/values/colors.xml b/app/src/main/res/values/colors.xml[m
[1mindex 3beb20b..115f486 100644[m
[1m--- a/app/src/main/res/values/colors.xml[m
[1m+++ b/app/src/main/res/values/colors.xml[m
[36m@@ -13,4 +13,6 @@[m
     <color name="sa_green">#1E9618</color>[m
     <color name="sa_green_transparent">#801E9618</color>[m
     <color name="sa_green_dark">#146310</color>[m
[32m+[m[32m    <color name="cycle_border">#c2c2c2</color>[m
[32m+[m[32m    <color name="cycle_bg">@color/white</color>[m
 </resources>[m

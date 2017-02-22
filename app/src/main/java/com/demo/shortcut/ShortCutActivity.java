package com.demo.shortcut;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.demo.R;

/**
 * Created by HuLiZhong on 2017/2/22.
 */
public class ShortCutActivity extends Activity {
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_cut);

        findViewById(R.id.tv1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAddShortCut()) {
                    shortcutAdd("changeIt!", count);
                }
                shortcutAdd("changeIt!", count);
            }
        });
        findViewById(R.id.tv2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shortcutDel("changeIt!");
                count++;
                shortcutAdd("changeIt!", count);
            }
        });
    }

    //    private void shortcutAdd(String name, int number) {
//        // Intent to be send, when shortcut is pressed by user ("launched")
//        Intent shortcutIntent = new Intent(getApplicationContext(), ShortCutActivity.class);
//        shortcutIntent.setAction(Constants.ACTION_PLAY);
//
//        // Create bitmap with number in it -> very default. You probably want to give it a more stylish look
//        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
//        Paint paint = new Paint();
//        paint.setColor(0xFF808080); // gray
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(50);
//        new Canvas(bitmap).drawText("" + number, 50, 50, paint);
////        ((ImageView) findViewById(R.id.icon)).setImageBitmap(bitmap);
//
//        // Decorate the shortcut
//        Intent addIntent = new Intent();
//        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
//        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
//        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);
//
//        // Inform launcher to create shortcut
//        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
//        getApplicationContext().sendBroadcast(addIntent);
//    }
//
//    private void shortcutDel(String name) {
//        // Intent to be send, when shortcut is pressed by user ("launched")
//        Intent shortcutIntent = new Intent(getApplicationContext(), ShortCutActivity.class);
//        shortcutIntent.setAction(Constants.ACTION_PLAY);
//
//        // Decorate the shortcut
//        Intent delIntent = new Intent();
//        delIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
//        delIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
//
//        // Inform launcher to remove shortcut
//        delIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
//        getApplicationContext().sendBroadcast(delIntent);
//    }
//新增快捷方式
    private void shortcutAdd(String name, int number) {
        //设置快捷方式点击后要打开的Activity（主入口）
        Intent shortcutIntent = new Intent(getApplicationContext(), ShortCutActivity.class);
        shortcutIntent.setAction(Constants.ACTION_PLAY);

        //这里创建了一个numbe的bitmap, 也可以设置自己想要的图表
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.indicator_code_lock_point_area_red_holo2);
        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Paint paint = new Paint();
        paint.setColor(0xFF808080); // gray
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        new Canvas(bitmap).drawText("" + number, 50, 50, paint);

        //设置快捷方式
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, bitmap);

        //创建快捷方式
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(addIntent);
    }

    //删除快捷方式
    private void shortcutDel(String name) {
        // Intent to be send, when shortcut is pressed by user ("launched")
        Intent shortcutIntent = new Intent(getApplicationContext(), ShortCutActivity.class);
        shortcutIntent.setAction(Constants.ACTION_PLAY);

        // Decorate the shortcut
        Intent delIntent = new Intent();
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        delIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // Inform launcher to remove shortcut
        //删除快捷方式
        delIntent.setAction("com.android.launcher.action.UNINSTALL_SHORTCUT");
        getApplicationContext().sendBroadcast(delIntent);
    }

    //判断快捷方式是否存在
    public boolean isAddShortCut() {

        final ContentResolver cr = this.getContentResolver();

        int versionLevel = android.os.Build.VERSION.SDK_INT;
        String AUTHORITY = "com.android.launcher2.settings";

        //2.2以上的系统的文件文件名字是不一样的
        if (versionLevel >= 8) {
            AUTHORITY = "com.android.launcher2.settings";
        } else {
            AUTHORITY = "com.android.launcher.settings";
        }

        final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
                + "/favorites?notify=true");
        Cursor c = cr.query(CONTENT_URI,
                new String[]{"title", "iconResource"}, "title=?",
                new String[]{getString(R.string.app_name)}, null);

        if (c != null && c.getCount() > 0) {
            return true;
        }
        return false;
    }
}

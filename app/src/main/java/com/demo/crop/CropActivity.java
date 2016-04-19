package com.demo.crop;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.demo.R;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hulizhong on 2016/4/19.
 */
public class CropActivity extends Activity {

    private String inputUri;
    private String outputUri;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);
        imageView = (ImageView) findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getImagesPath(CropActivity.this);
                inputUri = "file:/storage/emulated/0/DCIM/Camera/IMG_20160211_122548.jpg";
                outputUri = "file:/storage/emulated/0/DCIM/Camera/IMG_20160211_122548_2.jpg";
                File file = new File("/storage/emulated/0/DCIM/Camera/IMG_20160211_122548_2.jpg");
                try {
                    if (!file.exists()) {
                        boolean create = file.createNewFile();
                        System.err.println("create:" + create);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
//                Intent intent = new Intent(getBaseContext(), MyCropImageActivity.class);
//                intent.setData(Uri.parse(inputUri));
//                intent.putExtra("aspect_x", 2);
//                intent.putExtra("aspect_y", 1);
//                intent.putExtra("output", Uri.parse(outputUri));
//                startActivity(intent);
//                Crop.of(Uri.parse(inputUri), Uri.parse(outputUri)).withAspect(100, 44).withMaxSize(1080, 1080).start(CropActivity.this,Crop.REQUEST_CROP);
                Crop.pickImage(CropActivity.this, Crop.REQUEST_CROP);
//                this.aspectX = extras.getInt("aspect_x");
//                this.aspectY = extras.getInt("aspect_y");
//                this.maxX = extras.getInt("max_x");
//                this.maxY = extras.getInt("max_y");
//                this.saveUri = (Uri)extras.getParcelable("output");

            }
        });
    }

    public ArrayList<String> getImagesPath(Context activity) {
        Uri uri;
        ArrayList<String> listOfAllImages = new ArrayList<String>();
        Cursor cursor = null;
        int column_index_data;
        String pathOfImage = null;
        uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = {"DISTINCT " + MediaStore.MediaColumns.DATA};

        try {
            cursor = activity.getContentResolver().query(uri, projection, null, null, MediaStore.Images.Media.DATE_MODIFIED + " DESC");
            if (cursor == null) {
                return listOfAllImages;
            }
            column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            while (cursor.moveToNext()) {
                pathOfImage = cursor.getString(column_index_data);
                if (TextUtils.isEmpty(pathOfImage) || !new File(pathOfImage).exists()) {
                    continue;
                }
                System.err.println(pathOfImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return listOfAllImages;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == 100 && resultCode == RESULT_OK) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
            Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20160211_122548_2.jpg", options);
            imageView.setImageBitmap(bitmap);
        } else if (requestCode == Crop.REQUEST_CROP && resultCode == RESULT_OK) {
            Crop.of(result.getData(), Uri.parse(outputUri)).withAspect(100, 44).withMaxSize(1080, 1080).start(CropActivity.this, 100);
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//            Bitmap bitmap = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20160211_122548_2.jpg", options);
//            imageView.setImageBitmap(bitmap);
        }
    }
}

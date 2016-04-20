//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.soundcloud.android.crop;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.opengl.GLES10;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;

import com.soundcloud.android.crop.ImageViewTouchBase.Recycler;
import com.soundcloud.android.crop.R.id;
import com.soundcloud.android.crop.R.layout;
import com.soundcloud.android.crop.R.string;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.CountDownLatch;

public class MyCropImageActivity extends MonitoredActivity {
    private static final int SIZE_DEFAULT = 2048;
    private static final int SIZE_LIMIT = 4096;
    private final Handler handler = new Handler();
    private int aspectX;
    private int aspectY;
    private int maxX;
    private int maxY;
    private int exifRotation;
    private Uri sourceUri;
    private Uri saveUri;
    private boolean isSaving;
    private int sampleSize;
    private RotateBitmap rotateBitmap;
    private CropImageView imageView;
    private HighlightView cropView;

    public MyCropImageActivity() {
    }

    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getThis().setupWindowFlags();
        getThis().setupViews();
        getThis().loadInput();
        if (getThis().rotateBitmap == null) {
            getThis().finish();
        } else {
            getThis().startCrop();
        }
    }

    @TargetApi(19)
    private void setupWindowFlags() {
        getThis().requestWindowFeature(1);
        if (VERSION.SDK_INT >= 19) {
            getThis().getWindow().clearFlags(67108864);
        }

    }

    private void setupViews() {
        getThis().setContentView(layout.crop__activity_crop);
        this.imageView = (CropImageView) this.findViewById(id.crop_image);
        this.imageView.context = this;
        this.imageView.setRecycler(new Recycler() {
            public void recycle(Bitmap b) {
                b.recycle();
                System.gc();
            }
        });
        this.findViewById(id.btn_cancel).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MyCropImageActivity.this.setResult(0);
                MyCropImageActivity.this.finish();
            }
        });
        this.findViewById(id.btn_done).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                MyCropImageActivity.this.onSaveClicked();
            }
        });
    }

    private void loadInput() {
        Intent intent = this.getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            this.aspectX = extras.getInt("aspect_x");
            this.aspectY = extras.getInt("aspect_y");
            this.maxX = extras.getInt("max_x");
            this.maxY = extras.getInt("max_y");
            this.saveUri = (Uri) extras.getParcelable("output");
        }

        this.sourceUri = intent.getData();
        if (this.sourceUri != null) {
            this.exifRotation = CropUtil.getExifRotation(CropUtil.getFromMediaUri(this, this.getContentResolver(), this.sourceUri));
            InputStream is = null;

            try {
                this.sampleSize = this.calculateBitmapSampleSize(this.sourceUri);
                is = this.getContentResolver().openInputStream(this.sourceUri);
                Options e = new Options();
                e.inSampleSize = this.sampleSize;
                this.rotateBitmap = new RotateBitmap(BitmapFactory.decodeStream(is, (Rect) null, e), this.exifRotation);
            } catch (IOException var9) {
                Log.e("Error reading image: " + var9.getMessage(), var9);
                this.setResultException(var9);
            } catch (OutOfMemoryError var10) {
                Log.e("OOM reading image: " + var10.getMessage(), var10);
                this.setResultException(var10);
            } finally {
                CropUtil.closeSilently(is);
            }
        }

    }

    private int calculateBitmapSampleSize(Uri bitmapUri) throws IOException {
        InputStream is = null;
        Options options = new Options();
        options.inJustDecodeBounds = true;

        try {
            is = this.getContentResolver().openInputStream(bitmapUri);
            BitmapFactory.decodeStream(is, (Rect) null, options);
        } finally {
            CropUtil.closeSilently(is);
        }

        int maxSize = this.getMaxImageSize();

        int sampleSize;
        for (sampleSize = 1; options.outHeight / sampleSize > maxSize || options.outWidth / sampleSize > maxSize; sampleSize <<= 1) {
            ;
        }

        return sampleSize;
    }

    private int getMaxImageSize() {
        int textureLimit = this.getMaxTextureSize();
        return textureLimit == 0 ? 2048 : Math.min(textureLimit, 4096);
    }

    private int getMaxTextureSize() {
        int[] maxSize = new int[1];
        GLES10.glGetIntegerv(3379, maxSize, 0);
        return maxSize[0];
    }

    private void startCrop() {
        if (!this.isFinishing()) {
            this.imageView.setImageRotateBitmapResetBase(this.rotateBitmap, true);
            CropUtil.startBackgroundJob(this, (String) null, this.getResources().getString(string.crop__wait), new Runnable() {
                public void run() {
                    final CountDownLatch latch = new CountDownLatch(1);
                    MyCropImageActivity.this.handler.post(new Runnable() {
                        public void run() {
                            if (MyCropImageActivity.this.imageView.getScale() == 1.0F) {
                                MyCropImageActivity.this.imageView.center();
                            }

                            latch.countDown();
                        }
                    });

                    try {
                        latch.await();
                    } catch (InterruptedException var3) {
                        throw new RuntimeException(var3);
                    }

                    (MyCropImageActivity.this.new Cropper()).crop();
                }
            }, this.handler);
        }
    }

    private void onSaveClicked() {
        if (this.cropView != null && !this.isSaving) {
            this.isSaving = true;
            Rect r = this.cropView.getScaledCropRect((float) this.sampleSize);
            int width = r.width();
            int height = r.height();
            int outWidth = width;
            int outHeight = height;
            if (this.maxX > 0 && this.maxY > 0 && (width > this.maxX || height > this.maxY)) {
                float e = (float) width / (float) height;
                if ((float) this.maxX / (float) this.maxY > e) {
                    outHeight = this.maxY;
                    outWidth = (int) ((float) this.maxY * e + 0.5F);
                } else {
                    outWidth = this.maxX;
                    outHeight = (int) ((float) this.maxX / e + 0.5F);
                }
            }

            Bitmap croppedImage;
            try {
                croppedImage = this.decodeRegionCrop(r, outWidth, outHeight);
            } catch (IllegalArgumentException var8) {
                this.setResultException(var8);
                this.finish();
                return;
            }

            if (croppedImage != null) {
                this.imageView.setImageRotateBitmapResetBase(new RotateBitmap(croppedImage, this.exifRotation), true);
                this.imageView.center();
                this.imageView.highlightViews.clear();
            }

            this.saveImage(croppedImage);
        }
    }

    private void saveImage(final Bitmap croppedImage) {
        if (croppedImage != null) {
            CropUtil.startBackgroundJob(this, (String) null, this.getResources().getString(string.crop__saving), new Runnable() {
                public void run() {
                    saveOutput(croppedImage);
                }
            }, this.handler);
        } else {
            this.finish();
        }

    }

    private Bitmap decodeRegionCrop(Rect rect, int outWidth, int outHeight) {
        this.clearImageView();
        InputStream is = null;
        Bitmap croppedImage = null;

        try {
            is = this.getContentResolver().openInputStream(this.sourceUri);
            BitmapRegionDecoder e = BitmapRegionDecoder.newInstance(is, false);
            int width = e.getWidth();
            int height = e.getHeight();
            Matrix e1;
            if (this.exifRotation != 0) {
                e1 = new Matrix();
                e1.setRotate((float) (-this.exifRotation));
                RectF adjusted = new RectF();
                e1.mapRect(adjusted, new RectF(rect));
                adjusted.offset(adjusted.left < 0.0F ? (float) width : 0.0F, adjusted.top < 0.0F ? (float) height : 0.0F);
                rect = new Rect((int) adjusted.left, (int) adjusted.top, (int) adjusted.right, (int) adjusted.bottom);
            }

            try {
                croppedImage = e.decodeRegion(rect, new Options());
                if (rect.width() > outWidth || rect.height() > outHeight) {
                    e1 = new Matrix();
                    e1.postScale((float) outWidth / (float) rect.width(), (float) outHeight / (float) rect.height());
                    croppedImage = Bitmap.createBitmap(croppedImage, 0, 0, croppedImage.getWidth(), croppedImage.getHeight(), e1, true);
                }
            } catch (IllegalArgumentException var16) {
                throw new IllegalArgumentException("Rectangle " + rect + " is outside of the image (" + width + "," + height + "," + this.exifRotation + ")", var16);
            }
        } catch (IOException var17) {
            Log.e("Error cropping image: " + var17.getMessage(), var17);
            this.setResultException(var17);
        } catch (OutOfMemoryError var18) {
            Log.e("OOM cropping image: " + var18.getMessage(), var18);
            this.setResultException(var18);
        } finally {
            CropUtil.closeSilently(is);
        }

        return croppedImage;
    }

    private void clearImageView() {
        this.imageView.clear();
        if (this.rotateBitmap != null) {
            this.rotateBitmap.recycle();
        }

        System.gc();
    }

    private void saveOutput(final Bitmap croppedImage) {
        if (this.saveUri != null) {
            OutputStream b = null;

            try {
                b = this.getContentResolver().openOutputStream(this.saveUri);
                if (b != null) {
                    croppedImage.compress(CompressFormat.JPEG, 90, b);
                }
            } catch (IOException var7) {
                this.setResultException(var7);
                Log.e("Cannot open file: " + this.saveUri, var7);
            } finally {
                CropUtil.closeSilently(b);
            }

            CropUtil.copyExifRotation(CropUtil.getFromMediaUri(this, this.getContentResolver(), this.sourceUri), CropUtil.getFromMediaUri(this, this.getContentResolver(), this.saveUri));
            this.setResultUri(this.saveUri);
        }

        this.handler.post(new Runnable() {
            public void run() {
                MyCropImageActivity.this.imageView.clear();
                croppedImage.recycle();
            }
        });
        this.finish();
    }

    protected void onDestroy() {
        super.onDestroy();
        if (this.rotateBitmap != null) {
            this.rotateBitmap.recycle();
        }

    }

    public boolean onSearchRequested() {
        return false;
    }

    public boolean isSaving() {
        return this.isSaving;
    }

    private void setResultUri(Uri uri) {
        this.setResult(-1, (new Intent()).putExtra("output", uri));
    }

    private void setResultException(Throwable throwable) {
        this.setResult(404, (new Intent()).putExtra("error", throwable));
    }

    private class Cropper {
        private Cropper() {
        }

        private void makeDefault() {
            if (MyCropImageActivity.this.rotateBitmap != null) {
                HighlightView hv = new HighlightView(MyCropImageActivity.this.imageView);
                int width = MyCropImageActivity.this.rotateBitmap.getWidth();
                int height = MyCropImageActivity.this.rotateBitmap.getHeight();
                Rect imageRect = new Rect(0, 0, width, height);
//                int cropWidth = Math.min(width, height) * 4 / 5;
                int cropWidth = (int) (Math.min(width, height) * 0.98);
                int cropHeight = cropWidth;
                if (MyCropImageActivity.this.aspectX != 0 && MyCropImageActivity.this.aspectY != 0) {
                    if (MyCropImageActivity.this.aspectX > MyCropImageActivity.this.aspectY) {
                        cropHeight = cropWidth * MyCropImageActivity.this.aspectY / MyCropImageActivity.this.aspectX;
                    } else {
                        cropWidth = cropWidth * MyCropImageActivity.this.aspectX / MyCropImageActivity.this.aspectY;
                    }
                }

                int x = (width - cropWidth) / 2;
                int y = (height - cropHeight) / 2;
                RectF cropRect = new RectF((float) x, (float) y, (float) (x + cropWidth), (float) (y + cropHeight));
                hv.setup(MyCropImageActivity.this.imageView.getUnrotatedMatrix(), imageRect, cropRect, getThis().aspectX != 0 && MyCropImageActivity.this.aspectY != 0);
                MyCropImageActivity.this.imageView.add(hv);
            }
        }

        public void crop() {
            MyCropImageActivity.this.handler.post(new Runnable() {
                public void run() {
                    Cropper.this.makeDefault();
                    MyCropImageActivity.this.imageView.invalidate();
                    if (MyCropImageActivity.this.imageView.highlightViews.size() == 1) {
                        MyCropImageActivity.this.cropView = (HighlightView) MyCropImageActivity.this.imageView.highlightViews.get(0);
                        MyCropImageActivity.this.cropView.setFocus(true);
                    }

                }
            });
        }
    }

    @NonNull
    private MyCropImageActivity getThis() {
        return MyCropImageActivity.this;
    }
}

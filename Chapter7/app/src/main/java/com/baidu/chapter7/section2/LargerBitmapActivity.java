package com.baidu.chapter7.section2;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.widget.ImageView;

import com.baidu.chapter7.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;

import rx.functions.Action1;

public class LargerBitmapActivity extends AppCompatActivity {
    private ImageView imageView;
    private String name="example1.jpg"; // 图片名字
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_larger_bitmap);
        imageView= (ImageView) findViewById(R.id.imageView);
        loadImage();
    }

    private void loadImage() {
        // 获取操作SD卡权限
        RxPermissions.getInstance(this)
                .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .subscribe(new Action1<Boolean>() {
                    @Override
                    public void call(Boolean aBoolean) {
                        if(aBoolean){
                            scaleLoad();
                        }
                    }
                });
    }

    private void scaleLoad() {
        File file=new File(Environment.getExternalStorageDirectory(),name);
        if(!file.exists()) return;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        // 设置为true，代表加载器不加载图片, 而是把图片宽高读取出来
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
        int imageWidth = opts.outWidth;
        int imageHeight = opts.outHeight;
        Log.i("LargerBitmapActivity","图片宽:"+imageWidth);
        Log.i("LargerBitmapActivity","图片高:"+imageHeight);
        // 得到屏幕的宽和高
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics=new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight =displayMetrics.heightPixels;
        Log.i("LargerBitmapActivity","屏幕宽:"+screenWidth);
        Log.i("LargerBitmapActivity","屏幕高:"+screenHeight);
        // 计算缩放比例
        int widthScale = imageWidth / screenWidth;
        int heightScale = imageHeight / screenHeight;
        int scale = widthScale > heightScale ? widthScale : heightScale;
        Log.i("LargerBitmapActivity","缩放比例为:"+scale);
        // 设置为false,加载器就会加载图片了
        opts.inJustDecodeBounds = false;
        // 使用缩放比例进行缩放加载图片
        opts.inSampleSize = scale;
        Bitmap bm = BitmapFactory.decodeFile(file.getAbsolutePath(), opts);
        //显示在imageView上
        imageView.setImageBitmap(bm);
    }
}

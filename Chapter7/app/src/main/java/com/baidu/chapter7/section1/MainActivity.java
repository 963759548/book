package com.baidu.chapter7.section1;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.baidu.chapter7.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //在资源中获取Bitmap
    public Bitmap getBitmapFromResource(){
        Resources res=getResources();
        //R.drawable.sample_0表示drawable目录下有一个名字叫sample_0的图片
        return BitmapFactory.decodeResource(res, R.drawable.sample_0);
    }
    //在资源中获取Drawable
    public Drawable getDrawableFromResource(){
        Drawable drawable=getResources().getDrawable(R.drawable.sample_0);
        return  drawable;
    }

    public  Bitmap drawableToBitmap(Drawable drawable) {
        //创建bitmap, 参数1 宽度 参数2 高度, 参数3 压缩方式
        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(), //获取Drawable本身的宽度
                drawable.getIntrinsicHeight(),//获取Drawable本身的高度
                // 根据Drawable的是否透明采取不同的压缩方式
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                        : Bitmap.Config.RGB_565);
        //创建画布,绘制Bitmap
        Canvas canvas = new Canvas(bitmap);
        //设置
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        //把Drawable绘制到bitmap上
        drawable.draw(canvas);
        // 返回bitmap
        return bitmap;

    }
    public  Drawable bitmapToDrawable(Bitmap bitmap){
        Drawable drawable=new BitmapDrawable(getResources(),bitmap);
        return drawable;
    }
}

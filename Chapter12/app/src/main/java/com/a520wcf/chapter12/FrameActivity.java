package com.a520wcf.chapter12;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class FrameActivity extends AppCompatActivity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        image = (ImageView) findViewById(R.id.frame_image);
        //将动画资源文件设置为ImageView的显示的图片
        //image.setImageResource(R.drawable.frame);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        //完全编码实现的动画效果
        AnimationDrawable anim = new AnimationDrawable();
        for (int i = 1; i <= 6; i++) {
            //根据资源名称和目录获取R.java中对应的资源ID
            int id = getResources().getIdentifier("f" + i, "drawable", getPackageName());
            //根据资源ID获取到Drawable对象
            Drawable drawable = getResources().getDrawable(id);
            //将此帧添加到AnimationDrawable中
            anim.addFrame(drawable, 500);
        }
        anim.setOneShot(false); //设置为loop
        image.setImageDrawable(anim);  //将动画设置为ImageView显示的图片
        anim.start();   //开始动画

    }

    //开始动画的点击事件
    public void runFrame(View view) {
        //获取ImageView显示的图片,此时已被编译成AnimationDrawable
        AnimationDrawable anim = (AnimationDrawable) image.getDrawable();
        anim.start();   //开始动画
    }
    //结束动画的点击事件
    public void stopFrame(View view) {
        AnimationDrawable anim = (AnimationDrawable) image.getDrawable();
        if (anim.isRunning()) { //如果正在运行,就停止
            anim.stop();
        }
    }
}

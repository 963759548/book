package com.a520wcf.chapter12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv= (ImageView) findViewById(R.id.image);

    }

    public void alphaClick(View view) {
        //加载alpha动画
        Animation alphaAnimation =AnimationUtils.loadAnimation(this,
                R.anim.alpha);
        //动画运行完成保持结束的状态
        alphaAnimation.setFillAfter(true);
        iv.startAnimation(alphaAnimation);
    }
    //移除动画的点击事件
    public void reset(View view) {
        //移除所有动画
        iv.clearAnimation();
    }

    //scale按钮的点击事件
    public void scaleClick(View view) {
        //加载scale动画
        Animation scaleAnimation = AnimationUtils.loadAnimation(this,
                R.anim.scale);
        ////动画运行完成保持结束的状态
        scaleAnimation.setFillAfter(true);
        iv.startAnimation(scaleAnimation);
    }
    //位移按钮的点击事件
    public void translateClick(View view) {
        //加载alpha动画
        Animation translateAnimation =AnimationUtils.loadAnimation(this,
                R.anim.translate);
        //动画运行完成保持结束的状态
        translateAnimation.setFillAfter(true);
        iv.startAnimation(translateAnimation);
    }

    public void rotateClick(View view) {
        //加载alpha动画
        Animation rotateAnimation =AnimationUtils.loadAnimation(this,
                R.anim.rotate);
        //动画运行完成保持结束的状态
        rotateAnimation.setFillAfter(true);
        iv.startAnimation(rotateAnimation);
    }

}

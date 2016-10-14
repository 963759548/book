package com.a520wcf.chapter12;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class PropertyActivity extends AppCompatActivity {
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property);
        iv= (ImageView) findViewById(R.id.image);
    }

    public void alphaClick(View view) {
        //利用ObjectAnimator实现透明度动画
        ObjectAnimator.ofFloat(iv, "alpha", 1, 0, 1)
                .setDuration(2000).start();
    }
    //移除动画的点击事件
    public void reset(View view) {
        //移除所有动画
        //属性动画是实实在在修改了控件的属性 所以一般还原就是把属性改回原来的就可以了
    }

    //scale按钮的点击事件
    public void scaleClick(View view) {
        //利用AnimatorSet和ObjectAnimator实现缩放动画
        final AnimatorSet animatorSet = new AnimatorSet();
        //设置缩放的基准点
        iv.setPivotX(iv.getWidth()/2);
        iv.setPivotY(iv.getHeight()/2);
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(iv, "scaleX", 1, 0,1).setDuration(5000), //X轴缩放
                ObjectAnimator.ofFloat(iv, "scaleY", 1, 0,1).setDuration(5000));//Y轴缩放
        animatorSet.start();
    }
    //位移按钮的点击事件
    public void translateClick(View view) {
        //利用AnimatorSet和ObjectAnimator实现平移动画
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(iv, "translationX", 20, 100,0).setDuration(2000),
                ObjectAnimator.ofFloat(iv, "translationY", 20, 100,0).setDuration(2000));
        animatorSet.start();
    }

    public void rotateClick(View view) {
        //利用ObjectAnimator实现旋转动画
        iv.setPivotX(iv.getWidth()/2);
        iv.setPivotY(iv.getHeight()/2);
        ObjectAnimator.ofFloat(iv, "rotation", 0, 360)
                .setDuration(1000).start();
    }
}

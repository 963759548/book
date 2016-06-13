package com.baidu.chapter4.section5;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yull on 10/26.
 */
public class BaseActivity extends AppCompatActivity {
    // 用来记录所有运行的Activity的集合
    protected static final List<BaseActivity> mActivities = new LinkedList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // log输出当前运行的Activity
        Log.i("BaseActivity", getClass().getSimpleName());
        //如果Activity 调用onCreate方法,证明打开了当前Activity,添加到集合中。
        synchronized (mActivities) {  //  最好加一把同步锁
            mActivities.add(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //如果Activity onDestroy,当前Activity已经销毁了,在集合中移除。
        synchronized (mActivities) {  //  最好加一把同步锁
            mActivities.remove(this);
        }
    }
    /**
     * 退出整个应用程序
     */
    public static void exitApp() {
        //  把所有activity 全部finish
        List<BaseActivity> copy;
        synchronized (mActivities) { //  最好加一把同步锁
            // 把运行的Activity复制到新的数组,
            // 因为调用activity.finish(),就会执行onDestroy,然后就会把Activity在集合中移除。
            // 集合在遍历的过程中是不能移除或者添加元素的,所有需要复制到新的集合中
            copy = new ArrayList<>(mActivities);
        }
        for (BaseActivity activity : copy) {
            activity.finish();
        }
    }
}

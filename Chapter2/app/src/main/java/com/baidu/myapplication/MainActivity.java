package com.baidu.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;


public class MainActivity extends AppCompatActivity {
    // 首先启动onCreate方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置界面显示的布局
        setContentView(R.layout.activity_main);
        String url=BuildConfig.API_URL;
        boolean log_calls=BuildConfig.LOG_CALLS;
        int stringid=R.string.str_name;

        //参数1 代表日志的标签, 参数2是日志信息。
        Log.v("MainActivit","v");
        Log.d("MainActivity","d");
        Log.i("MainActivity","info");
        Log.w("MainActivity","w");
        Log.e("MainActivity","e");
        Log.wtf("MainActivity","wtf");
        System.out.println("out");
        LogUtils.i("MainActivity","LogUtils");
        Logger.d("a");
    }
}

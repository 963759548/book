package com.a520wcf.chapter11;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by apple on 16/9/30.
 */

public class MyIntentService extends IntentService {

    public MyIntentService() {
        //必须调用父类的有参数构造方法
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // 查看线程的id
        Log.i("MyIntentService","当前线程id"+Thread.currentThread().getId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyIntentService","onDestroy");
    }
}

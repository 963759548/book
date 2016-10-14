package com.a520wcf.chapter11;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;


public class MyService extends Service {
    //服务创建的时候调用
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("MyService","--onCreate");
    }
    //服务每次启动的时候调用
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyService","--onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    //服务销毁的时候 调用。
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("MyService","--onDestroy");
    }
    private DownloadBinder downloadBinder = new DownloadBinder();
    class DownloadBinder extends Binder{
        public void startDownload() {
            Log.d("MyService", "开始下载");
        }
        public int getProgress() {
            Log.d("MyService", "进度变化");
            return 0;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return downloadBinder;
    }
}

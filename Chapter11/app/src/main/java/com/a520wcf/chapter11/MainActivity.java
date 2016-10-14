package com.a520wcf.chapter11;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start(View view) {
        Log.i("MainActivity","----启动服务----");
        Intent intent=new Intent(this,MyService.class);
        startService(intent);
    }

    public void stop(View view) {
        Log.i("MainActivity","----停止服务----");
        Intent intent=new Intent(this,MyService.class);
        stopService(intent);
    }

    private MyService.DownloadBinder downloadBinder;
    //绑定服务的桥梁
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
        //当服务绑定成功调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (MyService.DownloadBinder) service;
            //调用服务的方法
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        } };


    //绑定服务
    public void bind(View view) {
        Log.i("MainActivity","--绑定服务--");
        Intent bindIntent = new Intent(this, MyService.class);
        // 绑定服务  参数1意图  参数2 ServiceConnection
        // 参数3表示如果绑定服务时没创建会自动创建
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
    }

    //解绑服务
    public void unBind(View view) {
        Log.i("MainActivity","--解绑服务--");
        //解绑服务
        unbindService(connection);
    }

    public void startIntentService(View view) {
        Log.i("MainActivity", "主线程id:" + Thread.currentThread().getId());
        Intent intentService = new Intent(this, MyIntentService.class);
        startService(intentService); //开启IntentService
    }

    public void download(View view) {
        Log.i("MainActivity","下载");
        Intent intent=new Intent(this,DownLoadService.class);
        startService(intent);
    }
}

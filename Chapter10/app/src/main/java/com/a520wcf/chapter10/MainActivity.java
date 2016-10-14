package com.a520wcf.chapter10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.a520wcf.chapter10.receiver.FinalResultReceiver;

public class MainActivity extends AppCompatActivity {


    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //创建IntentFilter
        intentFilter = new IntentFilter();
        //设置监听的Action
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        //注册监听
        registerReceiver(networkChangeReceiver, intentFilter);

        // 获取实例
        final LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(this);

        Button button = (Button) findViewById(R.id.localButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.a520wcf.chapter10.LOCAL_BROADCAST");
                localBroadcastManager.sendBroadcast(intent); // 发送本地广播
            }
        });

        intentFilter = new IntentFilter();
        intentFilter.addAction("com.a520wcf.chapter10.LOCAL_BROADCAST");
        LocalReceiver localReceiver = new LocalReceiver();
        localBroadcastManager.registerReceiver(localReceiver, intentFilter);
    }

    class LocalReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "收到了本地广播",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //当程序退出的时候,反注册监听
        unregisterReceiver(networkChangeReceiver);
    }


    //广播接受者
    class NetworkChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            //getNetworkType() 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
            Toast.makeText(context, "网络状态发生变化" + getNetworkType()
                    , Toast.LENGTH_SHORT).show();
        }
    }

    public static final int NETTYPE_WIFI = 0x01;
    public static final int NETTYPE_CMWAP = 0x02;
    public static final int NETTYPE_CMNET = 0x03;

    /**
     * 获取当前网络类型
     *
     * @return 0：没有网络   1：WIFI网络   2：WAP网络    3：NET网络
     */
    public int getNetworkType() {
        int netType = 0;
        //获取网络管理者
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取当前网络信息
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return netType;
        }
        int nType = networkInfo.getType();
        if (nType == ConnectivityManager.TYPE_MOBILE) {
            String extraInfo = networkInfo.getExtraInfo();
            if (!TextUtils.isEmpty(extraInfo)) {
                if (extraInfo.toLowerCase().equals("cmnet")) {
                    netType = NETTYPE_CMNET;
                } else {
                    netType = NETTYPE_CMWAP;
                }
            }
        } else if (nType == ConnectivityManager.TYPE_WIFI) {
            netType = NETTYPE_WIFI;
        }
        return netType;
    }


    //发送无序广播
    public void click2(View view) {
        Log.i("MainActivity", "无序广播");
        Intent intent = new Intent();
        intent.setAction("com.a520wcf.chapter10.CUSTOM");
        //有序广播第二个参数可以指定所需权限,在这里写null就可以
        sendBroadcast(intent);
    }

    //发送有序广播
    public void click1(View view) {
        Log.i("MainActivity", "有序广播");
        Intent intent = new Intent();
        intent.setAction("com.a520wcf.chapter10.CUSTOM");
        //有序广播第二个参数可以指定所需权限,在这里写null就可以
        //sendOrderedBroadcast(intent,null);

        //指定最终广播接受者
        sendOrderedBroadcast(intent, null, new FinalResultReceiver(), null, 0, null, null);
    }
}

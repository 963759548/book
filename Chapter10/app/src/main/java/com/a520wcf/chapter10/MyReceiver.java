package com.a520wcf.chapter10;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by apple on 16/9/29.
 */

public class MyReceiver extends BroadcastReceiver {
    //当收到广播的时候调用, intent就是发送广播的意图
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("MyReceiver","收到了广播");
        Toast.makeText(context,"收到了广播",Toast.LENGTH_SHORT).show();
    }
}

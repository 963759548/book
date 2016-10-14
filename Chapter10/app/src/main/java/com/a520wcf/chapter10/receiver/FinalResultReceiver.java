package com.a520wcf.chapter10.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by apple on 16/9/30.
 */

public class FinalResultReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("FinalResultReceiver","最终广播");
    }
}

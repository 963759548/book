package com.baidu.myapplication;

import android.util.Log;

public class LogUtils {
    public static final boolean DEBUG=true;

    public static void i(String tag,String msg){
        if(DEBUG) {
            Log.i(tag, msg);
        }
    }
}
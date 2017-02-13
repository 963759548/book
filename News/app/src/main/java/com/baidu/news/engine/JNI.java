package com.baidu.news.engine;


public class JNI {
    static {
        System.loadLibrary("native-lib");
    }
    /**获取AppKey*/
    public static native String getAppKey();
}

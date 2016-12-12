package com.a520wcf.jniproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // 加载函数库
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI2());
    }

    /**本地方法, 当前方法是通过C/C++代码实现*/
    public native String stringFromJNI();

    //手动添加native方法
    public native String stringFromJNI2();
}

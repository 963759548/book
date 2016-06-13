package com.baidu.chapter4.section5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baidu.chapter4.R;


public class Task2Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task2);
    }
    public void click1(View view){
        Intent intent=new Intent(this,Task1Activity.class);
        startActivity(intent);
    }

    public void click2(View view){
        Intent intent=new Intent(this,Task2Activity.class);
        startActivity(intent);
    }

    public void finishAll(View v){
        //  退出整个应用程序
        exitApp();
    }
}

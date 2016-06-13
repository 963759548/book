package com.baidu.chapter4.section5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.baidu.chapter4.R;


public class Task1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task1);


    }
    public void click1(View view){
        Intent intent=new Intent(this,Task1Activity.class);
        startActivity(intent);
    }

    public void click2(View view){
        Intent intent=new Intent(this,Task2Activity.class);
        startActivity(intent);
    }
    // 当Activity没有重新创建  但是其它的Activity  调用了startActivity的时候调用
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
}

package com.baidu.chapter4.section4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.baidu.chapter4.R;

/**
 * 生命周期
 */
public class LifeActivity extends AppCompatActivity {
    public static final String TAG="LifeActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //如果之前保存过数据, 取出来
        if(savedInstanceState!=null){
            String value=savedInstanceState.getString("key");
            Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "onCreate");
        setContentView(R.layout.activity_life);
    }
    public void click(View v) {
        Intent intent = new Intent(this, DialogActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG,"onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "onRestart");
    }
    // 当Activity异常退出的时候调用
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.e(TAG, "onSaveInstanceState");
        //保存了临时的数据
        outState.putString("key","value");
    }
//    // 异常退出重新打开的时候调用
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        Log.e(TAG, "onRestoreInstanceState");
//        // 取出临时的数据
//        String value=savedInstanceState.getString("key");
//        Toast.makeText(getApplicationContext(),value,Toast.LENGTH_SHORT).show();
//    }
}

package com.baidu.chapter4.section2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.baidu.chapter4.R;

/**
 * 接收数据
 */
public class RDataActivity extends AppCompatActivity {
    public static final String A1="a";
    public static final String B2="b";
    public static final String C3="c";

    public static Intent newIntent(Context context,String a,int b,float c){
        Intent intent=new Intent(context,RDataActivity.class);
        intent.putExtra(RDataActivity.A1,a);
        intent.putExtra(RDataActivity.B2,b);
        intent.putExtra(RDataActivity.C3,c);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rdata);
        Intent intent =getIntent(); // 获取到开启当前Activity的意图
        String  a=intent.getStringExtra(A1);
        int b=intent.getIntExtra(B2,0);  // 1  bb: 0
        float c=intent.getFloatExtra(C3, 1.0f);
        Toast.makeText(this,a+"..."+b+"..."+c,Toast.LENGTH_LONG).show();
    }
    public void exit(View v){
        Intent  intent=new Intent();
        intent.putExtra("result","resultData");
        setResult(0, intent); //resultCode
        finish();  // 主动关闭当前Activity
    }
}

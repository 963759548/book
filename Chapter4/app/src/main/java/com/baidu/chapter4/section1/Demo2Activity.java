package com.baidu.chapter4.section1;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.baidu.chapter4.R;

public class Demo2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo2);
    }
    /**
     * 拨打电话
     */
    public void dial(View v){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:1333333333"));
        startActivity(intent);
    }
    /**
     * 打开浏览器
     */
    public void goToBrowser(View v){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://www.baidu.com"));
        startActivity(intent);
    }
    /**
     * 分享
     */
    public void share(View v){
        Intent intent=new Intent();
        intent.setAction(Intent.ACTION_SEND);
        // 指定参数的类型
        intent.setType("text/plain"); // "image/png"
        // 设置要分享的文本
        intent.putExtra(Intent.EXTRA_TEXT,"于连林很帅");
        startActivity(intent);
    }
}

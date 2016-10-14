package com.a520wcf.chapter10;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.a520wcf.chapter10.utils.ShortcutUtils;

public class ShortCutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_cut);
    }

    //删除快捷方式的点击事件
    public void deleteShortCut(View view) {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));
        ShortcutUtils.deleteShortcut(this,"打开百度",intent,false);
    }
    //按钮点击事件 创建快捷方式
    public void addShortCut(View view) {
        //快捷方式打开的意图必须是隐式意图
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("https://www.baidu.com"));

        ShortcutUtils.addShortcut(this,"打开百度",R.mipmap.ic_launcher,intent,false);
    }
}

package com.baidu.chapter4.section1;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.baidu.chapter4.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn= (Button) findViewById(R.id.button);
        assert btn != null;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  声明Button的点击事件 跳转到Demo2Activity
                //  参数1 上下文  参数2 跳转Activity的字节码
               // Intent intent = new Intent(getApplicationContext(), Demo2Activity.class);
               // startActivity(intent);
                Intent intent = new Intent();
                intent.setAction("aaa.bbb.ccc");
                intent.addCategory("android.intent.category.DEFAULT");
                startActivity(intent);
            }
        });
    }
}

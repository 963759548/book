package com.baidu.chapter5.section1;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.baidu.chapter5.R;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    // sp保存数据
    SharedPreferences config;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 创建SP
        config = getSharedPreferences("config", MODE_PRIVATE);
        editText = (EditText) findViewById(R.id.editText);
        // 读取保存的数据  写入到editText
        // 取数据   参数2为 如果找不到"data"key值,默认返回的数据
        String data=config.getString("data","");
        editText.setText(data);
    }
    /**按钮点击事件*/
    public void saveData(View v) {
        // 1 获取输入的内容
        String data = editText.getText().toString();
        // 2获取到了编辑器
        SharedPreferences.Editor edit = config.edit();
        // 3 保存数据  key -value
        edit.putString("data",data);
        //4 保存到文件中
        //edit.commit(); // 效率慢  线程安全
        edit.apply();  // 效率快 线程不安全
    }
}

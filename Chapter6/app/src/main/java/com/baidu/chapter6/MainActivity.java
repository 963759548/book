package com.baidu.chapter6;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView= (TextView) findViewById(R.id.textView);
    }
    //在主线程new的Handler，就会在主线程进行后续处理。
    private Handler handler = new Handler(){
        //方式2 接受消息
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 处理消息
            String result = (String) msg.obj;
            textView.setText(result);
        }
    };
    public void click(View v){
        // 创建子线程并通过 start开启线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                final String result = NetUtils.get("http://likedev.applinzi.com");
                Log.i("MainActivity",result);
                //方式1
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
//                        //在UI线程更新UI
//                        textView.setText(result);
//                    }
//                });
                // 方式2
                Message message=new Message();
                message.obj=result; // 设置数据
                //发送消息
                handler.sendMessage(message);
            }
        }).start();
        // 方式3
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                final String result = NetUtils.get("http://likedev.applinzi.com");
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText(result);
//                    }
//                });
//            }
//        }).start();
    }

}

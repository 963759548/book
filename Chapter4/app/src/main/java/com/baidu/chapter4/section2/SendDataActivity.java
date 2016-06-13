package com.baidu.chapter4.section2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.baidu.chapter4.R;

/**
 * 传递数据
 */
public class SendDataActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data);
    }
    //  传递字符串 给ReceiveData
    public void sendData(View v){
        Intent intent=RDataActivity.newIntent(this,"aaa",1,1.5f);
        // map  key-value
       // startActivity(intent);  // 开启RDataActivity
        // 打开Activity, 需要接收打开Activity返回的数据
        startActivityForResult(intent,0);  // requestCode
    }
    // 当返回数据的时候 调用该方法
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==0&&data!=null){
            String result=data.getStringExtra("result");
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
        }
    }
}

package com.baidu.chapter6;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AsycTaskActivity extends AppCompatActivity {
    Button button;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asyc_task);
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadAsycTask().execute();//执行异步任务
            }
        });

    }

    class DownloadAsycTask extends AsyncTask<Void,Integer,Boolean>{

        @Override
        protected Boolean doInBackground(Void... params) {
            //后台执行
            for(int i=0;i<=100;i++){
                // 模拟后台耗时的操作
                try {
                    Thread.sleep(50);// 休息50毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false; //发生异常执行失败
                }
                publishProgress(i);// 更新进度
            }
            return true;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 后台任务执行前执行
            //创建进度条对话框, 参数为上下文,上下文必须是Activity
            progressDialog=new ProgressDialog(AsycTaskActivity.this);
            //设置进度条对话框为水平有进度的样式。
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.show();// 显示进度条对话框
        }
        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            // 后台任务执行后执行
            progressDialog.dismiss();//隐藏对话框
            if(aBoolean){
                Toast.makeText(getApplicationContext(),"执行成功", Toast.LENGTH_SHORT).show();
            }
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // 更新进度
            progressDialog.setProgress(values[0]);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(progressDialog!=null){
            progressDialog.dismiss();
            progressDialog=null;
        }
    }
}

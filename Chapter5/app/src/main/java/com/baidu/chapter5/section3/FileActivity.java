package com.baidu.chapter5.section3;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.chapter5.R;
import com.tbruyelle.rxpermissions.RxPermissions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import rx.functions.Action1;

public class FileActivity extends AppCompatActivity {
    private static final String LOG_TAG ="FileActivity" ;
    EditText editText;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);
        textView= (TextView) findViewById(R.id.textView);
        // 获取可用空间 单位是字节
        long freeSpace = Environment.getExternalStorageDirectory().getFreeSpace();
        //借助android.text.format.Formatter 格式化显示 可以把字节转换成正常的文本显示
        String freeSize=Formatter.formatFileSize(getApplicationContext(),freeSpace);
        textView.setText("SD卡剩余空间:"+freeSize);

        editText= (EditText) findViewById(R.id.editText);
        Button button = (Button) findViewById(R.id.button);
        assert button != null;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  保存数据
                String content = editText.getText().toString().trim();
                try {
                    // 写入到file目录
                    // /data/data/com.baidu.chapter5/file/save.txt
                    //  FileOutputStream fos= openFileOutput("save.txt",MODE_PRIVATE);
                    // 写入缓存目录
                    // 获取写入文件的流 /data/data/com.baidu.chapter5/cache/cache.txt
                    FileOutputStream fos= new FileOutputStream(new File(getCacheDir(), "cache.txt"));
                    // 对流进行包装,提高写入效率
                    PrintStream printStream = new PrintStream(fos);
                    // 写入对应的文字
                    printStream.print(content);
                    // 关流
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    // 按钮点击事件
    public void saveToSD(View v){
        if(ExternalStorageUtils.isExternalStorageWritable()) {
            final String content = editText.getText().toString().trim();
            Toast.makeText(this, "SD卡可用", Toast.LENGTH_SHORT).show();
            RxPermissions.getInstance(this)
                    // 申请权限
                    .request(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .subscribe(new Action1<Boolean>() {
                        @Override
                        public void call(Boolean granted) {
                            if (granted) {
                                //权限通过了 保存内容到SD
                                saveToSDFile(content);
                            }
                        }
                    });
        }
    }
    //保存内容到SD
    private void saveToSDFile(String content) {
        File file = new File(Environment.getExternalStorageDirectory(), "cache.text");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            // 对流进行包装,提高写入效率
            PrintStream printStream = new PrintStream(fos);
            // 写入对应的文字
            printStream.print(content);
            // 关流
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public File getAlbumStorageDir(String albumName) {
        // 获取保存图片的文件夹
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }
    public File getAlbumStorageDir(Context context, String albumName) {
        // 获取应用程序保存图片的私有文件夹
        File file = new File(context.getExternalFilesDir(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e(LOG_TAG, "Directory not created");
        }
        return file;
    }

}

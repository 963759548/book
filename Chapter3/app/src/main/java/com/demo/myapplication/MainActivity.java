package com.demo.myapplication;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
        //TODO  Toast
       // setContentView(R.layout.demo_coordinator);
   //     Button btn = (Button) findViewById(R.id.btn_show_toast);
//        assert btn != null;  // 断言 btn对象不为空 这句代码不加也行
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Toast toast = Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT);
////                // 设置在右下角,相对右下角x,y的偏移量为0
////                // toast.setGravity(Gravity.BOTTOM| Gravity.END, 0, 0);
////                toast.setMargin(0.2f, 0.9f);
////                // 必须调用show方法,否则不显示
////                toast.show();
//                showToast();
//            }
//        });

        //TODO Snackbar
//        setContentView(R.layout.demo_coordinator);
//        FloatingActionButton fab= (FloatingActionButton) findViewById(R.id.fab);
//        assert fab != null;
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Snackbar
//                        .make(v ,"1 item removed", Snackbar.LENGTH_SHORT)
//                        .setAction("undo", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                // 想干点什么就整点什么吧
//                            }
//                        }).show();
//            }
//        });
//    }

    private void showToast() {
        Toast toast = new Toast(this);
        // 布局填充器,可以把xml转换成java对象
        LayoutInflater inflater = getLayoutInflater();
        //参数1 布局的id,参数2 挂载的根布局,这个位置可以不传
        View layout = inflater.inflate(R.layout.custom_toast,null);
        // 设置Toast显示的View
        toast.setView(layout);
        //设置显示时间 单位是毫秒
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
    ProgressBar progressBar;
    Button button;
    //TODO 按钮点击事件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //----隐藏进度
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        button= (Button) findViewById(R.id.button);
        // 按钮点击的时候隐藏进度
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 隐藏进度
                progressBar.setVisibility(View.GONE);
            }
        });

        //------------按钮点击方式
//        // 匿名内部类
//        Button btn = (Button) findViewById(R.id.btn_show_toast);
//        assert btn != null;  // 断言 btn对象不为空 这句代码不加也行
//        btn.setOnClickListener(v -> showToast());
//        // 内部类
//        btn.setOnClickListener(new MyOnClickListener());
//        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_show_toast:
                Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT).show();
        }
    }

    public void show(View v){
        Toast.makeText(MainActivity.this, "toast", Toast.LENGTH_SHORT).show();
    }
}

package com.baidu.chapter6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        textView= (TextView) findViewById(R.id.textView);
    }
    public void click(View v){
        String[] str=new String[]{"http://likedev.applinzi.com","http://www.baidu.com"};

        //Observable.just("http://likedev.applinzi.com","http://www.baidu.com");
        //传递字符串参数
        Observable.from(str)
      //  Observable.just("http://likedev.applinzi.com","http://www.baidu.com")
                //规定请求现在在子线程
                .subscribeOn(Schedulers.io())
                .map(new Func1<String, String>() {
                    // 把字符串参数转换成字符串结果
                    @Override
                    public String call(String s) {
                        return NetUtils.get(s);
                    }
                })
                //observeOn之后的操作切换到主线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    //根据请求结果
                    @Override
                    public void call(String s) {
                        textView.setText(s);
                    }
                }, new Action1<Throwable>() {
                    // 处理异常
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                });
    }
}

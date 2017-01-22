package com.baidu.chapter6.section6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.baidu.chapter6.R;
import com.baidu.chapter6.section5.Bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class MobileByRetrofitActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textView;
    Button button;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile);
        textView= (TextView) findViewById(R.id.textView);
        button= (Button) findViewById(R.id.button);
        editText= (EditText) findViewById(R.id.editText);
        button.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        //初始化Retrofit,加载接口
        NetInterface netInterface = NetWork.getRetrofit().create(NetInterface.class);
        //RxJava方式
        netInterface.getAddress(editText.getText().toString(),"e6d7409002af0588ba8679910833659f")
                .subscribeOn(Schedulers.io())//设置网络请求在子线程中
                .observeOn(AndroidSchedulers.mainThread())// 回调在主线程中
                .subscribe(new Action1<Bean>() {
                    @Override
                    public void call(Bean bean) {
                        //请求成功
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        //请求失败
                    }
                });


        //请求接口 普通方式
//        netInterface.getAddress(editText.getText().toString(),"e6d7409002af0588ba8679910833659f")
//                .enqueue(new Callback<Bean>() {
//                    @Override
//                    public void onResponse(Call<Bean> call, Response<Bean> response) {
//                        //请求成功
//                        Bean bean = response.body();
//                        if("200".equals(bean.getResultcode())) {
//                            Bean.ResultBean body = bean.getResult();
//                            StringBuilder sb=new StringBuilder();
//                            //如果省份不为空 地址拼装省份
//                            if(!TextUtils.isEmpty(body.getProvince())){
//                                sb.append(body.getProvince());
//                            }
//                            // 城市
//                            if(!TextUtils.isEmpty(body.getCity())){
//                                sb.append(body.getCity());
//                            }
//                            textView.setText(sb.toString());
//                        }else{
//                            // 状态码不为200 显示错误信息
//                            textView.setText(bean.getReason());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<Bean> call, Throwable t) {
//                        //请求失败
//                    }
//                });

    }
}

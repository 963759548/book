package com.baidu.chapter6.section6;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yll on 2017/1/22.
 */

public class NetWork {
    private static Retrofit retrofit;

    /**返回Retrofit*/
    public static Retrofit getRetrofit(){
        if(retrofit==null){
            //创建Retrfit构建器
            Retrofit.Builder builder = new Retrofit.Builder();
            //指定网络请求的baseUrl
            retrofit = builder.baseUrl("http://apis.juhe.cn/")
                    //返回的数据通过Gson解析
                    .addConverterFactory(GsonConverterFactory.create())
                    //使用RxJava模式
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

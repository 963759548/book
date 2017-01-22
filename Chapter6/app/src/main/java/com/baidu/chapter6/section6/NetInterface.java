package com.baidu.chapter6.section6;

import com.baidu.chapter6.section5.Bean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface NetInterface {
    //获取号码归属地，返回来类型是Bean, 需要两个参数分别为phone何key
    @GET("mobile/get")
    Observable<Bean> getAddress(@Query("phone") String phone, @Query("key") String key);
}

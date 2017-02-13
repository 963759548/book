package com.baidu.news.engine;

import com.baidu.news.domain.NewsBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface Api {
    @GET("toutiao/index")
    Observable<NewsBean> getNews(@Query("key")String key,@Query("type")String type);

}

package com.example.a86066.appfinale;

import com.example.a86066.appfinale.jsoned.Itemed;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;


public interface Mainitemedserver {
//    https://www.wanandroid.com/project/list/1/json?cid=294
//    String BaseUrl="https://www.wanandroid.com/";

    //query
    @GET
    Observable<Itemed> getservered(@Url String url);
}

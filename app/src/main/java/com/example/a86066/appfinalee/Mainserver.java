package com.example.a86066.appfinalee;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Mainserver {
//    https://www.wanandroid.com/project/tree/json
    String  BaseUrl="https://www.wanandroid.com/";
    @GET("project/tree/json")
    Observable<Man> getserver();
}

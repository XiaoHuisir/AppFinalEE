package com.example.a86066.appfinale;

import com.example.a86066.appfinale.jsoned.Classes;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Mainclasses {
//      https://www.wanandroid.com/project/tree/json
    String BaseUrl="https://www.wanandroid.com/";
    @GET("project/tree/json")
    Observable<Classes> getmainclasses();
}

package com.example.a86066.appfinalee.tabandvp;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface servereding {

    @GET
    Observable<Demo> getserverding(@Url String url);
}

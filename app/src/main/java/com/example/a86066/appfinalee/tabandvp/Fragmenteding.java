package com.example.a86066.appfinalee.tabandvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a86066.appfinalee.R;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragmenteding extends Fragment {
    private int cod;
    private String uri="https://www.wanandroid.com/project/list/1/json?cid=";
    private View view;
    private RecyclerView mRecycDing;

    //uri+cod
    public void setCod(int cod) {
        this.cod = cod;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ding, null);
        initView(view);

        return view;
    }

    private void initView(View view) {
        mRecycDing = (RecyclerView) view.findViewById(R.id.recyc_ding);
        mRecycDing.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        initlist();
    }

    private void initlist() {
        Observable<Demo> observable = new Retrofit.Builder()
                .baseUrl("https://www.wanandroid.com/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(servereding.class).getserverding(uri + cod);
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Demo>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Demo demo) {
                        List<Demo.DataBean.DatasBean> list = demo.getData().getDatas();
                        //
                        Cycadagter cycadagter = new Cycadagter(list);
                        mRecycDing.setAdapter(cycadagter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

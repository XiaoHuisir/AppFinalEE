package com.example.a86066.appfinale;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a86066.appfinale.adaptereding.FragAdapter;
import com.example.a86066.appfinale.jsoned.Itemed;

import java.io.File;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragmenteding extends Fragment {
    public int cid;
   public String  ciid="https://www.wanandroid.com/project/list/1/json?cid=";
    private View view;
    private RecyclerView mReyclerviewFragment;

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view, null);

        initView(view);
        return view;
    }

    private void initView(View view) {
        mReyclerviewFragment = (RecyclerView) view.findViewById(R.id.reyclerviewFragment);
        mReyclerviewFragment.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        initlist();
    }

    private void initlist() {
        //缓存
        File cacheDir = MainApplication.context.getCacheDir();
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache(cacheDir, 1024 * 1024))
                .build();
        new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl("https://www.wanandroid.com/")
                .build()
                .create(Mainitemedserver.class)
                .getservered(ciid+cid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Itemed>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Itemed itemed) {
                        List<Itemed.DataBean.DatasBean> list = itemed.getData().getDatas();
                        //创建适配器
                        FragAdapter adapter = new FragAdapter(list);
                        //绑定适配器
                        mReyclerviewFragment.setAdapter(adapter);

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

package com.example.a86066.appfinalee;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.a86066.appfinalee.tabandvp.Fragmenteding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragmentone extends Fragment {
    private View view;
    private TabLayout mTablayoutview;
    private ViewPager mViewpagerview;
    private ArrayList<Fragment> vplist;
    private List<Man.DataBean> tab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mTablayoutview = (TabLayout) view.findViewById(R.id.tablayoutview);
        mViewpagerview = (ViewPager) view.findViewById(R.id.viewpagerview);
        initlist();
    }

    private void initlist() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Mainserver.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Mainserver mainserver = retrofit.create(Mainserver.class);
        Observable<Man> getserver = mainserver.getserver();
        getserver.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Man>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Man man) {
                        tab = man.getData();

                        vplist = new ArrayList<>();
                        for (int i = 0; i < tab.size(); i++) {

                            Fragmenteding fragmenteding = new Fragmenteding();
                            fragmenteding.setCod(tab.get(i).getId());
                            vplist.add(fragmenteding);
                        }
                        Andtablayout andtablayout = new Andtablayout(getChildFragmentManager(), vplist, tab);
                        mViewpagerview.setAdapter(andtablayout);
                        mTablayoutview.setupWithViewPager(mViewpagerview);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(getActivity(),"异常:"+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}

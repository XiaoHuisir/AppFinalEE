package com.example.a86066.appfinale;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.a86066.appfinale.adaptereding.VPandTabAdapter;
import com.example.a86066.appfinale.jsoned.Classes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tablayoutview)
    TabLayout mTablayoutview;
    @BindView(R.id.viewpagerview)
    ViewPager mViewpagerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initlist();
    }

    private void initlist() {
//        https://www.wanandroid.com/project/tree/json
        //缓存
        File cacheDir = MainApplication.context.getCacheDir();
        OkHttpClient client = new OkHttpClient.Builder()
                .cache(new Cache(cacheDir, 1024 * 1024))
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Mainclasses.BaseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Mainclasses mainclasses = retrofit.create(Mainclasses.class);
        Observable<Classes> getmainclasses = mainclasses.getmainclasses();
        getmainclasses.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Classes>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Classes classes) {
                        List<Classes.DataBean> tablist = classes.getData();
                        ArrayList<Fragment> fraglist = new ArrayList<>();
                        for (int i = 0; i < tablist.size(); i++) {
                            Fragmenteding fragmenteding = new Fragmenteding();
                            fragmenteding.setCid(tablist.get(i).getId());
                            fraglist.add(fragmenteding);
                        }
                        //创建适配器getchildfragmentmanager
                        FragmentManager manager = getSupportFragmentManager();
                        VPandTabAdapter adapter = new VPandTabAdapter(manager, tablist, fraglist);
                        //绑定适配器
                        mViewpagerview.setAdapter(adapter);

                        // Vp and tablayout
                        mTablayoutview.setupWithViewPager(mViewpagerview);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, "--- 请求失败" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

}

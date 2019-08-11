package com.example.a86066.appfinalee;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewpagerview;
    private TabLayout mTablayoutview;

    private ArrayList<Fragment> vplist;
    private ArrayList<String> tablist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mViewpagerview = (ViewPager) findViewById(R.id.viewpagerview);
        mTablayoutview = (TabLayout) findViewById(R.id.tablayoutview);

        initVpandTab();
    }

    private void initVpandTab() {
        Fragmentone fone = new Fragmentone();
        vplist = new ArrayList<>();

        vplist.add(fone);
        tablist = new ArrayList<>();
        tablist.add("首页");
        VpandTabadpater tabadpater = new VpandTabadpater(getSupportFragmentManager(), vplist, tablist);
        mViewpagerview.setAdapter(tabadpater);
            mTablayoutview.setupWithViewPager(mViewpagerview);

    }
}

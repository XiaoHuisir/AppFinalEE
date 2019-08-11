package com.example.a86066.appfinalee;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Andtablayout extends FragmentStatePagerAdapter {
    private ArrayList<Fragment> flist;
    private List<Man.DataBean> tab;

    public Andtablayout(FragmentManager fm, ArrayList<Fragment> flist, List<Man.DataBean> data) {
        super(fm);
        this.flist = flist;
        this.tab = data;
    }

    public Andtablayout(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return flist.get(position);
    }

    @Override
    public int getCount() {
        return flist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return   tab.get(position).getName();
    }
}

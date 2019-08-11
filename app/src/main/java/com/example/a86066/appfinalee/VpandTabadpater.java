package com.example.a86066.appfinalee;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VpandTabadpater extends FragmentPagerAdapter {
    private ArrayList<Fragment> vplist;
    private ArrayList<String> tablist;

    public VpandTabadpater(FragmentManager fm, ArrayList<Fragment> vplist, ArrayList<String> tablist) {
        super(fm);
        this.vplist = vplist;
        this.tablist = tablist;
    }

    public VpandTabadpater(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return vplist.get(position);
    }

    @Override
    public int getCount() {
        return vplist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position);
    }
}

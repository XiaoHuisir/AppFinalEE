package com.example.a86066.appfinale.adaptereding;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.a86066.appfinale.jsoned.Classes;

import java.util.ArrayList;
import java.util.List;

public class VPandTabAdapter extends FragmentStatePagerAdapter {
   private  List<Classes.DataBean> tablist =new ArrayList<>();
   private ArrayList<Fragment> fraglist = new ArrayList<>();

    public VPandTabAdapter(FragmentManager fm, List<Classes.DataBean> tablist, ArrayList<Fragment> fraglist) {
        super(fm);
        this.tablist = tablist;
        this.fraglist = fraglist;
    }

    public VPandTabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fraglist.get(position);
    }

    @Override
    public int getCount() {
        return fraglist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  tablist.get(position).getName() ;

    }
}

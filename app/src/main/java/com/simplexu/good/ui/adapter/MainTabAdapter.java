package com.simplexu.good.ui.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.simplexu.good.config.ApiData;

import java.util.List;

public class MainTabAdapter extends FragmentStatePagerAdapter{

    private List<Fragment> fragments;

    public MainTabAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return ApiData.Titles[position];
    }
}

package com.simplexu.good.ui.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.simplexu.good.R;
import com.simplexu.good.config.ApiData;
import com.simplexu.good.ui.adapter.MainTabAdapter;
import com.simplexu.good.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    private MainTabAdapter mAdapter;
    private List<Fragment> fragments;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        mTabLayout = (TabLayout) view.findViewById(R.id.layout_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.vp_home);

        fragments = new ArrayList<>();
        for (int i = 0; i < ApiData.Titles.length; i++) {
            fragments.add(HomeFragment.getInstance(ApiData.Titles[i]));
        }

        mAdapter = new MainTabAdapter(getChildFragmentManager(),fragments);

        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        return view;
    }


}

package com.kingtvarshin.oasis2016new.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.kingtvarshin.oasis2016new.Adapter.TabPagerAdapter;
import com.kingtvarshin.oasis2016new.R;

/**
 * Created by lenovo on 14-09-2016.
 */
public class Fragment_schedule extends android.support.v4.app.Fragment {

    ViewPager Tab;
    TabPagerAdapter TabAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_schedule,container,false);

        TabAdapter = new TabPagerAdapter(getChildFragmentManager());
        Tab = (ViewPager)rootView.findViewById(R.id.pager);
        Tab.setOffscreenPageLimit(5);
        Tab.setAdapter(new TabPagerAdapter(getChildFragmentManager()));
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip)rootView.findViewById(R.id.tabs);

        tabs.setViewPager(Tab);

        return rootView;
    }


}
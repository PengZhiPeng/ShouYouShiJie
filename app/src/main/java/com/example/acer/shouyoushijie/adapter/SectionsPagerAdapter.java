package com.example.acer.shouyoushijie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.acer.shouyoushijie.view.FindFragment;
import com.example.acer.shouyoushijie.view.FuliFragment;
import com.example.acer.shouyoushijie.view.GameFragment;
import com.example.acer.shouyoushijie.view.MainFragment;


public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitle[]=new String[]{"首页","发现","找游戏","福利"};

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new FindFragment();
            case 2:
                return new GameFragment();
            case 3:
                return new FuliFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}

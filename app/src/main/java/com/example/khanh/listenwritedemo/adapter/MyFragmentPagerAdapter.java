package com.example.khanh.listenwritedemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by khanh on 07/07/2017.
 */

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> listFragment;
//    Bundle bundle;


    public MyFragmentPagerAdapter(FragmentManager fm, List<Fragment> listFragment) {
        super(fm);
        this.listFragment = listFragment;
    }

    @Override
    public Fragment getItem(int position) {
//        listFragment.get(position).setArguments(this.bundle);
        return listFragment.get(position);
    }

    @Override
    public int getCount() {
        return listFragment.size();
    }

}

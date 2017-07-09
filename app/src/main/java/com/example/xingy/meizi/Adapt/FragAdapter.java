package com.example.xingy.meizi.Adapt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by xingy on 2017/6/9.
 */

public class FragAdapter extends FragmentPagerAdapter {
    private List<Fragment>fragments;
    public FragAdapter(FragmentManager fm,List<Fragment>fragmentList) {
        super(fm);
        fragments=fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

package com.example.wxl.myapplication.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.wxl.myapplication.fragment.NewsBasicFragment;

import java.util.List;

/**
 * Created by wxl on 4/29/16.
 */
public class PagerAdaper extends FragmentPagerAdapter{
    private List<String> mList;
    public PagerAdaper(FragmentManager fm) {
        super(fm);
    }

    public PagerAdaper(FragmentManager fm, List<String> list) {
        super(fm);
        mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return NewsBasicFragment.newInstance(mList.get(position));
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mList.get(position);
    }

    public void setData(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }
}

package com.example.commuteeazy.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeTabsAdapter extends FragmentPagerAdapter {

    private final List<Fragment> pages = new ArrayList<>();
    private final List<String> titles = new ArrayList<>();

    public HomeTabsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return pages.get(i);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    public void addFragment(Fragment page,String title){
        pages.add(page);
        titles.add(title);
    }

    public CharSequence getPageTitle(int position){
        return titles.get(position);
    }
}

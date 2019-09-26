package com.example.koshanapp;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class tabLayout1 extends FragmentPagerAdapter {

    private int tabcount;

    public tabLayout1(FragmentManager fm, int tabcount){
        super(fm);
        this.tabcount=tabcount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new HomeView();
            case 1:
                return new BookView();
            case 2:
                return new ProfileView();
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}

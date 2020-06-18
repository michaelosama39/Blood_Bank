package com.example.bloodbank.adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.bloodbank.view.fragment.splash.Onboard_1Fragment;
import com.example.bloodbank.view.fragment.splash.SplashFragment;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerWithFragmentAdapter extends FragmentPagerAdapter {

    Fragment fragment [] = {new Onboard_1Fragment() , new SplashFragment()};

    //private List<Fragment> fragments = new ArrayList<>();

    public ViewPagerWithFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

/*
    public void addPager(Fragment fragments) {
        this.fragments.add(fragments);
    }
*/

    // Returns total number of pages
    @Override
    public int getCount() {
        return fragment.length;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        return fragment[position];
    }

}

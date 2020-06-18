package com.example.bloodbank.view.activity;

import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ViewPagerAdapter;
import com.example.bloodbank.adapter.ViewPagerWithFragmentAdapter;
import com.example.bloodbank.view.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SplashCycleActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        ViewPagerWithFragmentAdapter adapter = new ViewPagerWithFragmentAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
    }
}

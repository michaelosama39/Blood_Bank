package com.example.bloodbank.view.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ViewPagerAdapter;
import com.example.bloodbank.view.BaseFragment;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabbFragment extends Fragment {


    @BindView(R.id.tab)
    TabLayout tabb;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.pager)
    ViewPager pager;


    public TabbFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tabb, container, false);
        ButterKnife.bind(this, view);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager());
        viewPagerAdapter.addPager(new ArticlesTabbFragment(), "المقالات");
        viewPagerAdapter.addPager(new OrdersTabbFragment(), "طلبات الطبرع");
        pager.setAdapter(viewPagerAdapter);
        tabb.setupWithViewPager(pager);
        return view;
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        DonationRequestFragment fragment = new DonationRequestFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.tab_layout, fragment);
        transaction.commit();
    }
}

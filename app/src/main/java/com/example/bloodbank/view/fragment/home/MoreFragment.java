package com.example.bloodbank.view.fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.view.BaseFragment;
import com.example.bloodbank.view.fragment.home.AboutAppFragment;
import com.example.bloodbank.view.fragment.home.ConnectWithUsFragment;
import com.example.bloodbank.view.fragment.home.SettingNotificationFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreFragment extends BaseFragment {


    @BindView(R.id.more_fav_TV)
    TextView moreFavTV;
    @BindView(R.id.more_concet_TV)
    TextView moreConcetTV;
    @BindView(R.id.more_aboutApp_TV)
    TextView moreAboutAppTV;
    @BindView(R.id.more_rate_TV)
    TextView moreRateTV;
    @BindView(R.id.more_setting_TV)
    TextView moreSettingTV;
    @BindView(R.id.more_logOut_TV)
    TextView moreLogOutTV;

    BottomNavigationView bottomNav;
    public MoreFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this , view);
        return view;
    }


    @OnClick({R.id.more_fav_TV, R.id.more_concet_TV, R.id.more_aboutApp_TV, R.id.more_rate_TV, R.id.more_setting_TV, R.id.more_logOut_TV})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.more_fav_TV:
                FavoriteFragment fragment = new FavoriteFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.tab_layout, fragment);
                transaction.commit();
                break;
            case R.id.more_concet_TV:
                ConnectWithUsFragment fragment1 = new ConnectWithUsFragment();
                FragmentTransaction transaction1 = getFragmentManager().beginTransaction();
                transaction1.replace(R.id.tab_layout, fragment1);
                transaction1.commit();
                break;
            case R.id.more_aboutApp_TV:
                AboutAppFragment fragment2 = new AboutAppFragment();
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                transaction2.replace(R.id.tab_layout, fragment2);
                transaction2.commit();
                break;
            case R.id.more_rate_TV:
                break;
            case R.id.more_setting_TV:
                SettingNotificationFragment fragment4 = new SettingNotificationFragment();
                FragmentTransaction transaction4 = getFragmentManager().beginTransaction();
                transaction4.replace(R.id.tab_layout, fragment4);
                transaction4.commit();
                break;
            case R.id.more_logOut_TV:
                break;
        }
    }
}

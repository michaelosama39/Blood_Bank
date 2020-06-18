package com.example.bloodbank.view.fragment.splash;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.bloodbank.R;
import com.example.bloodbank.view.BaseFragment;
import com.example.bloodbank.view.activity.LoginCycleActivity;
import com.example.bloodbank.view.activity.SplashCycleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Onboard_1Fragment extends BaseFragment {

    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;

    public Onboard_1Fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_onboard_1, container, false);
        ButterKnife.bind(this , view);
        return view;
    }
}

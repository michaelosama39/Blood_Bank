package com.example.bloodbank.view.fragment.splash;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.view.BaseFragment;
import com.example.bloodbank.view.activity.LoginCycleActivity;
import com.example.bloodbank.view.fragment.authCycle.ForgetCodeFragment;
import com.example.bloodbank.view.fragment.authCycle.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SplashFragment extends BaseFragment {

    @BindView(R.id.imageView2)
    ImageView imageView2;
    @BindView(R.id.imageView3)
    ImageView imageView3;
    @BindView(R.id.splash_next_btn)
    Button splashNextBtn;
    @BindView(R.id.splash_Fragment)
    RelativeLayout splashFragment;

    public SplashFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.splash_next_btn)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity() , LoginCycleActivity.class);
        getActivity().startActivity(intent);
        getActivity().finish();
    }
}


package com.example.bloodbank.view;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class BaseFragment extends Fragment {

    public BaseActivity baseActivity ;

    public void setUpActivity(){
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;
    }

    public void onBack(){
        baseActivity.superBackPressed();
    }

}

package com.example.bloodbank.view.fragment.home;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bloodbank.R;
import com.example.bloodbank.view.BaseFragment;


public class DetailsPostFragment extends BaseFragment {


    public DetailsPostFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_post, container, false);

        return view;
    }

}

package com.example.bloodbank.view.fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.data.model.settingsApp.SettingsApp;
import com.example.bloodbank.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AboutAppFragment extends BaseFragment {


    @BindView(R.id.imageView12)
    TextView textView;
    private ApiService apiService;
    private LoginData loginData;

    public AboutAppFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_app, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        call();
        return view;
    }

    public void call(){
        apiService.SETTINGS_APP_CALL(loginData.getApiToken()).enqueue(new Callback<SettingsApp>() {
            @Override
            public void onResponse(Call<SettingsApp> call, Response<SettingsApp> response) {
                try {
                    if (response.body().getStatus() == 1){
                        textView.setText(response.body().getData().getAboutApp());
                    }else {
                        Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SettingsApp> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

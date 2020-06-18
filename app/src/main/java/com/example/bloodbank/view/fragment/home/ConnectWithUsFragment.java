package com.example.bloodbank.view.fragment.home;


import
        android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.contactUs.ContactUs;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.view.BaseFragment;
import com.example.bloodbank.view.activity.ArticlesCycleActivity;
import com.example.bloodbank.view.fragment.authCycle.ForgetPhoneFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConnectWithUsFragment extends BaseFragment {


    @BindView(R.id.connectWithUs_titleMasg_ET)
    EditText connectWithUsTitleMasgET;
    @BindView(R.id.connectWithUs_masg_ET)
    EditText connectWithUsMasgET;
    private ApiService apiService;
    private String title;
    private String masg;
    private LoginData loginData;

    public ConnectWithUsFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_connect_with_us, container, false);
        ButterKnife.bind(this , view);
        apiService = RetrofitClient.getClient();
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        return view;
    }

    private void connectWithUs() {
        title = connectWithUsTitleMasgET.getText().toString();
        masg = connectWithUsMasgET.getText().toString();

        apiService.CONTACT_US_CALL(loginData.getApiToken(), title, masg).enqueue(new Callback<ContactUs>() {
            @Override
            public void onResponse(Call<ContactUs> call, Response<ContactUs> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(baseActivity, response.body().getMsg() , Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getActivity(), ArticlesCycleActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getContext(), "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ContactUs> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }


    @OnClick({R.id.connectWithUs_send_BTN, R.id.connectWithUs_back_BTN})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.connectWithUs_send_BTN:
                connectWithUs();
                break;
            case R.id.connectWithUs_back_BTN:
                break;
        }
    }

}

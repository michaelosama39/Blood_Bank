package com.example.bloodbank.view.fragment.authCycle;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.login.Login;

import com.example.bloodbank.view.BaseFragment;
import com.example.bloodbank.view.activity.ArticlesCycleActivity;
import com.example.bloodbank.view.activity.LoginCycleActivity;
import com.example.bloodbank.view.fragment.home.ArticlesTabbFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;
import static com.example.bloodbank.data.local.HelperMethod.disappearKeypad;
import static com.example.bloodbank.data.local.SharedPreferencesManger.REMEMBER;
import static com.example.bloodbank.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.bloodbank.data.local.SharedPreferencesManger.saveUserData;

public class LoginFragment extends BaseFragment {

    @BindView(R.id.login_phone_ET)
    EditText loginPhoneET;
    @BindView(R.id.login_password_ET)
    EditText loginPasswordET;
    @BindView(R.id.login_enter_BTN)
    Button loginEnterBTN;
    private ApiService apiService;
    private boolean remember = true;


    public LoginFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
        return view;
    }


    private void login() {
        String phone = loginPhoneET.getText().toString();
        String password = loginPasswordET.getText().toString();
        apiService.Login_Call(phone, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        saveUserData(getActivity(), response.body().getData());
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        SharedPreferencesManger.SaveString(getActivity(), USER_PASSWORD, password);
                        Intent intent = new Intent(getActivity(), ArticlesCycleActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }else {
                        Toast.makeText(getContext(), "else" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                   Toast.makeText(getActivity(), "Exception" + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.login_forget_TV, R.id.login_enter_BTN, R.id.login_newAccount_TV})
    public void onViewClicked(View view) {
        disappearKeypad(getActivity() ,getView());
        switch (view.getId()) {
            case R.id.login_enter_BTN:
                login();
                break;
            case R.id.login_forget_TV:
                ForgetPhoneFragment fragment = new ForgetPhoneFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.login_activity, fragment);
                transaction.commit();
                break;

            case R.id.login_newAccount_TV:
                NewAccountFragment fragment2 = new NewAccountFragment();
                FragmentTransaction transaction2 = getFragmentManager().beginTransaction();
                transaction2.replace(R.id.login_activity, fragment2);
                transaction2.commit();
                break;
        }
    }


}





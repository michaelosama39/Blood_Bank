package com.example.bloodbank.view.fragment.authCycle;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.data.model.newPassword.NewPassword;
import com.example.bloodbank.data.model.profile.Client;
import com.example.bloodbank.data.model.profile.Profile;
import com.example.bloodbank.data.model.profile.ProfileData;
import com.example.bloodbank.view.BaseFragment;
import com.example.bloodbank.view.activity.ArticlesCycleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;
import static com.example.bloodbank.data.local.HelperMethod.disappearKeypad;
import static com.example.bloodbank.data.local.SharedPreferencesManger.PASSWORD;
import static com.example.bloodbank.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.bloodbank.data.local.SharedPreferencesManger.saveUserData;

public class ForgetCodeFragment extends BaseFragment {

    @BindView(R.id.forget_code_ET)
    EditText forgetCodeET;
    @BindView(R.id.forget_password_ET)
    EditText forgetPasswordET;
    @BindView(R.id.forget_passwordCheck_ET)
    EditText forgetPasswordCheckET;
    @BindView(R.id.forget_phone_BTN)
    Button forgetPhoneBTN;
    private ApiService apiService;
    private LoginData loginData;

    public ForgetCodeFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_code, container, false);

        apiService = RetrofitClient.getClient();
        ButterKnife.bind(this, view);
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        getProfile();
        return view;
    }

    private void getProfile() {
        startCall(getClient().getProfile(loginData.getApiToken()), false);
    }

    private void startCall(Call<Profile> call, boolean edit) {
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                try {
                    if (response.body().getStatus() == 1){
                        setData(response.body().getData().getClient());
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setData(Client client) {
        forgetCodeET.setText(client.getPinCode());
    }

    private void setPassword() {
        String pass = forgetPasswordET.getText().toString();
        String passCheck = forgetPasswordCheckET.getText().toString();

        apiService.newPassword(pass, passCheck).enqueue(new Callback<NewPassword>() {
            @Override
            public void onResponse(Call<NewPassword> call, Response<NewPassword> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        saveUserData(getActivity(), (LoginData) response.body().getData());
                        if (pass.equals(passCheck)) {
                            SharedPreferencesManger.SaveString(getActivity(), PASSWORD, pass);
                            Intent intent = new Intent(getActivity(), ArticlesCycleActivity.class);
                            startActivity(intent);
                        }
                    }else {
                        Toast.makeText(getContext(), "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(), "ee" + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewPassword> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.forget_phone_BTN)
    public void onViewClicked() {
        disappearKeypad(getActivity() ,getView());
        setPassword();
    }
}

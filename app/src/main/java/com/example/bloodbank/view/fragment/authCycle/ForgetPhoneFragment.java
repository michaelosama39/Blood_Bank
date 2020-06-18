package com.example.bloodbank.view.fragment.authCycle;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.model.resetPassword.ResetPassword;
import com.example.bloodbank.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.local.HelperMethod.disappearKeypad;


public class ForgetPhoneFragment extends BaseFragment {


    @BindView(R.id.forget_phone_ET)
    EditText forgetPhoneET;
    @BindView(R.id.forget_phone_BTN)
    Button forgetPhoneBTN;

    private ApiService apiService;


    public ForgetPhoneFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_forget_phone, container, false);

        apiService = RetrofitClient.getClient();
        ButterKnife.bind(this, view);

        return view;
    }

    private void phone() {
        String phone = forgetPhoneET.getText().toString();

        apiService.getPhone(phone).enqueue(new Callback<ResetPassword>() {
            @Override
            public void onResponse(Call<ResetPassword> call, Response<ResetPassword> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        response.body().getData();
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        ForgetCodeFragment fragment = new ForgetCodeFragment();
                        FragmentTransaction transaction = getFragmentManager().beginTransaction();
                        transaction.replace(R.id.login_activity, fragment);
                        transaction.commit();
                    } else {
                        Toast.makeText(getContext(), "" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(), "" + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResetPassword> call, Throwable t) {
                Toast.makeText(getActivity(), "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.forget_phone_BTN)
    public void onViewClicked() {
        disappearKeypad(getActivity() ,getView());
        phone();
    }
}

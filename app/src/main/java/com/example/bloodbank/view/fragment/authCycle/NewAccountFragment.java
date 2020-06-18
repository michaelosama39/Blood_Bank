package com.example.bloodbank.view.fragment.authCycle;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.view.BaseFragment;
import com.example.bloodbank.view.activity.ArticlesCycleActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;
import static com.example.bloodbank.data.local.HelperMethod.disappearKeypad;
import static com.example.bloodbank.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.bloodbank.data.local.SharedPreferencesManger.saveUserData;
import static com.example.bloodbank.helper.GeneralRequest.getSpinnerData;

public class NewAccountFragment extends BaseFragment {

    @BindView(R.id.newAcount_name_ET)
    EditText newAcountNameET;
    @BindView(R.id.newAcount_email_ET)
    EditText newAcountEmailET;
    @BindView(R.id.newAcount_data_ET)
    EditText newAcountDataET;
    @BindView(R.id.newAcount_type_SPN)
    Spinner newAcountTypeSPN;
    @BindView(R.id.newAcount_lastData_ET)
    EditText newAcountLastDataET;
    @BindView(R.id.newAcount_governorate_SPN)
    Spinner newAcountGovernorateSPN;
    @BindView(R.id.newAcount_city_SPN)
    Spinner newAcountCitySPN;
    @BindView(R.id.newAcount_phone_ET)
    EditText newAcountPhoneET;
    @BindView(R.id.newAcount_password_ET)
    EditText newAcountPasswordET;
    @BindView(R.id.newAcount_passwordCheck_ET)
    EditText newAcountPasswordCheckET;
    @BindView(R.id.save_btn)
    Button saveBtn;
    String name;
    String email;
    String date;
    int city;
    int bloodType;
    String phone;
    String lastDate;
    String pass;
    String passCom;

    private SpinnerAdapter gavarmentAdapter;
    private SpinnerAdapter bloodTypeAdapter;
    private SpinnerAdapter cityAdapter;
    private ApiService apiService;

    public NewAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_new_account, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
        setSpinner();
        return view;
    }

    private void setSpinner() {
        bloodTypeAdapter = new SpinnerAdapter(getActivity());
        gavarmentAdapter = new SpinnerAdapter(getActivity());
        cityAdapter = new SpinnerAdapter(getActivity());

        getSpinnerData(getClient().getBloodTypes(), newAcountTypeSPN, bloodTypeAdapter, 0, getString(R.string.blood_types));

        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (i != 0) {
                    getSpinnerData(getClient().getCity(gavarmentAdapter.selectedId), newAcountCitySPN, cityAdapter, 0, getString(R.string.city));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        getSpinnerData(getClient().getGavarment(), newAcountGovernorateSPN, gavarmentAdapter, 0,
                getString(R.string.governorate), onItemSelectedListener);
    }

    @OnClick({R.id.save_btn, R.id.new_account_fragment_lin_sub_view})
    public void onViewClicked(View view) {
        disappearKeypad(getActivity() ,getView());
        switch (view.getId()) {
            case R.id.save_btn:
                register();
                break;
            case R.id.new_account_fragment_lin_sub_view:
                break;
        }
    }


    private void register() {
        name = newAcountNameET.getText().toString();
        email = newAcountEmailET.getText().toString();
        date = newAcountDataET.getText().toString();
        phone = newAcountPhoneET.getText().toString();
        lastDate = newAcountLastDataET.getText().toString();
        pass = newAcountPasswordET.getText().toString();
        passCom = newAcountPasswordCheckET.getText().toString();
        city = cityAdapter.selectedId;
        bloodType = bloodTypeAdapter.selectedId;

        apiService.registerCall(name, email, date, city, phone, lastDate, pass, passCom, bloodType).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        saveUserData(getActivity(), response.body().getData());
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                        SharedPreferencesManger.SaveString(getActivity(), USER_PASSWORD, pass);
                        Intent intent = new Intent(getActivity(), ArticlesCycleActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                    }else{
                        Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();

            }
        });
    }

}

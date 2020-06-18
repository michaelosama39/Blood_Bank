package com.example.bloodbank.view.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.createDonation.CreateDonation;
import com.example.bloodbank.data.model.donationRequest.DonationRequest;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.data.model.profile.Profile;
import com.example.bloodbank.view.BaseFragment;
import com.example.bloodbank.view.activity.ArticlesCycleActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;
import static com.example.bloodbank.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.bloodbank.data.local.SharedPreferencesManger.loadUserData;
import static com.example.bloodbank.data.local.SharedPreferencesManger.saveUserData;
import static com.example.bloodbank.helper.GeneralRequest.getSpinnerData;


public class DonationRequestFragment extends BaseFragment {

    @BindView(R.id.donationRequest_name_ET)
    EditText donationRequestNameET;
    @BindView(R.id.donationRequest_age_ET)
    EditText donationRequestAgeET;
    @BindView(R.id.donationRequest_type_SPN)
    Spinner donationRequestTypeSPN;
    @BindView(R.id.donationRequest_number_ET)
    EditText donationRequestNumberET;
    @BindView(R.id.donationRequest_adress_BTN)
    Button donationRequestAdressET;
    @BindView(R.id.donationRequest_governoment_SPN)
    Spinner donationRequestGovernomentSPN;
    @BindView(R.id.donationRequest_ctiy_SPN)
    Spinner donationRequestCtiySPN;
    @BindView(R.id.donationRequest_phone_ET)
    EditText donationRequestPhoneET;
    @BindView(R.id.donationRequest_notes_ET)
    EditText donationRequestNotesET;
    private SpinnerAdapter bloodTypeAdapter;
    private SpinnerAdapter gavarmentAdapter;
    private SpinnerAdapter cityAdapter;
    private ApiService apiService;
    private String name;
    private String age;
    private int bloodType;
    private String number;
    private int governoment;
    private int ctiy;
    private String phone;
    private String adress;
    private String note;

    private LoginData loginData;

    public DonationRequestFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_donation_request, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        setSpinner();
        return view;
    }

    private void setSpinner() {
        bloodTypeAdapter = new SpinnerAdapter(getActivity());
        gavarmentAdapter = new SpinnerAdapter(getActivity());
        cityAdapter = new SpinnerAdapter(getActivity());

        getSpinnerData(getClient().getBloodTypes(), donationRequestTypeSPN, bloodTypeAdapter, 0, getString(R.string.blood_types));

        AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                if (i != 0) {
                    getSpinnerData(getClient().getCity(gavarmentAdapter.selectedId), donationRequestCtiySPN, cityAdapter, 0, getString(R.string.city));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        };
        getSpinnerData(getClient().getGavarment(), donationRequestGovernomentSPN, gavarmentAdapter, 0,
                getString(R.string.governorate), onItemSelectedListener);
    }

    @OnClick({R.id.donationRequest_adress_BTN, R.id.donationRequest_save_BTN})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.donationRequest_adress_BTN:
                MapViewFragment fragment = new MapViewFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.articles_activity, fragment);
                transaction.commit();
                break;
            case R.id.donationRequest_save_BTN:
                setDonationRequest();
                break;
        }
    }

    private void setDonationRequest() {
        name = donationRequestNameET.getText().toString();
        age = donationRequestAgeET.getText().toString();
        bloodType = bloodTypeAdapter.selectedId;
        number = donationRequestNumberET.getText().toString();
        adress = donationRequestAdressET.getText().toString();
        governoment = gavarmentAdapter.selectedId;
        ctiy = cityAdapter.selectedId;
        phone = donationRequestPhoneET.getText().toString();
        note = donationRequestNotesET.getText().toString();

        apiService.DONATION_CREATE_CALL(loginData.getApiToken(),
                name, age, bloodType, number, adress, ctiy, phone, note).enqueue(new Callback<CreateDonation>() {
            @Override
            public void onResponse(Call<CreateDonation> call, Response<CreateDonation> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Intent intent = new Intent(getActivity(), ArticlesCycleActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getContext(), "else" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(), "catch" + e, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<CreateDonation> call, Throwable t) {

            }
        });
    }

}


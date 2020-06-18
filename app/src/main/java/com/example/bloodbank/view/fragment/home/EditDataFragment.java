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

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.data.model.profile.Client;
import com.example.bloodbank.data.model.profile.Profile;
import com.example.bloodbank.data.model.setting.DateTxt;
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
import static com.example.bloodbank.data.local.SharedPreferencesManger.USER_PASSWORD;
import static com.example.bloodbank.data.local.SharedPreferencesManger.saveUserData;
import static com.example.bloodbank.helper.GeneralRequest.getSpinnerData;


public class EditDataFragment extends BaseFragment {
    @BindView(R.id.editData_name_ET)
    EditText editDataNameET;
    @BindView(R.id.editData_email_ET)
    EditText editDataEmailET;
    @BindView(R.id.editData_data_ET)
    EditText editDataDataET;
    @BindView(R.id.editData_type_SPN)
    Spinner editDataTypeSPN;
    @BindView(R.id.editData_lastData_ET)
    EditText editDataLastDataET;
    @BindView(R.id.editData_governorate_SPN)
    Spinner editDataGovernorateSPN;
    @BindView(R.id.editData_city_SPN)
    Spinner editDataCitySPN;
    @BindView(R.id.editData_phone_ET)
    EditText editDataPhoneET;
    @BindView(R.id.editData_password_ET)
    EditText editDataPasswordET;
    @BindView(R.id.editData_passwordCheck_ET)
    EditText editDataPasswordCheckET;
    @BindView(R.id.editData_Edit_btn)
    Button editDataEditBtn;
    private SpinnerAdapter gavarmentAdapter;
    private SpinnerAdapter bloodTypeAdapter;
    private SpinnerAdapter cityAdapter;
    String name;
    String email;
    String date;
    int city;
    int bloodType;
    String phone;
    String lastDate;
    String pass;
    String passCom;
    private ApiService apiService;
    private LoginData loginData;

    public EditDataFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_data, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
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
                    if (response.body().getStatus() == 1) {

                        setData(response.body().getData().getClient());
                        gavarmentAdapter = new SpinnerAdapter(getActivity());
                        bloodTypeAdapter = new SpinnerAdapter(getActivity());
                        cityAdapter = new SpinnerAdapter(getActivity());

                        AdapterView.OnItemSelectedListener onGavarmentItemClick = new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
                                if (i != 0) {

                                    getSpinnerData(getClient().getCity(gavarmentAdapter.selectedId), editDataCitySPN, cityAdapter,
                                            response.body().getData().getClient().getCity().getId(), getString(R.string.city));
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        };

                        getSpinnerData(getClient().getGavarment(), editDataGovernorateSPN, gavarmentAdapter,
                                response.body().getData().getClient().getCity().getGovernorate().getId(), getString(R.string.governorate), onGavarmentItemClick);

                        getSpinnerData(getClient().getBloodTypes(), editDataTypeSPN, bloodTypeAdapter,
                                response.body().getData().getClient().getBloodType().getId(), getString(R.string.blood_types));

                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {

            }
        });
    }

    private void setData(Client client) {
        editDataNameET.setText(client.getName());
        editDataEmailET.setText(client.getEmail());
        editDataDataET.setText(client.getBirthDate());
        editDataLastDataET.setText(client.getDonationLastDate());
        editDataPhoneET.setText(client.getPhone());
        editDataPasswordET.setText("");
        editDataPasswordCheckET.setText("");
    }

    private void editProfile() {
        name = editDataNameET.getText().toString();
        email = editDataEmailET.getText().toString();
        date = editDataDataET.getText().toString();
        phone = editDataPhoneET.getText().toString();
        lastDate = editDataLastDataET.getText().toString();
        pass = editDataPasswordET.getText().toString();
        passCom = editDataPasswordCheckET.getText().toString();
        city = editDataCitySPN.getId();
        bloodType = editDataTypeSPN.getId();

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
                    }else {
                        Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();

            }
        });

    }

    private Boolean Validation() {
        if (editDataNameET == null && editDataEmailET == null &&
                editDataDataET == null && editDataLastDataET == null &&
                editDataPhoneET.length() <= 11 && editDataPasswordET == null &&
                editDataPasswordCheckET == null && editDataTypeSPN == null &&
                editDataCitySPN == null && editDataGovernorateSPN == null) {
            Toast.makeText(getActivity(), "OOOOOOOOOOPS", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    @OnClick(R.id.editData_Edit_btn)
    public void onViewClicked() {
        disappearKeypad(getActivity() ,getView());
        if (Validation() == true){
            editProfile();
        }
    }
}


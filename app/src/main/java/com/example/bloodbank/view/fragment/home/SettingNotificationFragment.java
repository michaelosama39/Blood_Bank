package com.example.bloodbank.view.fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.city.City;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.data.model.notificationSetting.NotificationSetting;
import com.example.bloodbank.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingNotificationFragment extends BaseFragment {

    List<String> governorates = new ArrayList<>();
    List<String> bloodTypes = new ArrayList<>();
    @BindView(R.id.save_SettingNotification_btn)
    Button saveSettingNotificationBtn;
    @BindView(R.id.settingNotification_BloodTypes_RecyclerView)
    RecyclerView settingNotificationBloodTypesRecyclerView;
    @BindView(R.id.settingNotification_Governorates_RecyclerView)
    RecyclerView settingNotificationGovernoratesRecyclerView;
    private LoginData loginData;

    public SettingNotificationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting_notification, container, false);
        getNotificationsSettings();
//        init();
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        return view;
    }

//    private void init() {
//        settingNotificationBloodTypesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//        settingNotificationGovernoratesRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
//    }

    private void getNotificationsSettings() {
        getClient().getNotificationsSettings(loginData.getApiToken()).enqueue(new Callback<NotificationSetting>() {
            @Override
            public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        bloodTypes = response.body().getData().getBloodTypes();
                        governorates = response.body().getData().getGovernorates();
                        getBloodTypes();
                        getGovernorates();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<NotificationSetting> call, Throwable t) {

            }
        });
    }

    private void getBloodTypes() {
        getClient().getBloodTypes().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {

            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }

    private void getGovernorates() {
        getClient().getGavarment().enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {

            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }

}

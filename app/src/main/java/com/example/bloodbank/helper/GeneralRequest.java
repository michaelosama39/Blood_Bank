package com.example.bloodbank.helper;

import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.model.city.City;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRequest {
    public static void getSpinnerData(Call<City> call, Spinner spinner
            , final SpinnerAdapter adapter, final Integer selectId
            , String hint, AdapterView.OnItemSelectedListener listener) {
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                try {
                    adapter.setData(response.body().getData(), hint);
                    spinner.setAdapter(adapter);
                    for (int i = 0; i < adapter.cityData.size(); i++) {
                        if (adapter.cityData.get(i).getId().equals(selectId)) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                    spinner.setOnItemSelectedListener(listener);
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }

    public static void getSpinnerData(Call<City> call, Spinner spinner
            , SpinnerAdapter adapter, Integer selectId, String hint) {
        call.enqueue(new Callback<City>() {
            @Override
            public void onResponse(Call<City> call, Response<City> response) {
                try {
                    adapter.setData(response.body().getData(), hint);
                    spinner.setAdapter(adapter);
                    for (int i = 0; i < adapter.cityData.size(); i++) {
                        if (adapter.cityData.get(i).getId().equals(selectId)) {
                            spinner.setSelection(i);
                            break;
                        }
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<City> call, Throwable t) {

            }
        });
    }
}


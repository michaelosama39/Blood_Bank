package com.example.bloodbank.view.fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.DonationRequestAdapter;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.donationRequest.DonationRequest;
import com.example.bloodbank.data.model.donationRequest.DonationRequestPagination;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.RetrofitClient.getClient;
import static com.example.bloodbank.helper.GeneralRequest.getSpinnerData;


public class OrdersTabbFragment extends Fragment {

    @BindView(R.id.donationRequest_RecyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.order_type_SPN)
    Spinner orderTypeSPN;
    @BindView(R.id.order_gavarment_SPN)
    Spinner orderGavarmentSPN;

    private LinearLayoutManager layoutManager;
    private List<DonationRequestPagination> list = new ArrayList<>();
    private DonationRequestAdapter adapter;
    private ApiService apiService;
    private SpinnerAdapter bloodTypeAdapter;
    private SpinnerAdapter gavarmentAdapter;
    private LoginData loginData;

    public OrdersTabbFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_orders_tabb, container, false);
        ButterKnife.bind(this, view);
        apiService = getClient();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DonationRequestAdapter(getActivity(), getActivity(), list);
        recyclerView.setAdapter(adapter);
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        setSpinner();
        donation();
        return view;
    }

    private void setSpinner() {
        bloodTypeAdapter = new SpinnerAdapter(getActivity());
        gavarmentAdapter = new SpinnerAdapter(getActivity());

        getSpinnerData(getClient().getBloodTypes(), orderTypeSPN, bloodTypeAdapter, 0,
                getString(R.string.blood_types));

        getSpinnerData(getClient().getGavarment(), orderGavarmentSPN, gavarmentAdapter, 0,
                getString(R.string.governorate));
    }

    private void donation(){
    apiService.DONATION_REQUEST_CALL(loginData.getApiToken(), 1).enqueue(new Callback<DonationRequest>() {
        @Override
        public void onResponse(Call<DonationRequest> call, Response<DonationRequest> response) {
            try {
                if(response.body().getStatus() == 1){
                    list.addAll(response.body().getData().getData());
                    adapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getContext(), "else" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }

            }catch (Exception e){
                Toast.makeText(getContext(), "catch" + e, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<DonationRequest> call, Throwable t) {
            Toast.makeText(getActivity(), "onFailure" + t, Toast.LENGTH_SHORT).show();
        }
    });
    }
}

package com.example.bloodbank.view.fragment.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.displayDonationDetails.DisplayDonationDetails;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailsDonationFragment extends BaseFragment {

    @BindView(R.id.name_TV)
    TextView nameTV;
    @BindView(R.id.age_TV)
    TextView ageTV;
    @BindView(R.id.type_TV)
    TextView typeTV;
    @BindView(R.id.bagsNum_TV)
    TextView bagsNumTV;
    @BindView(R.id.hospital_TV)
    TextView hospitalTV;
    @BindView(R.id.adress_TV)
    TextView adressTV;
    @BindView(R.id.phone_Tv)
    TextView phoneTv;
    @BindView(R.id.notes_TV)
    TextView notesTV;
    @BindView(R.id.map_IMG)
    ImageView mapIMG;
    @BindView(R.id.recyclerDonation_call_BTN)
    Button recyclerDonationCallBTN;
    private ApiService apiService;
    private LoginData loginData;

    public DetailsDonationFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details_donation, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        DetailsDonation();
        return view;
    }

    private void DetailsDonation() {
        apiService.DISPLAY_DONATION_DETAILS_CALL(loginData.getApiToken(), 418).enqueue(new Callback<DisplayDonationDetails>() {
            @Override
            public void onResponse(Call<DisplayDonationDetails> call, Response<DisplayDonationDetails> response) {
                try {
                    if (response.body().getStatus()== 1){
                        nameTV.setText(response.body().getData().getPatientName());
                        ageTV.setText(response.body().getData().getPatientAge());
                        typeTV.setText(response.body().getData().getBloodType().getName());
                        bagsNumTV.setText(response.body().getData().getBagsNum());
                        hospitalTV.setText(response.body().getData().getHospitalName());
                        adressTV.setText(response.body().getData().getHospitalAddress());
                        phoneTv.setText(response.body().getData().getPhone());
                        notesTV.setText(response.body().getData().getPhone());

                    }else {
                        Toast.makeText(getContext(), ""+response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                }catch (Exception e){
                    Toast.makeText(getContext(), response.body().getMsg(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<DisplayDonationDetails> call, Throwable t) {
                Toast.makeText(getActivity(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.recyclerDonation_call_BTN)
    public void onViewClicked() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        startActivity(intent);

    }
}

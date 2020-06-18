package com.example.bloodbank.adapter;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.donationRequest.DonationRequestPagination;
import com.example.bloodbank.view.activity.ArticlesCycleActivity;
import com.example.bloodbank.view.fragment.authCycle.ForgetPhoneFragment;
import com.example.bloodbank.view.fragment.home.DetailsDonationFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.Manifest.permission.CALL_PHONE;
import static androidx.core.content.ContextCompat.startActivity;

public class

DonationRequestAdapter extends RecyclerView.Adapter<DonationRequestAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<DonationRequestPagination> donationRequestPaginations = new ArrayList<>();

    public DonationRequestAdapter(Context context, Activity activity, List<DonationRequestPagination> donationRequestPaginations) {
        this.context = context;
        this.activity = activity;
        this.donationRequestPaginations = donationRequestPaginations;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyceview_donation,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position );
    }

    private void setData(ViewHolder holder, int position) {
        holder.recyclerDonationTypeTV.setText(donationRequestPaginations.get(position).getBloodType().getName());
        holder.recyclerDonationName.setText(donationRequestPaginations.get(position).getClient().getName());
        holder.recyclerDonationHospital.setText(donationRequestPaginations.get(position).getHospitalName());
        holder.recyclerDonationAdress.setText(donationRequestPaginations.get(position).getHospitalAddress());
    }

    private void setAction(ViewHolder holder, int position ) {
    holder.recyclerDonationCall.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
        try {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:01286008357"));
            context.startActivity(intent);

        }catch (Exception e){
            Toast.makeText(context, "Exception" + e, Toast.LENGTH_SHORT).show();
        }
    }
    });

    holder.recyclerDonationAbout.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DetailsDonationFragment fragment = new DetailsDonationFragment();
            FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
            transaction.replace(R.id.tab_layout, fragment);
            transaction.commit();
        }
    });
    }

    @Override
    public int getItemCount() {
        return donationRequestPaginations.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recyclerDonation_type_TV)
        TextView recyclerDonationTypeTV;
        @BindView(R.id.recyclerDonation_call)
        Button recyclerDonationCall;
        @BindView(R.id.recyclerDonation_about)
        Button recyclerDonationAbout;
        @BindView(R.id.recyclerDonation_name)
        TextView recyclerDonationName;
        @BindView(R.id.recyclerDonation_hospital)
        TextView recyclerDonationHospital;
        @BindView(R.id.recyclerDonation_adress)
        TextView recyclerDonationAdress;
        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

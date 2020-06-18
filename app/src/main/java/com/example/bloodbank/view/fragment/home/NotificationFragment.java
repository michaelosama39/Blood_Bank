package com.example.bloodbank.view.fragment.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.NotificationAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.data.model.notificationsList.NotificationsList;
import com.example.bloodbank.data.model.notificationsList.NotificationsListPagination;
import com.example.bloodbank.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationFragment extends BaseFragment {
    @BindView(R.id.recycler)
    RecyclerView recycler;
    private LinearLayoutManager layoutManager;
    private List<NotificationsListPagination> list = new ArrayList<>();
    private NotificationAdapter adapter;
    private ApiService apiService;
    private LoginData loginData;

    public NotificationFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
        layoutManager = new LinearLayoutManager(getContext());
        recycler.setLayoutManager(layoutManager);
        adapter = new NotificationAdapter(getActivity(), getActivity(), list);
        recycler.setAdapter(adapter);
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        notificationList();
        return view;
    }

    private void notificationList() {
        apiService.NOTIFICATIONS_LIST_CALL(loginData.getApiToken(), 6).enqueue(new Callback<NotificationsList>() {
            @Override
            public void onResponse(Call<NotificationsList> call, Response<NotificationsList> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        list.addAll(response.body().getData().getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "else" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(), "catch" + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificationsList> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure" + t, Toast.LENGTH_SHORT).show();
            }
        });

    }


}

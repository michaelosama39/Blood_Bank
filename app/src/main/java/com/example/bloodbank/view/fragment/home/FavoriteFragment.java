package com.example.bloodbank.view.fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.FavoruitePostsAdapter;
import com.example.bloodbank.adapter.PostsAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.allPosts.AllPostsData;
import com.example.bloodbank.data.model.favoruitePosts.FavoruitePosts;
import com.example.bloodbank.data.model.favoruitePosts.FavoruitePostsData;
import com.example.bloodbank.data.model.favoruitePosts.FavoruitePostsPagination;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteFragment extends BaseFragment {


    @BindView(R.id.favorite_post_recyclerView)
    RecyclerView favoritePostRecyclerView;

    private ApiService apiService;
    private LinearLayoutManager layoutManager;
    private LoginData loginData;
    private FavoruitePostsAdapter adapter;
    private List<FavoruitePostsPagination> list = new ArrayList<>();

    public FavoriteFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
        layoutManager = new LinearLayoutManager(getContext());
        favoritePostRecyclerView.setLayoutManager(layoutManager);
        adapter = new FavoruitePostsAdapter(getActivity() , getActivity(), list);
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        favoritePostRecyclerView.setAdapter(adapter);
        favourite();
        return view;
    }

    private void favourite() {
        apiService.FAVORUITE_POSTS_CALL(loginData.getApiToken()).enqueue(new Callback<FavoruitePosts>() {
            @Override
            public void onResponse(Call<FavoruitePosts> call, Response<FavoruitePosts> response) {
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
            public void onFailure(Call<FavoruitePosts> call, Throwable t) {

            }
        });

    }


}

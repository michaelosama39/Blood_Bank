package com.example.bloodbank.view.fragment.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.PostsAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.api.RetrofitClient;
import com.example.bloodbank.data.local.SharedPreferencesManger;
import com.example.bloodbank.data.model.allPosts.AllPosts;
import com.example.bloodbank.data.model.allPosts.AllPostsData;
import com.example.bloodbank.data.model.login.LoginData;
import com.example.bloodbank.view.BaseActivity;
import com.example.bloodbank.view.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ArticlesTabbFragment extends Fragment {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    private LoginData loginData;

    private LinearLayoutManager layoutManager;
    private List<AllPostsData> allPostsData = new ArrayList<>();
    private PostsAdapter adapter;
    private ApiService apiService;

    public ArticlesTabbFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_articles_tabb, container, false);
        ButterKnife.bind(this, view);
        apiService = RetrofitClient.getClient();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new PostsAdapter(getActivity() ,getActivity() ,allPostsData) ;
        recyclerView.setAdapter(adapter);
        loginData = SharedPreferencesManger.loadUserData(getActivity());
        posts();
        return view;
    }

    private void posts() {
        apiService.ALL_POSTS_CALL(loginData.getApiToken(), 1).enqueue(new Callback<AllPosts>() {
            @Override
            public void onResponse(Call<AllPosts> call, Response<AllPosts> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        allPostsData.addAll(response.body().getData().getData());
                        adapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(getContext(), "else" + response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    Toast.makeText(getContext(), "catch" + e, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AllPosts> call, Throwable t) {
                Toast.makeText(getActivity(), "onFailure" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

}

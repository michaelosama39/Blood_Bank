package com.example.bloodbank.view.activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;

import com.example.bloodbank.R;
import com.example.bloodbank.view.BaseActivity;
import com.example.bloodbank.view.fragment.home.ArticlesTabbFragment;
import com.example.bloodbank.view.fragment.home.EditDataFragment;
import com.example.bloodbank.view.fragment.home.MoreFragment;
import com.example.bloodbank.view.fragment.home.NotificationFragment;
import com.example.bloodbank.view.fragment.home.TabbFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesCycleActivity extends BaseActivity {

    @BindView(R.id.BottomNavigation)
    BottomNavigationView bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_cycle);
        ButterKnife.bind(this);

        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.home_nav){
                    TabbFragment fragment = new TabbFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.tab_layout , fragment);
                    transaction.commit();
                }

                if (id == R.id.edit_nav){
                    EditDataFragment fragment = new EditDataFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.tab_layout , fragment);
                    transaction.commit();
                }

                if (id == R.id.notification_nav){
                    NotificationFragment fragment = new NotificationFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.tab_layout , fragment);
                    transaction.commit();
                }

                if (id == R.id.more_nav){
                    MoreFragment fragment = new MoreFragment();
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.tab_layout , fragment);
                    transaction.commit();
                }
                return true;
            }
        });
        bottomNavigation.setSelectedItemId(R.id.home_nav);
    }

}

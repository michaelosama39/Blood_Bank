package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.allPosts.AllPostsData;
import com.example.bloodbank.view.activity.ArticlesCycleActivity;
import com.example.bloodbank.view.fragment.home.DetailsDonationFragment;
import com.example.bloodbank.view.fragment.home.DetailsPostFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<AllPostsData> allPostsPaginations = new ArrayList<>();

    public PostsAdapter(Context context, Activity activity, List<AllPostsData> allPostsPaginations) {
        this.context = context;
        this.activity = activity;
        this.allPostsPaginations = allPostsPaginations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_posts,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.recyclerTextView.setText(allPostsPaginations.get(position).getTitle());

    }

    private void setAction(ViewHolder holder, int position) {
        holder.recyclerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailsPostFragment fragment =  new DetailsPostFragment();
                FragmentTransaction transaction = fragment.getFragmentManager().beginTransaction();
                transaction.replace(R.id.tab_layout, fragment);
                transaction.commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allPostsPaginations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.favourite_checkBox)
        CheckBox favouriteCheckBox;
        @BindView(R.id.recycler_imageView)
        ImageView recyclerImageView;
        @BindView(R.id.recycler_textView)
        TextView recyclerTextView;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.favoruitePosts.FavoruitePostsPagination;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

FavoruitePostsAdapter extends RecyclerView.Adapter<FavoruitePostsAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<FavoruitePostsPagination> favoruitePostsPaginations = new ArrayList<>();

    public FavoruitePostsAdapter(Context context, Activity activity, List<FavoruitePostsPagination> favoruitePostsPaginations) {
        this.context = context;
        this.activity = activity;
        this.favoruitePostsPaginations = favoruitePostsPaginations;
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
        holder.recyclerTextView.setText(favoruitePostsPaginations.get(position).getTitle());

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return favoruitePostsPaginations.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_imageView)
        ImageView recyclerImageView;
        @BindView(R.id.recycler_textView)
        TextView recyclerTextView;
        @BindView(R.id.favourite_checkBox)
        CheckBox favouriteCheckBox;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}

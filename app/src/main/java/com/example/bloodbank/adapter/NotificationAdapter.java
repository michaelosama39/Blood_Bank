package com.example.bloodbank.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.notificationsList.NotificationsListData;
import com.example.bloodbank.data.model.notificationsList.NotificationsListPagination;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class

NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<NotificationsListPagination> notificationsListDataPagination = new ArrayList<>();

    public NotificationAdapter(Context context, Activity activity, List<NotificationsListPagination> notificationsListDataPagination) {
        this.context = context;
        this.activity = activity;
        this.notificationsListDataPagination = notificationsListDataPagination;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_notifications,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
    holder.checkBox.setText(notificationsListDataPagination.get(position).getTitle());
    holder.alarm.setText(notificationsListDataPagination.get(position).getCreatedAt());
    }

    private void setAction(ViewHolder holder, int position) {
    holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            notificationsListDataPagination.get(position).getPivot().getIsRead();
        }
    });
    }

    @Override
    public int getItemCount() {
        return notificationsListDataPagination.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.checkBox)
        CheckBox checkBox;
        @BindView(R.id.alarm)
        TextView alarm;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
